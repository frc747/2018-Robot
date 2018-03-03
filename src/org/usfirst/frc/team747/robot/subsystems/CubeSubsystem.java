package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.OI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeSubsystem extends Subsystem {

	public static TalonSRX intakeArmLeft = new TalonSRX(3); // = new TalonSRX(?); 4
	public static TalonSRX intakeArmRight = new TalonSRX(8); 
	
	public static TalonSRX intakeLeft = new TalonSRX(4); // = new TalonSRX(?); 5
	public static TalonSRX intakeRight = new TalonSRX(7); // = new TalonSRX(?); 6 // 7 is reversed
	
	public static TalonSRX ejectLeft = new TalonSRX(5); // = new TalonSRX(?); 7
	public static TalonSRX ejectRight = new TalonSRX(6); // = new TalonSRX(?); 8
	
	public static TalonSRX roller = new TalonSRX(11);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public CubeSubsystem() {
		
	}
	
	public void intakeArms(double left, double right) {
		//TODO Based on a conversation with (Jeff and George 3/3/2018 2:35), the speeds for intake motors while being controlled by joysticks are unknown. Setting to 75% speeds by default.
		intakeLeft.set(ControlMode.PercentOutput, (left/4)*3);
		intakeRight.set(ControlMode.PercentOutput, (-right/4)*3);
		roller.set(ControlMode.PercentOutput, (left+right)/2);
	}
	
	public void setIntake(boolean enable, boolean reverse) {
		int mod;
		if (reverse == true) {
			mod = -1;
		} else {
			mod = 1;
		}
		if (enable) {
			intakeLeft.set(ControlMode.PercentOutput, -.5*mod);
			intakeRight.set(ControlMode.PercentOutput, .5*mod);
			roller.set(ControlMode.PercentOutput, -.5*mod);
		} else {
			intakeLeft.set(ControlMode.PercentOutput, 0);
			intakeRight.set(ControlMode.PercentOutput, 0);
			roller.set(ControlMode.PercentOutput, 0);
			
		}
	}
	
	public void setEject(boolean enable, boolean reverse) {
		int mod;
		if (reverse == true) {
			mod = -1;
		} else {
			mod = 1;
		}
		if (enable) {
			ejectLeft.set(ControlMode.PercentOutput, -.5*mod);
			ejectRight.set(ControlMode.PercentOutput, .5*mod);
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

