package org.usfirst.frc.team747.robot.subsystems;

import java.io.IOException;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.DriveCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
    public static TalonSRX talonDriveLeftPrimary = new TalonSRX(0);

	public TalonSRX talonDriveLeftMid = new TalonSRX(1);

	public TalonSRX talonDriveLeftBack = new TalonSRX(2);

	public static TalonSRX talonDriveRightPrimary = new TalonSRX(3);

	public TalonSRX talonDriveRightMid = new TalonSRX(4);

	public TalonSRX talonDriveRightBack = new TalonSRX(5);

    private static final int pidIdx = 0;
    private static final int timeoutMs = 10;
    
    private static final double ENCODER_TICKS = 4096;
// 4096 for the mag encoders
    private static final double WHEEL_CIRCUMFERNCE = 18.85; //18.875 then was 18.85
    
    private static final double MAX_PERCENT_VOLTAGE = 1.0;
    private static final double MIN_PERCENT_VOLTAGE = 0.0;
  
    //Gear Distance IN REVOLUTIONS 3.7125 (needed like another inch or so; trying 3.725
    
    private static final double TICKS_PER_INCH = ENCODER_TICKS / WHEEL_CIRCUMFERNCE;
    
	StringBuilder sb = new StringBuilder();
	int loops = 0;
    
    public DriveSubsystem() {
        super();
        
        talonDriveLeftPrimary.setInverted(true);
        this.talonDriveLeftMid.setInverted(true);
        this.talonDriveLeftBack.setInverted(true);
        
        talonDriveRightPrimary.setInverted(false);
        this.talonDriveRightMid.setInverted(false);
        this.talonDriveRightBack.setInverted(false);
       
//        this.talonDriveLeftPrimary.setSensorPhase(true);
//        this.talonDriveRightPrimary.setSensorPhase(false);
        
        talonDriveLeftMid.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        talonDriveLeftBack.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        talonDriveRightMid.set(ControlMode.Follower, talonDriveRightPrimary.getDeviceID());
        talonDriveRightBack.set(ControlMode.Follower, talonDriveRightPrimary.getDeviceID());
        
        talonDriveLeftPrimary.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);

        talonDriveRightPrimary.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);
        
//        this.talonDriveLeftPrimary.config_kF(pidIdx, 0.1489, timeoutMs);
//        this.talonDriveRightPrimary.config_kF(pidIdx, 0.1489, timeoutMs);
        
        talonDriveLeftPrimary.configMotionCruiseVelocity(3092, timeoutMs); //706
        talonDriveLeftPrimary.configMotionAcceleration(3092, timeoutMs); //706
        talonDriveRightPrimary.configMotionCruiseVelocity(3092, timeoutMs); //706
        talonDriveRightPrimary.configMotionAcceleration(3092, timeoutMs); //706

        talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        
    }
   
    public void set(double left, double right) {
    	
        talonDriveLeftPrimary.set(ControlMode.PercentOutput, left);
        talonDriveRightPrimary.set(ControlMode.PercentOutput, right);
    }

    public void setPID(double leftTicks, double rightTicks) {
        talonDriveLeftPrimary.set(ControlMode.MotionMagic, leftTicks);
        talonDriveRightPrimary.set(ControlMode.MotionMagic, rightTicks);
    }
    
    public double convertRevsToInches(double revs) {
        return revs * WHEEL_CIRCUMFERNCE;
    }
    
    public double convertInchesToRevs(double inches) {
        return inches / WHEEL_CIRCUMFERNCE;
    }
    
    public double convertRevsToTicks(double revs) {
        return revs * ENCODER_TICKS;
    }
    
    public double convertTicksToRevs(double ticks) {
        return ticks / ENCODER_TICKS;
    }
    
    public double convertInchesToTicks(double inches) {
        return convertRevsToTicks(convertInchesToRevs(inches));
    }
    
    public double convertTicksToInches(double ticks) {
        return convertRevsToInches(convertTicksToRevs(ticks));
    }
    
    public void changeControlMode(ControlMode mode) {
    	talonDriveLeftPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	talonDriveRightPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
        talonDriveLeftPrimary.set(mode, 0);
        talonDriveRightPrimary.set(mode, 0);
    }
    
    public void stop() {
        ControlMode mode = talonDriveLeftPrimary.getControlMode();

        double left = 0;
        double right = 0;
        
        switch (mode) {
        case MotionMagic:
            left = talonDriveLeftPrimary.getSelectedSensorPosition(pidIdx);
            right = talonDriveRightPrimary.getSelectedSensorPosition(pidIdx);
            break;
        case PercentOutput:
        case Velocity:
        case Follower:
        default:
            // Values should be 0;
            break;
        }
        
        this.set(left, right);
    }
    
//    public void talonEnableControl() {
//        talonDriveLeftPrimary.enableControl();
//        talonDriveRightPrimary.enableControl();
//    }
//    
//    public void talonDisableControl() {
//        talonDriveLeftPrimary.disableControl();
//        talonDriveRightPrimary.disableControl();
//    }
    
    public void enablePositionControl() {
        this.changeControlMode(ControlMode.MotionMagic);
//        this.talonEnableControl();
    }

    public void enableVBusControl() {
//        this.talonDisableControl();
        this.changeControlMode(ControlMode.PercentOutput);
    }
    
    public void resetLeftEncoder() {
        this.enableVBusControl();
        talonDriveLeftPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetRightEncoder() {
        this.enableVBusControl();
        talonDriveRightPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetBothEncoders(){
        this.enableVBusControl();
    	this.talonDriveRightPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	this.talonDriveLeftPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
        //get the current encoder position regardless of whether it is the current feedback device
    public double getLeftEncoderPosition() {
        return talonDriveLeftPrimary.getSelectedSensorPosition(pidIdx);
    }
    
    public double getRightEncoderPosition() {
        return talonDriveRightPrimary.getSelectedSensorPosition(pidIdx);
    }

    public double getLeftPosition() {
        return talonDriveLeftPrimary.getSelectedSensorPosition(pidIdx);
    }
        
    public double getRightPosition() {
        return talonDriveRightPrimary.getSelectedSensorPosition(pidIdx);
    }
    
    public double getCombindedEncoderPosition() {
        return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
    }



	public static void setMotorSpeed(double speedMod) {
		if(OI.leftStick.getRawAxis(1) > 0.15 || OI.leftStick.getRawAxis(1) < -0.15) {
			talonDriveLeftPrimary.set(ControlMode.PercentOutput, -OI.leftStick.getRawAxis(1)*speedMod);
		} else {
			talonDriveLeftPrimary.set(ControlMode.PercentOutput, 0);
		}
		if(OI.rightStick.getRawAxis(1) > 0.15 || OI.rightStick.getRawAxis(1) < -0.15) {
			talonDriveRightPrimary.set(ControlMode.PercentOutput, OI.rightStick.getRawAxis(1)*-speedMod);
		} else {
			talonDriveRightPrimary.set(ControlMode.PercentOutput, 0);
		}
	
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}
}
