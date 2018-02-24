/**
 * Example demonstrating the motion magic control mode.
 * Tested with Logitech F710 USB Gamepad inserted into Driver Station.
 * 
 * Be sure to select the correct feedback sensor using configSelectedFeedbackSensor() below.
 *
 * After deploying/debugging this to your RIO, first use the left Y-stick 
 * to throttle the Talon manually.  This will confirm your hardware setup/sensors
 * and will allow you to take initial measurements.
 * 
 * Be sure to confirm that when the Talon is driving forward (green) the 
 * position sensor is moving in a positive direction.  If this is not the 
 * cause, flip the boolean input to the setSensorPhase() call below.
 *
 * Once you've ensured your feedback device is in-phase with the motor,
 * and followed the walk-through in the Talon SRX Software Reference Manual,
 * use button1 to motion-magic servo to target position specified by the gamepad stick.
 */
package org.usfirst.frc.team217.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;

public class Robot extends IterativeRobot {
	TalonSRX _rightBackPrimaryTalon = new TalonSRX(1);
	TalonSRX _rightFrontTalon = new TalonSRX(2);
	Joystick _joy = new Joystick(0);
	StringBuilder _sb = new StringBuilder();

	public void robotInit() {

	    _rightFrontTalon.set(ControlMode.MotionMagic, _rightBackPrimaryTalon.getDeviceID());
		/* first choose the sensor */
		_rightBackPrimaryTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.setSensorPhase(true);
		_rightBackPrimaryTalon.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		_rightBackPrimaryTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* set the peak and nominal outputs */
		_rightBackPrimaryTalon.configNominalOutputForward(0, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.configPeakOu tputReverse(-1, Constants.kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		_rightBackPrimaryTalon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_rightBackPrimaryTalon.config_kF(0, 0.2, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.config_kP(0, 0.2, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.config_kI(0, 0, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.config_kD(0, 0, Constants.kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		_rightBackPrimaryTalon.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		_rightBackPrimaryTalon.configMotionAcceleration(6000, Constants.kTimeoutMs);
		/* zero the sensor */
		_rightBackPrimaryTalon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		/* get gamepad axis - forward stick is positive */
		double leftYstick = -1.0 * _joy.getY();
		/* calculate the percent motor output */
		double motorOutput = _rightBackPrimaryTalon.getMotorOutputPercent();
		/* prepare line to print */
		_sb.append("\tOut%:");
		_sb.append(motorOutput);
		_sb.append("\tVel:");
		_sb.append(_rightBackPrimaryTalon.getSelectedSensorVelocity(Constants.kPIDLoopIdx));

		if (_joy.getRawButton(1)) {
			/* Motion Magic - 4096 ticks/rev * 10 Rotations in either direction */
			double targetPos = leftYstick * 4096 * 10.0;
			_rightBackPrimaryTalon.set(ControlMode.MotionMagic, targetPos);

			/* append more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(_rightBackPrimaryTalon.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
			_sb.append(targetPos);
		} else {
			/* Percent voltage mode */
			_rightBackPrimaryTalon.set(ControlMode.PercentOutput, leftYstick);
		}
		/* instrumentation */
		Instrum.Process(_rightBackPrimaryTalon, _sb);
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (Exception e) {
		}
	}
}
