/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team747.robot.commands.ExampleCommand;
import org.usfirst.frc.team747.robot.subsystems.ExampleSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	public static OI mOI;
	Joystick rightDrive = new Joystick(1);
	Joystick leftDrive = new Joystick(0);
	public static TalonSRX leftFrontDrive = new TalonSRX(0);
	public static TalonSRX leftMiddleDrive = new TalonSRX(1);
	public static TalonSRX leftRearDrive = new TalonSRX(2);
	public static TalonSRX rightFrontDrive = new TalonSRX(3);
	public static TalonSRX rightMiddleDrive = new TalonSRX(4);
	public static TalonSRX rightRearDrive = new TalonSRX(5);
	public static int sleepTimer;
	public double SPEED = 29.8814933638;
	public double DISTANCE = 55;
	public double TIME = DISTANCE/SPEED;
	public int stopTimer = 0;
	public double autonomousSpeed = 0.25;
	Command mAutonomousCommand;
	SendableChooser<Command> mChooser = new SendableChooser<>();
	NetworkTable table;
	NetworkTableEntry tx;
	NetworkTableEntry tv;
	public static double x;// = 10;
	public static double v;// = 10;
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mOI = new OI();
		mChooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", mChooser);
		
		leftMiddleDrive.set(ControlMode.Follower, 0);
		leftRearDrive.set(ControlMode.Follower, 0);
		rightMiddleDrive.set(ControlMode.Follower, 3);
		rightRearDrive.set(ControlMode.Follower, 3);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		mAutonomousCommand = mChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (mAutonomousCommand != null) {
			mAutonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//TIME *= 50; // converts TIME to milliseconds and divides out the 20 ms in between running this
		//TIME *= (1/autonomousSpeed);
		//stopTimer++;
		//
		//if (stopTimer < TIME) {
		//	leftFrontDrive.set(ControlMode.PercentOutput, autonomousSpeed);
		//	leftRearDrive.set(ControlMode.PercentOutput, autonomousSpeed);
		//	rightFrontDrive.set(ControlMode.PercentOutput, autonomousSpeed);
		//	rightRearDrive.set(ControlMode.PercentOutput, autonomousSpeed);
		//} else {
		//	leftFrontDrive.set(ControlMode.PercentOutput, 0.0);
		//	leftRearDrive.set(ControlMode.PercentOutput, 0.0);
		//	rightFrontDrive.set(ControlMode.PercentOutput, 0.0);
		//	rightRearDrive.set(ControlMode.PercentOutput, 0.0);
		//}
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (mAutonomousCommand != null) {
			mAutonomousCommand.cancel();
		}
		leftMiddleDrive.set(ControlMode.PercentOutput, 0);
		leftRearDrive.set(ControlMode.PercentOutput, 0);
		rightMiddleDrive.set(ControlMode.PercentOutput, 0);
		rightRearDrive.set(ControlMode.PercentOutput, 0);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		double speedModifier;
					
		if(leftDrive.getRawButton(1) ^ rightDrive.getRawButton(1)) {
			speedModifier = 0.5;
		} else if (leftDrive.getRawButton(1) && rightDrive.getRawButton(1)) {
			speedModifier = 1;
		} else {
			speedModifier = 0.25;
		}
			
		if(rightDrive.getRawButton(2)) {
			speedModifier = 0;
		}
		
		if (rightDrive.getRawButton(4)) {
			this.table = NetworkTableInstance.getDefault().getTable("limelight");
			this.tx = this.table.getEntry("tx");
			this.tv = this.table.getEntry("tv");
			Robot.x = this.tx.getDouble(0);
			Robot.v = this.tv.getDouble(0);
			
			if (Robot.v != 1) {
				leftFrontDrive.set(ControlMode.PercentOutput, -1);
				rightFrontDrive.set(ControlMode.PercentOutput, -1);
			} else {
				if (Robot.x > 10) {
					leftFrontDrive.set(ControlMode.PercentOutput, -.7);
					rightFrontDrive.set(ControlMode.PercentOutput, -.7);
				} else if(Robot.x < -10) {
					leftFrontDrive.set(ControlMode.PercentOutput, .7);
					rightFrontDrive.set(ControlMode.PercentOutput, .7);
				} else {
					leftFrontDrive.set(ControlMode.PercentOutput, 0);
					rightFrontDrive.set(ControlMode.PercentOutput, 0);
				}
				//leftFrontDrive.set(ControlMode.PercentOutput, 0);
				//rightFrontDrive.set(ControlMode.PercentOutput, 0);
			}
			
			/*if (Robot.v == 1) {
				if (Robot.x < -5) {
					leftFrontDrive.set(ControlMode.PercentOutput, .5);
					rightFrontDrive.set(ControlMode.PercentOutput, .5);
				} else if(Robot.x > 5) {
					leftFrontDrive.set(ControlMode.PercentOutput, -.5);
					rightFrontDrive.set(ControlMode.PercentOutput, -.5);
				} else {
					leftFrontDrive.set(ControlMode.PercentOutput, 0);
					rightFrontDrive.set(ControlMode.PercentOutput, 0);
				}
			} else {
				leftFrontDrive.set(ControlMode.PercentOutput, .5);
				rightFrontDrive.set(ControlMode.PercentOutput, .5);
			}*/
		}
		
//		double rightJoystickValue = -rightDrive.getRawAxis(1)*speedModifier;	
//		double leftJoystickValue = leftDrive.getRawAxis(1)*speedModifier;	
				
//		leftFrontDrive.set(ControlMode.PercentOutput, leftJoystickValue);
//		leftRearDrive.set(ControlMode.PercentOutput, leftJoystickValue);
//		rightFrontDrive.set(ControlMode.PercentOutput, rightJoystickValue);
//		rightRearDrive.set(ControlMode.PercentOutput, rightJoystickValue);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
