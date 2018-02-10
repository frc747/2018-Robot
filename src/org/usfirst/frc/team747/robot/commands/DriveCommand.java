package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.DriverStation;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class DriveCommand extends Command {

    
    private static final double MAX_PERCENT_VOLTAGE = 1.0; //was 12 (volts previously, now the input is percent)
    private static final double MIN_PERCENT_VOLTAGE = 0.0; //was 1.9 (volts perviously, now the input is percent)

    private static final int timeoutMs = 10;
    
    public DriveCommand() {
        requires(Robot.DRIVE_TRAIN);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	  Robot.DRIVE_TRAIN.changeControlMode(ControlMode.PercentOutput);
    	  Robot.DRIVE_TRAIN.talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_TRAIN.talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_TRAIN.talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_TRAIN.talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_TRAIN.talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_TRAIN.talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_TRAIN.talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_TRAIN.talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 double left = -OI.JOYSTICK_DRIVER_LEFT.getRawAxis(DriverStation.Joystick.AXIS_Y.getValue());
         double right = -OI.JOYSTICK_DRIVER_RIGHT.getRawAxis(DriverStation.Joystick.AXIS_Y.getValue());

         if (Math.abs(left) < 0.1) {
             left = 0;
         }
         if (Math.abs(right) < 0.1) {
             right = 0;
         }
         
         double speed = 1;
         
         Robot.DRIVE_TRAIN.set(left * speed, right * speed);         
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.DRIVE_TRAIN.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
