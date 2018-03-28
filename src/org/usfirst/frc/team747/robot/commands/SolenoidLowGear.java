package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SolenoidLowGear extends Command {

    public SolenoidLowGear() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.cube);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.switchb = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.pneu.leftHIGH.set(true);
        Robot.pneu.rightHIGH.set(false);
        
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
    }
}
