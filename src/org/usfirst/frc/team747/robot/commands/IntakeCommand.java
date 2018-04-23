package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {
	
	boolean rev;
	
    public IntakeCommand(boolean reverse) {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.cube);
    	
    	rev = reverse;
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cube.setIntake(true, rev);
    	Robot.cube.setIntakeArms(true, !rev);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cube.setIntake(false, rev);
    	Robot.cube.setIntakeArms(false, !rev);

    }
    
}
