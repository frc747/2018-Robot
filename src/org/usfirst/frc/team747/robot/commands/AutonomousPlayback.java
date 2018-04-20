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
	int minimum;
	private static final double MAX_PERCENT_VOLTAGE = 1.0;
    private static final double MIN_PERCENT_VOLTAGE = 0.0;
	int timeoutMs = 10;
	public static boolean fin = false;
	String folder;
    public AutonomousPlayback(String loc) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	folder = loc;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.DRIVE_SUBSYSTEM.talonDriveLeftFront.enableCurrentLimit(false);
    	Robot.DRIVE_SUBSYSTEM.talonDriveRightFront.enableCurrentLimit(false);
    	Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.enableCurrentLimit(false);
    	Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.enableCurrentLimit(false);

    	Robot.DRIVE_SUBSYSTEM.changeControlMode(ControlMode.PercentOutput);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
    	
    	
    	
    	AutonomousMaps.left.clear();
    	AutonomousMaps.right.clear();
    	AutonomousMaps.ejectButton.clear();
    	AutonomousMaps.IntakeButton.clear();
    	
    	BufferedReader reader;
    	BufferedReader reader2;

    	mainCounter = 0;

		try {
			
			reader = new BufferedReader(new FileReader("/media/sda1/"+folder+"leftAuto.txt"));
			String line = reader.readLine();
			
			while (line != null){
				//if(line.length() > 0) {
	    	    AutonomousMaps.left.add((Double.parseDouble(line))); 
	    	    line = reader.readLine();
	    	    
				//}
	    	}
			reader.close();

	    	reader = new BufferedReader(new FileReader("/media/sda1/"+folder+"rightAuto.txt"));

			String line2 = reader.readLine();
			while (line2 != null){
				//if(line2.length() > 0) {
	    	    AutonomousMaps.right.add(Double.parseDouble(line2));
	    	    line2 = reader.readLine();
				//}
	    	}
			reader.close();

	    	reader = new BufferedReader(new FileReader("/media/sda1/"+folder+"intakeAuto.txt"));

			String line3 = reader.readLine();
			while (line3 != null){
				//if(line3.length() > 0) {
	    	    AutonomousMaps.IntakeButton.add(Boolean.parseBoolean((line3)));
	    	    line3 = reader.readLine();
				//}
	    	}
			reader.close();

			reader = new BufferedReader(new FileReader("/media/sda1/"+folder+"ejectAuto.txt"));

			String line4 = reader.readLine();
			while (line4 != null) {
				//if(line4.length() > 0) {
	    	    AutonomousMaps.ejectButton.add(Boolean.parseBoolean((line4)));
	    	    line4 = reader.readLine();
				//}
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
    		
    	try {
    		if(mainCounter<AutonomousMaps.left.size()-1) {
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
    				Robot.cube.setIntakeAuton(true, false);
    				Robot.cube.setRollersAuton(true, false);
    			} else {
    				Robot.cube.setIntakeAuton(false, false);
    				Robot.cube.setRollersAuton(false, false);

    			}

    			OI.leftV = AutonomousMaps.left.get(mainCounter);
    			OI.rightV = AutonomousMaps.right.get(mainCounter);

    			OI.i = mainCounter;
    			mainCounter++;

    		} else {
    			fin = true;
    		}
    	} catch (ArrayIndexOutOfBoundsException e) {
    		e.printStackTrace();
    		fin = true;
    	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return fin;
    }

    // Called once after isFinished returns true
    protected void end() {
    	mainCounter = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
