package org.usfirst.frc.team747.robot.subsystems;

import java.io.IOException;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.DriveCommand;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
    public TalonSRX talonDriveLeftPrimary = new TalonSRX(0),
            		talonDriveLeftMid = new TalonSRX(1),
            		talonDriveLeftBack = new TalonSRX(2),
            		talonDriveRightPrimary = new TalonSRX(3),
            		talonDriveRightMid = new TalonSRX(4),
            		talonDriveRightBack = new TalonSRX(5);

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
        
        this.talonDriveLeftPrimary.setInverted(false);
        this.talonDriveLeftMid.setInverted(false);
        this.talonDriveLeftBack.setInverted(false);
        
        this.talonDriveRightPrimary.setInverted(false);
        this.talonDriveRightMid.setInverted(false);
        this.talonDriveRightBack.setInverted(false);
       
//        this.talonDriveLeftPrimary.setSensorPhase(true);
//        this.talonDriveRightPrimary.setSensorPhase(false);
        
        this.talonDriveLeftMid.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        this.talonDriveLeftBack.set(ControlMode.Follower, talonDriveLeftPrimary.getDeviceID());
        this.talonDriveRightMid.set(ControlMode.Follower, talonDriveRightPrimary.getDeviceID());
        this.talonDriveRightBack.set(ControlMode.Follower, talonDriveRightPrimary.getDeviceID());
        
        this.talonDriveLeftPrimary.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);

        this.talonDriveRightPrimary.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, pidIdx, timeoutMs);
        
//        this.talonDriveLeftPrimary.config_kF(pidIdx, 0.1489, timeoutMs);
//        this.talonDriveRightPrimary.config_kF(pidIdx, 0.1489, timeoutMs);
        
        this.talonDriveLeftPrimary.configMotionCruiseVelocity(2000, timeoutMs); //706
        this.talonDriveLeftPrimary.configMotionAcceleration(2000, timeoutMs); //706
        this.talonDriveRightPrimary.configMotionCruiseVelocity(2000, timeoutMs); //706
        this.talonDriveRightPrimary.configMotionAcceleration(2000, timeoutMs); //706

        this.talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        this.talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        this.talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        this.talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        this.talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        this.talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        this.talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        this.talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new DriveCommand());
    }
    
    public void set(double left, double right) {
    	
        this.talonDriveLeftPrimary.set(ControlMode.PercentOutput, left);
        this.talonDriveRightPrimary.set(ControlMode.PercentOutput, right);
    }

    public void setPID(double leftTicks, double rightTicks) {
        this.talonDriveLeftPrimary.set(ControlMode.MotionMagic, leftTicks);
        this.talonDriveRightPrimary.set(ControlMode.MotionMagic, rightTicks);
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
    	this.talonDriveLeftPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
    	this.talonDriveRightPrimary.setSelectedSensorPosition(0, pidIdx, timeoutMs);
        this.talonDriveLeftPrimary.set(mode, 0);
        this.talonDriveRightPrimary.set(mode, 0);
    }
    
    public void stop() {
        ControlMode mode = this.talonDriveLeftPrimary.getControlMode();

        double left = 0;
        double right = 0;
        
        switch (mode) {
        case MotionMagic:
            left = this.talonDriveLeftPrimary.getSelectedSensorPosition(pidIdx);
            right = this.talonDriveRightPrimary.getSelectedSensorPosition(pidIdx);
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
}

