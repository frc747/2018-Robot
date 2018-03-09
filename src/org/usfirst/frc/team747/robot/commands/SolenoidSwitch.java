package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team747.robot.subsystems.PneumaticsSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SolenoidSwitch extends Command {
	
	public boolean pgname = false;

	
	
	
    public SolenoidSwitch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneu);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		if(OI.operatorController.getRawButton(8)) {
			if(Robot.switchb) {
				Robot.pneu.leftHIGH.set(true);
				Robot.pneu.rightHIGH.set(false);
				Robot.switchb = false;
			} else {
				Robot.pneu.leftHIGH.set(false);
				Robot.pneu.rightHIGH.set(true);
				Robot.switchb = true;
			}
		}
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

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
