package org.usfirst.frc.team747.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class Drive {
	static Joystick rightDrive = new Joystick(1);
	static Joystick leftDrive = new Joystick(0);
	public static TalonSRX leftFrontDrive = new TalonSRX(0);
	public static TalonSRX leftRearDrive = new TalonSRX(1);
	public static TalonSRX rightFrontDrive = new TalonSRX(2);
	public static TalonSRX rightRearDrive = new TalonSRX(3);
	
	public static void drive() {
		double speedModifier;
		
		boolean leftTriggerPressed = leftDrive.getRawButton(1);
		boolean rightTriggerPressed = rightDrive.getRawButton(1);
		boolean leftBrakePressed = leftDrive.getRawButton(2);
		boolean rightBrakePressed = rightDrive.getRawButton(2);
			
		if(leftTriggerPressed ^ rightTriggerPressed) {
			speedModifier = 0.5;
		} else if (leftTriggerPressed && rightTriggerPressed) {
			speedModifier = 1.0;
		} else {
			speedModifier = 0.25;
		}
		
		if(leftBrakePressed || rightBrakePressed) {
			speedModifier = 0.0;
		}
		
		CubeSightIntakeSpin.detectCube();

			
		double rightJoystickValue = -rightDrive.getRawAxis(1)*speedModifier;
		double leftJoystickValue = leftDrive.getRawAxis(1)*speedModifier;
		
		leftFrontDrive.set(ControlMode.PercentOutput, leftJoystickValue);
		leftRearDrive.set(ControlMode.PercentOutput, leftJoystickValue);
		rightFrontDrive.set(ControlMode.PercentOutput, rightJoystickValue);
		rightRearDrive.set(ControlMode.PercentOutput, rightJoystickValue);
	}
}
