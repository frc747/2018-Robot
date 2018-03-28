package org.usfirst.frc.team747.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.AutonomousMaps;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

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
    	Scanner s;
    	

		try {
			s = new Scanner(new File("C:\\Users\\Sammy\\Desktop\\Autonomous Routines\\leftAuto.txt"));
			while (s.hasNext()){
	    	    AutonomousMaps.left.add(Double.parseDouble(s.next()));
	    	}
	    	s.close();
	    	s = new Scanner(new File("C:\\Users\\Sammy\\Desktop\\Autonomous Routines\\rightAuto.txt"));
			while (s.hasNext()){
	    	    AutonomousMaps.right.add(Double.parseDouble(s.next()));
	    	}
	    	s.close();
	    	s = new Scanner(new File("C:\\Users\\Sammy\\Desktop\\Autonomous Routines\\intakeAuto.txt"));
			while (s.hasNext()){
	    	    AutonomousMaps.IntakeButton.add(Boolean.parseBoolean((s.next())));
	    	}
	    	s.close();

			s = new Scanner(new File("C:\\Users\\Sammy\\Desktop\\Autonomous Routines\\ejectAuto.txt"));
			while (s.hasNext()){
	    	    AutonomousMaps.ejectButton.add(Boolean.parseBoolean((s.next())));
	    	}
	    	s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
