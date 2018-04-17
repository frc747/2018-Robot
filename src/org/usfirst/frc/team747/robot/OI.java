/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;


//import java.nio.channels.ShutdownChannelGroupException;

//import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
//import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

//import org.usfirst.frc.team747.robot.commands.EjectCommand;
import org.usfirst.frc.team747.robot.commands.ForwardGroup;
import org.usfirst.frc.team747.robot.commands.IntakeCommand;
import org.usfirst.frc.team747.robot.commands.PrintCommand;
import org.usfirst.frc.team747.robot.commands.ReverseGroup;
import org.usfirst.frc.team747.robot.commands.RollerCommand;
import org.usfirst.frc.team747.robot.commands.ShootGroup;
import org.usfirst.frc.team747.robot.commands.SolenoidHighGear;
import org.usfirst.frc.team747.robot.commands.SolenoidLowGear;
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
    
    public static boolean compBot = false;
    public static double leftV;
    public static double rightV;
    public static double i;
	public static Joystick leftStick = new Joystick(ControllerMap.Controller.DRIVER_LEFT.getValue()); //Driver Controller 1
	public static Joystick rightStick = new Joystick(ControllerMap.Controller.DRIVER_RIGHT.getValue()); //Driver Controller 2
	public static Joystick operatorController = new Joystick(ControllerMap.Controller.OPERATOR.getValue());
	public String highLow;
	public static int intLeft = 1;
	public static int intRight = 1;
	public static int extLeft = 1;
	public static int extRight = 1;
	public static int rol = 1;
	public static int intakeRightArm = 1;
	
	public static int robotLength;
	
	public static double PID_VALUE_P;
	public static double PID_VALUE_I;
	public static double PID_VALUE_D;
	public static double PID_VALUE_F;
	
	public static double latestAngleRadians;
	public static double latestDistanceDriven;
	public static double latestDiagonalDriven;
	
	// CREATING BUTTONS - OP == OPERATOR; DR == DRIVER
	Button OP_A = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_A.getValue());
	Button OP_B = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_B.getValue());
	Button OP_X = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_X.getValue());
	Button OP_Y = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_Y.getValue());
	Button OP_LB = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_LB.getValue());
	Button OP_RB = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_RB.getValue());
	Button OP_START = new JoystickButton(operatorController, ControllerMap.GamePad.BUTTON_START.getValue());
	Button OP_LEFT_STICK_PRESS = new JoystickButton(operatorController, ControllerMap.GamePad.STICK_LEFT.getValue());
	Button DR_LEFT_TRIGGER = new JoystickButton(leftStick, ControllerMap.Joystick.BUTTON_1.getValue());
	
	public OI() {
		new Notifier(() -> updateOI()).startPeriodic(.1);
		DR_LEFT_TRIGGER.whenPressed(new PrintCommand());
		OP_B.whileHeld(new RollerCommand(false));
		OP_Y.whileHeld(new ShootGroup());
		OP_A.whileHeld(new ReverseGroup());
		OP_X.whileHeld(new IntakeCommand(false));
		OP_RB.whileHeld(new ForwardGroup());
		OP_LB.whenPressed(new SolenoidLowGear());
		OP_LB.whenReleased(new SolenoidHighGear());
		SmartDashboard.putBoolean("Competition Robot?", compBot);

	    //OP_LEFT_STICK_PRESS.toggleWhenPressed(new PIDDriveRotateCommand(90));
        //OP_START.toggleWhenPressed(new PIDDriveInchesCommand(20, false));

	}
	
	public void updateOI() {
		highLow = (Robot.switchb)? "High" : "Low";
		SmartDashboard.putString("High or Low?", highLow);
	    SmartDashboard.putNumber("Left Encoder Position:", (Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition()/22118.4)*19.635);
		SmartDashboard.putNumber("Right Encoder Position:", (Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition()/22118.4)*19.635);
		SmartDashboard.putNumber("Current NavX Angle:", Robot.getNavXAngle());
		SmartDashboard.putNumber("Left Count", Robot.leftCount);
		SmartDashboard.putNumber("Right Count", Robot.rightCount);
		SmartDashboard.putNumber("Current Count", i);
		SmartDashboard.putNumber("Left Joystick POS", leftV);
		SmartDashboard.putNumber("Right Joystick POS", rightV);

		if(compBot) {
			
			intakeRightArm = 1;
			intLeft = 1;
			intRight = 1;
			extLeft = 1;
			extRight = -1;
			rol = -1;
			robotLength = 39;
			PID_VALUE_P = 0.5;
			PID_VALUE_I = 0.0;
			PID_VALUE_D = 0.1;
			PID_VALUE_F = 0.199;
		} else {
			
			intakeRightArm = -1;
			intLeft = -1;
			intRight = 1;
			extLeft = -1;
			extRight = 1;
			rol = -1;
			robotLength = 32;
	         PID_VALUE_P = 0.4;
	         PID_VALUE_I = 0.002;
	         PID_VALUE_D = 0.1;
	         PID_VALUE_F = 0.2031;
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
