/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.swing.Spring;

import org.usfirst.frc.team747.robot.commands.DriveCommand;
import org.usfirst.frc.team747.robot.subsystems.CubeSubsystem;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem();
	public static OI m_oi;
	public static int sleepTimer;
	NetworkTable table;
	NetworkTableEntry tx;
	NetworkTableEntry tv;
	NetworkTableEntry ty; 
	public static double x;
	public static double v;
	public static double y;
	public static double distance;
	public static boolean switchb = false;

	//public static String gameData;
	public static Command autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public static CubeSubsystem cube = new CubeSubsystem();
	
    private static final AHRS NAV_X = new AHRS (SPI.Port.kMXP);
    public static double getNavXAngle() {
    	return NAV_X.getYaw();
    }
    
    public static double getNavXAngleRadians() {
    	return Math.toRadians(getNavXAngle());
    }
    
    public static void resetNavXAngle() {
    	NAV_X.zeroYaw();
    }
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
//		autoChooser = new SendableChooser<SelectAutonomousCommand>();
//		autoChooser.addObject("Robot Position 1", new SelectAutonomousCommand(1));
//		autoChooser.addObject("Robot Position 2", new SelectAutonomousCommand(2));
//		autoChooser.addObject("Robot Position 3", new SelectAutonomousCommand(3));
//		SmartDashboard.putData("Autonomous Position Chooser", autoChooser);
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
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
		//gameData = DriverStation.getInstance().getGameSpecificMessage();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		//autonomousCommand = (Command) autoChooser.getSelected();
		//autonomousCommand.start();
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		
		DriveSubsystem.talonDriveLeftPrimary.enableCurrentLimit(false);
		DriveSubsystem.talonDriveRightPrimary.enableCurrentLimit(false);
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		this.table = NetworkTableInstance.getDefault().getTable("limelight");
		this.tx = this.table.getEntry("tx");
		this.tv = this.table.getEntry("tv");
		this.ty = this.table.getEntry("ty");
		Robot.x = this.tx.getDouble(0);
		Robot.v = this.tv.getDouble(0);
		Robot.y = this.ty.getDouble(0);
		if (Robot.v == 1) {
			Robot.distance = (11-18)/Math.tan(Math.toRadians(-50+Robot.y));
		} else {
			Robot.distance = 0;
		}
		
		OI.degrees = Math.round(x);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
