package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command{
	Solenoid leftS = new Solenoid(0);
	Solenoid rightS = new Solenoid(1);
	public boolean test = (OI.leftStick.getRawAxis(1) > 0.3 && OI.leftStick.getRawAxis(1) < -0.3) || (OI.rightStick.getRawAxis(1) > 0.3 && OI.rightStick.getRawAxis(1) < -0.3);
	public boolean switchb = false;
	private double speedMod; // A modifier to allow for different speeds.
	public DriveCommand() {
		requires(Robot.DRIVE_SUBSYSTEM);
		
	}
	protected void initalize() {
		System.out.println("Initialized DriveCommand.");
	}
	protected void execute() {
		
		//Solenoid Switch
		if(OI.operatorController.getRawButton(4)) {
			switchb = !switchb;
		} else if(test) {
			switchb = true;
		} else {
			switchb = false;
		}
		
		if(switchb) {
			leftS.set(true);
			rightS.set(true);
		} else {
			leftS.set(true);
			rightS.set(true);

		}
		
		DriveSubsystem.setMotorSpeed(1);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
