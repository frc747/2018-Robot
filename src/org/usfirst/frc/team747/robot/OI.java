/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;


import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommandVision;
import org.usfirst.frc.team747.robot.commands.TestCommandGroup;

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
	private double inches;
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	private static Joystick operatorController = new Joystick(2);
	public static double degrees;
	Button left = new JoystickButton(leftStick, 3);
	Button right = new JoystickButton(leftStick, 4);

	
	public OI() {
		left.toggleWhenPressed(new PIDDriveRotateCommandVision());
	//right.toggleWhenPressed(new TestCommandGroup(3));
		new Notifier(() -> updateOI()).startPeriodic(.1);
	}
	
	public void updateOI() {
	    SmartDashboard.putNumber("Value of TX:", getDegrees());
	    SmartDashboard.putNumber("Value of TV:", Robot.v);

	}
	public static double getDegrees() {
		return OI.degrees;
	}
}
