package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SolenoidSwitch extends Command {
	
	public boolean pgname = false;

	
//	Solenoid leftLOW = new Solenoid(2);
//	Solenoid rightLOW = new Solenoid(3);
	
    public SolenoidSwitch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveSubsystem.leftHIGH.set(true);
		DriveSubsystem.rightHIGH.set(false);
//		leftLOW.set(false);
//		rightLOW.set(false);
		Robot.switchb = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		//Solenoid Switch
			
		
	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       
        	return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {

		DriveSubsystem.leftHIGH.set(false);
		DriveSubsystem.rightHIGH.set(true);
//		leftLOW.set(true);
//		rightLOW.set(true);
		Robot.switchb = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
