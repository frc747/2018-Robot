package org.usfirst.frc.team747.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.AutonomousMaps;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class JoystickMappingCommand extends Command {
	public static int mainCounter = 0;
	
	private static boolean fin = false;
    public JoystickMappingCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	File fileL = new File("leftAuto.txt");
    	File fileR = new File("rightAuto.txt");
    	File intakeFile = new File("intakeAuto.txt");
    	File ejectFile = new File("ejectAuto.txt");

    	try {
    		Scanner inputL = new Scanner(fileL);
        	Scanner inputR = new Scanner(fileR);
        	Scanner inputIntake = new Scanner(intakeFile);
        	Scanner inputEject = new Scanner(ejectFile);

        	while (inputL.hasNextLine()) {
        	    AutonomousMaps.left.add(Double.parseDouble(inputL.nextLine()));
        	}
        	while (inputR.hasNextLine()) {
        	    AutonomousMaps.right.add(Double.parseDouble(inputR.nextLine()));
        	}
        	while (inputIntake.hasNextLine()) {
        	    AutonomousMaps.IntakeButton.add(Boolean.parseBoolean(inputIntake.nextLine()));
        	}
        	while (inputEject.hasNextLine()) {
        	    AutonomousMaps.ejectButton.add(Boolean.parseBoolean(inputEject.nextLine()));
        	}
        	inputL.close();
        	inputR.close();
        	inputIntake.close();
        	inputEject.close();
    	} catch (FileNotFoundException e) {
    		SmartDashboard.putString("File found?", "NO" );
    	}
    	

    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(mainCounter<AutonomousMaps.left.size()) {
			Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.set(ControlMode.PercentOutput,  -AutonomousMaps.left.get(mainCounter));
			Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.set(ControlMode.PercentOutput, -AutonomousMaps.right.get(mainCounter));
			if(AutonomousMaps.ejectButton.get(mainCounter)) {
				Robot.cube.setEject(true, false);
			} else {
				Robot.cube.setEject(false, false);
			}
			if(AutonomousMaps.IntakeButton.get(mainCounter)) {
				Robot.cube.setIntake(true, false);
			} else {
				Robot.cube.setIntake(false, false);
			}
			OI.leftV = AutonomousMaps.left.get(mainCounter);
			OI.rightV = AutonomousMaps.right.get(mainCounter);

			OI.i = mainCounter;
		} else {
			fin = true;
		}
		
		mainCounter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(fin) {
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
