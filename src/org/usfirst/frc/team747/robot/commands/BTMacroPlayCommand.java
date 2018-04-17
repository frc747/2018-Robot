package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.readwrite.BTMain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BTMacroPlayCommand extends Command {

    public BTMacroPlayCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	BTMain.autonomousInit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	BTMain.autonomousPeriodic();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return OI.finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	BTMain.disabledInit();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
