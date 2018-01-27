/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.commands.DriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.command.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static TalonSRX leftFrontDrive = new TalonSRX(0),
			   			   leftMiddleDrive = new TalonSRX(1),
			   			   leftRearDrive = new TalonSRX(2),
			   			   rightFrontDrive = new TalonSRX(3),
			   			   rightMiddleDrive = new TalonSRX(4),
			   			   rightRearDrive = new TalonSRX(5);
	public static void setMotorSpeed(double speedMod) {
		leftFrontDrive.set(ControlMode.PercentOutput, speedMod);
		leftMiddleDrive.set(ControlMode.Follower, leftFrontDrive.getDeviceID());
		leftRearDrive.set(ControlMode.Follower, leftFrontDrive.getDeviceID());
		rightFrontDrive.set(ControlMode.PercentOutput, speedMod);
		rightMiddleDrive.set(ControlMode.Follower, rightFrontDrive.getDeviceID());
		rightRearDrive.set(ControlMode.Follower, rightFrontDrive.getDeviceID());
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}
}
