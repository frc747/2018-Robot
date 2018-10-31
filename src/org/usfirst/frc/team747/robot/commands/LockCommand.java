package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LockCommand extends Command {

	private double leftEncoderStart, rightEncoderStart;
	private double leftEncoderError, rightEncoderError;
	private double angleStart, angleError, angleMultiplier;
	
    public LockCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.DRIVE_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	angleStart = Robot.getNavXAngle();
    	leftEncoderStart = Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition();
    	rightEncoderStart = Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	angleError = Robot.getNavXAngle() - angleStart;
    	angleMultiplier = (angleError/150)+1;
    	
    	leftEncoderError = Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition() - leftEncoderStart;
    	rightEncoderError = Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition() - rightEncoderStart;
    	
    	SmartDashboard.putNumber("Left error Value: ", leftEncoderError);
    	SmartDashboard.putNumber("Right error Value: ", rightEncoderError);

    	
    	Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.set(ControlMode.PercentOutput, -Math.tanh(leftEncoderError/2000)*angleMultiplier);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.set(ControlMode.PercentOutput, -Math.tanh(rightEncoderError/2000)*angleMultiplier);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
