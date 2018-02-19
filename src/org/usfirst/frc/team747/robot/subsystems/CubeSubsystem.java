package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.OI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeSubsystem extends Subsystem {

	public static TalonSRX intakeRollerLeft = new TalonSRX(3); // = new TalonSRX(?); 4
	public static TalonSRX intakeRollerRight = new TalonSRX(8); 
	
	public static TalonSRX intakeLeft = new TalonSRX(4); // = new TalonSRX(?); 5
	public static TalonSRX intakeRight = new TalonSRX(7); // = new TalonSRX(?); 6
	
	public static TalonSRX ejectLeft = new TalonSRX(5); // = new TalonSRX(?); 7
	public static TalonSRX ejectRight = new TalonSRX(6); // = new TalonSRX(?); 8
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public CubeSubsystem() {
		
	}
	
	public void setIntake(boolean enable) {
		if (enable) {
			intakeRollerLeft.set(ControlMode.PercentOutput, .5);
			intakeRollerRight.set(ControlMode.PercentOutput, .5);
			intakeLeft.set(ControlMode.PercentOutput, .5);
			intakeRight.set(ControlMode.PercentOutput, -.5);
		} else {
			intakeRollerLeft.set(ControlMode.PercentOutput, 0);
			intakeRollerRight.set(ControlMode.PercentOutput, 0);
			intakeLeft.set(ControlMode.PercentOutput, 0);
			intakeRight.set(ControlMode.PercentOutput, 0);
			
		}
	}
	
	public void setEject(boolean enable) {
		if (enable) {
			ejectLeft.set(ControlMode.PercentOutput, .5);
			ejectRight.set(ControlMode.PercentOutput, -.5);
		} else {
			ejectLeft.set(ControlMode.PercentOutput, 0);
			ejectRight.set(ControlMode.PercentOutput, 0);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

