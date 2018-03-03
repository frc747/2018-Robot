/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;


import java.nio.channels.ShutdownChannelGroupException;

import org.usfirst.frc.team747.robot.commands.EjectCommand;
import org.usfirst.frc.team747.robot.commands.ForwardGroup;
import org.usfirst.frc.team747.robot.commands.IntakeCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommandVision;
import org.usfirst.frc.team747.robot.commands.ReverseGroup;
import org.usfirst.frc.team747.robot.commands.SolenoidSwitch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static double inches = 20;
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick operatorController = new Joystick(2);
	public static double degrees;
	public static double PIDdegrees = 90;
	public String highLow;
	Button OP_A = new JoystickButton(operatorController, 1);
	Button OP_B = new JoystickButton(operatorController, 2);
	Button OP_X = new JoystickButton(operatorController, 3);
	Button OP_Y = new JoystickButton(operatorController, 4);
	Button OP_START = new JoystickButton(operatorController, 8);
	Button OP_PRESS = new JoystickButton(operatorController, 9);
	Button LEFT_TRIGGER = new JoystickButton(leftStick, 1);

	public static double kP = 0;
	public static double kI = 0;
	public static double kD = 0;
	
	
	public OI() {
		new Notifier(() -> updateOI()).startPeriodic(.1);
		
		/*
		OP_A.whileHeld(new IntakeCommand(false));
		OP_B.whileHeld(new EjectCommand(false));
		OP_X.toggleWhenPressed(new PIDDriveRotateCommand(-180));
		OP_Y.toggleWhenPressed(new PIDDriveInchesCommand(Robot.distance, false));
		OP_START.toggleWhenPressed(new PIDDriveRotateCommand(OI.getDegrees()));
		*/
		
		OP_Y.whileHeld(new ForwardGroup());
		OP_A.whileHeld(new ReverseGroup());
		//OP_X.whileHeld(new IntakeCommand(false));
		//OP_PRESS.toggleWhenPressed(new PIDDriveRotateCommand(OI.PIDdegrees));
		//OP_START.toggleWhenPressed(new PIDDriveInchesCommand(inches, false));
	}
	
	public void updateOI() {
		inches = 20.1;
		highLow = (Robot.switchb)? "High" : "Low";
		SmartDashboard.putString("High or Low?", highLow);
	    SmartDashboard.putNumber("Value of TX:", getDegrees());
	    SmartDashboard.putNumber("Value of TV:", Robot.v);
	    SmartDashboard.putNumber("Distance:", Robot.distance);
	    SmartDashboard.putNumber("Left Encoder Position:", (Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition()/22118.4)*19.635);
		SmartDashboard.putNumber("Right Encoder Position:", (Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition()/22118.4)*19.635);
		SmartDashboard.putNumber("Inches", inches);
		SmartDashboard.putNumber("Error", Robot.getNavXAngle()-OI.PIDdegrees);
		SmartDashboard.putNumber("Current", Robot.getNavXAngle());
		SmartDashboard.putNumber("Degrees", OI.PIDdegrees);
		/*
		SmartDashboard.putNumber("kP", OI.kP);
		SmartDashboard.putNumber("kI", OI.kI);
		SmartDashboard.putNumber("kD", OI.kD);
		OI.kP = SmartDashboard.getNumber("kP", OI.kP);
		OI.kI = SmartDashboard.getNumber("kI", OI.kI);
		OI.kD = SmartDashboard.getNumber("kD", OI.kD);
		*/
		inches = 20;
	}
	public static double getDegrees() {
		return OI.degrees;
	}
}
