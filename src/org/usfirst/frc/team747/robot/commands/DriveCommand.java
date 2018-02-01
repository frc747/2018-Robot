package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command{
	

	private double speedMod; // A modifier to allow for different speeds.
	public DriveCommand() {
		requires(Robot.DRIVE_SUBSYSTEM);
		
	}
	protected void initalize() {
		System.out.println("Initialized DriveCommand.");
	}
	protected void execute() {
		if(OI.leftStick.getRawButton(1) || OI.rightStick.getRawButton(1)) {
			speedMod = 1;
		} else {
			speedMod = 0.5;
		}
		
		
		DriveSubsystem.setMotorSpeed(speedMod);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
