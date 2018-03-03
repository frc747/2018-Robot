package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.CubeSubsystem;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class DriveCommand extends Command {
	private CubeSubsystem cube = new CubeSubsystem();
    
    private static final double MAX_PERCENT_VOLTAGE = 1.0; //was 12 (volts previously, now the input is percent)
    private static final double MIN_PERCENT_VOLTAGE = 0.0; //was 1.9 (volts perviously, now the input is percent)

    private static final int timeoutMs = 10;
    
    public DriveCommand() {
        requires(Robot.DRIVE_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	  Robot.DRIVE_SUBSYSTEM.changeControlMode(ControlMode.PercentOutput);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
    	  Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	 double leftO = OI.operatorController.getRawAxis(1);
    	 double rightO = OI.operatorController.getRawAxis(5);
    	
    	
    	 if(!OI.operatorController.getRawButton(1) && !OI.operatorController.getRawButton(4)) {
    		 cube.intakeArms(leftO, rightO);
    	 }
    	 
    	 
    	 double left = -OI.leftStick.getRawAxis(1);
         double right = -OI.rightStick.getRawAxis(1);

         if (Math.abs(left) < 0.1) {
             left = 0;
         }
         if (Math.abs(right) < 0.1) {
             right = 0;
         }
         
         double speed = 1;
         
         Robot.DRIVE_SUBSYSTEM.set(left * speed, right * speed);         
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.DRIVE_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
