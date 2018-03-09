/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;


//import java.nio.channels.ShutdownChannelGroupException;

import org.usfirst.frc.team747.robot.commands.EjectCommand;
import org.usfirst.frc.team747.robot.commands.ForwardGroup;
import org.usfirst.frc.team747.robot.commands.IntakeCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.ReverseGroup;
import org.usfirst.frc.team747.robot.commands.SolenoidSwitch;
import org.usfirst.frc.team747.robot.commands.SolenoidSwitchToggle;
import org.usfirst.frc.team747.robot.maps.ControllerMap;

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
	public static Joystick leftStick = new Joystick(ControllerMap.Controller.DRIVER_LEFT.getValue()); //Driver Controller 1
	public static Joystick rightStick = new Joystick(ControllerMap.Controller.DRIVER_RIGHT.getValue()); //Driver Controller 2
	public static Joystick operatorController = new Joystick(ControllerMap.Controller.OPERATOR.getValue());
	public String highLow;
	public static boolean compBot = true;
	public static int intLeft = 1;
	public static int intRight = 1;
	public static int extLeft = 1;
	public static int extRight = 1;
	public static int rol = 1;
	
	// CREATING BUTTONS - OP == OPERATOR; DR == DRIVER
	Button OP_A = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_A.getValue());
	Button OP_B = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_B.getValue());
	Button OP_X = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_X.getValue());
	Button OP_Y = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_Y.getValue());
	public static Button OP_START = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_START.getValue());
	Button OP_LEFT_STICK_PRESS = new JoystickButton(operatorController, ControllerMap.GamePad.STICK_LEFT.getValue());
	Button DR_LEFT_TRIGGER = new JoystickButton(leftStick, ControllerMap.Joystick.BUTTON_1.getValue());

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
		OP_X.whileHeld(new IntakeCommand(false));
		//OP_START.toggleWhenPressed(new SolenoidSwitchToggle());
		//OP_LEFT_STICK_PRESS.toggleWhenPressed(new PIDDriveRotateCommand(90));
		//OP_START.toggleWhenPressed(new PIDDriveInchesCommand(20, false));
		SmartDashboard.putBoolean("Competition Robot?", compBot);

	}
	
	public void updateOI() {
		highLow = (Robot.switchb)? "High" : "Low";
		SmartDashboard.putString("High or Low?", highLow);
	    SmartDashboard.putNumber("Left Encoder Position:", (Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition()/22118.4)*19.635);
		SmartDashboard.putNumber("Right Encoder Position:", (Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition()/22118.4)*19.635);
		SmartDashboard.putNumber("Current NavX Angle:", Robot.getNavXAngle());
		
		if(compBot) {
			
			
			intLeft = 1;
			intRight = 1;
			extLeft = 1;
			extRight = -1;
			rol = -1;
		} else {
			
			intLeft = -1;
			intRight = 1;
			extLeft = -1;
			extRight = 1;
			rol = 1;
		}
		/*
		SmartDashboard.putNumber("kP", OI.kP);
		SmartDashboard.putNumber("kI", OI.kI);
		SmartDashboard.putNumber("kD", OI.kD);
		OI.kP = SmartDashboard.getNumber("kP", OI.kP);
		OI.kI = SmartDashboard.getNumber("kI", OI.kI);
		OI.kD = SmartDashboard.getNumber("kD", OI.kD);
		*/
		
		
		
		////POTENTIAL DIAGNOSTICS PRINTOUTS
		/*
		SmartDashboard.putNumber("Left Encoder Position:", Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition());
	    SmartDashboard.putNumber("Right Encoder Position:", Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition());

	    SmartDashboard.putNumber("Left Position (Inches):", Robot.DRIVE_SUBSYSTEM.convertRevsToInches(Robot.DRIVE_SUBSYSTEM.convertTicksToRevs(Robot.DRIVE_SUBSYSTEM.getLeftPosition())));
	    SmartDashboard.putNumber("Right Position (Inches):", Robot.DRIVE_SUBSYSTEM.convertRevsToInches(Robot.DRIVE_SUBSYSTEM.convertTicksToRevs(Robot.DRIVE_SUBSYSTEM.getRightPosition())));

	    SmartDashboard.putNumber("NavX Angle:", Robot.getNavXAngle());
		
		
		SmartDashboard.putNumber("Left Joystick:", -OI.leftStick.getRawAxis(ControllerMap.Joystick.AXIS_Y.getValue()));
	    SmartDashboard.putNumber("Right Joystick:", -OI.rightStick.getRawAxis(ControllerMap.Joystick.AXIS_Y.getValue()));
	    SmartDashboard.putString("Left Talon Mode:", Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.getControlMode().toString());
	    SmartDashboard.putString("Right Talon Mode:", Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.getControlMode().toString());
	    SmartDashboard.putNumber("Left Talon Percent Output:", Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.getMotorOutputPercent());
	    SmartDashboard.putNumber("Left Talon Voltage Output:", Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.getMotorOutputVoltage());
	    SmartDashboard.putNumber("Right Talon Percent Output:", Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.getMotorOutputPercent());
	    SmartDashboard.putNumber("Right Talon Voltage Output:", Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.getMotorOutputVoltage());
        */
	}
}
