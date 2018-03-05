package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PauseCommand extends Command {

    public PauseCommand(double timeOutAmount) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	setTimeout(timeOutAmount);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
