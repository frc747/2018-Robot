package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RotateToCubeCommand extends Command {
	private boolean done = false;
	
    public RotateToCubeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.DRIVE_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Began Vision Rotation:","Starting!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Math.abs(Robot.x) > 10) {
    		if(Robot.x > 10) {
        		Robot.DRIVE_SUBSYSTEM.set(-0.4, -0.4);
    		} else {
        		Robot.DRIVE_SUBSYSTEM.set(0.4, 0.4);
    		}
    	} else if(Robot.x == 0.0) {
    		// Does nothing, as if it does not see a cube why rotate!
    	} else  {
    		Robot.DRIVE_SUBSYSTEM.set(0, 0);

    		done = true; // ends the loop when the cube is within a range.
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       if(done) {
    	return true;
       } else {
    	   return false;
       }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
