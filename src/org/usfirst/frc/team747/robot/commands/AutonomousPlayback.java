package org.usfirst.frc.team747.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.readwrite.BTMain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousPlayback extends Command {
	public static boolean fin = false;

	Scanner scanner;
	long startTime;

	boolean onTime = true;
	double nextDouble;

	public AutonomousPlayback() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// create a scanner to read the file created during BTMacroRecord
		// scanner is able to read out the doubles recorded into recordedAuto.csv (as of
		// 2015)
		
		BTMain.autoFileRead = new String("/home/lvuser/recordedAuto" + Robot.gameData.charAt(0) + ".csv");

		try {
			scanner = new Scanner(new File(BTMain.autoFileRead));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// let scanner know that the numbers are separated by a comma or a newline, as
		// it is a .csv file
		scanner.useDelimiter(",|\\n");

		// lets set start time to the current time you begin autonomous
		startTime = System.currentTimeMillis();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if recordedAuto.csv has a double to read next, then read it
		if ((scanner != null) && (scanner.hasNextDouble())) {
			double t_delta;

			// if we have waited the recorded amount of time assigned to each respective
			// motor value,
			// then move on to the next double value
			// prevents the macro playback from getting ahead of itself and writing
			// different
			// motor values too quickly
			if (onTime) {
				// take next value
				nextDouble = scanner.nextDouble();
			}

			// time recorded for values minus how far into replaying it we are--> if not
			// zero, hold up
			t_delta = nextDouble - (System.currentTimeMillis() - startTime);

			// if we are on time, then set motor values
			if (t_delta <= 0) {
				// for 2015 robot. these are all the motors available to manipulate during
				// autonomous.
				// it is extremely important to set the motors in the SAME ORDER as was recorded
				// in BTMacroRecord
				// otherwise, motor values will be sent to the wrong motors and the robot will
				// be unpredictable

				Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.set(ControlMode.PercentOutput, -scanner.nextDouble());
				Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.set(ControlMode.PercentOutput, -scanner.nextDouble());
				Robot.cube.setEject(scanner.nextBoolean(), false);

				/*
				 * storage.robot.getFrontLeftMotor().setX(scanner.nextDouble());
				 * storage.robot.getFrontRightMotor().setX(scanner.nextDouble());
				 * storage.robot.getBackRightMotor().setX(scanner.nextDouble());
				 * storage.robot.getBackLeftMotor().setX(scanner.nextDouble());
				 * 
				 * storage.robot.getBarrelMotorLeft().setX(scanner.nextDouble());
				 * storage.robot.getBarrelMotorRight().setX(scanner.nextDouble());
				 * 
				 * storage.robot.getLeftForkLeft().setX(scanner.nextDouble());
				 * storage.robot.getLeftForkRight().setX(scanner.nextDouble());
				 * storage.robot.getRightForkLeft().setX(scanner.nextDouble());
				 * storage.robot.getRightForkRight().setX(scanner.nextDouble());
				 */

				// go to next double
				onTime = true;
			}
			// else don't change the values of the motors until we are "onTime"
			else {
				onTime = false;
			}
		}
		// end play, there are no more values to find
		else {
			fin = true;
			this.end();
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
		}
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
