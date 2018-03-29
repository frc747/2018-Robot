package org.usfirst.frc.team747.robot.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class AutonomousPlayback extends Command {
	public static int mainCounter = 0;
	public static boolean fin = false;
    public AutonomousPlayback() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	AutonomousMaps.left.clear();
    	AutonomousMaps.right.clear();
    	AutonomousMaps.ejectButton.clear();
    	AutonomousMaps.IntakeButton.clear();
    	BufferedReader reader;


		try {
			reader = new BufferedReader(new FileReader("/media/sda1/leftAuto.txt"));
			String line = reader.readLine();
			while (line != null){
	    	    AutonomousMaps.left.add((Double.parseDouble(line)));
	    	    line = reader.readLine();
	    	}
			reader.close();
	    	reader = new BufferedReader(new FileReader("/media/sda1/rightAuto.txt"));
			String line2 = reader.readLine();
			while (line2 != null){
	    	    AutonomousMaps.right.add(Double.parseDouble(line2));
	    	    line2 = reader.readLine();
	    	}
			reader.close();
	    	reader = new BufferedReader(new FileReader("/media/sda1/intakeAuto.txt"));
			String line3 = reader.readLine();
			while (line3 != null){
	    	    AutonomousMaps.IntakeButton.add(Boolean.parseBoolean((line3)));
	    	    line3 = reader.readLine();
	    	}
			reader.close();
			reader = new BufferedReader(new FileReader("/media/sda1/ejectAuto.txt"));
			String line4 = reader.readLine();
			while (line4 != null){
	    	    AutonomousMaps.ejectButton.add(Boolean.parseBoolean((line4)));
	    	    line4 = reader.readLine();
	    	}
	    	reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SmartDashboard.putNumber("Amount of Ticks for AUTON", AutonomousMaps.left.size());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    		if(mainCounter<AutonomousMaps.left.size()) {
    	    	SmartDashboard.putNumber("Maincounter value", mainCounter);
    	    	SmartDashboard.putNumber("LEFT JOYSTICK AUTON VALUE", AutonomousMaps.left.get(mainCounter));
    	    	SmartDashboard.putNumber("RIGHT JOYSTICK AUTON VALUE", AutonomousMaps.right.get(mainCounter));

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
    	return fin;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
