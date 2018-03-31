package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RollerTimedCommand extends Command {
	
	boolean rev;
	
    public RollerTimedCommand(boolean reverse, double timeOutAmount) {
        // Use requires() here to declare subsystem dependencies
    	
    	rev = reverse;
        setTimeout(timeOutAmount);	
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cube.setRollersAuton(true, rev);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cube.setRollersAuton(false, rev);
    }
    
}
