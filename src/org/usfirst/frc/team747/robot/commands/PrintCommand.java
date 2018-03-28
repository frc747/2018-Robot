package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.maps.AutonomousMaps;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintCommand extends Command {

    public PrintCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("LEFT");
    	for(int i = 0; i < AutonomousMaps.left.size(); i++) {
			System.out.println(AutonomousMaps.left.get(i));
		}
    	System.out.println("RIGHT");

		for(int i = 0; i < AutonomousMaps.right.size(); i++) {
			System.out.println(AutonomousMaps.right.get(i));
		}
    	System.out.println("EJECT");

		for(int i = 0; i < AutonomousMaps.ejectButton.size(); i++) {
			System.out.println(AutonomousMaps.ejectButton.get(i));
		}
    	System.out.println("INTAKE");

		for(int i = 0; i < AutonomousMaps.IntakeButton.size(); i++) {
			System.out.println(AutonomousMaps.IntakeButton.get(i));
		}
		setTimeout(0.01);
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
