package org.usfirst.frc.team747.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class Drive {
	static Joystick rightDrive = new Joystick(1);
	static Joystick leftDrive = new Joystick(0);
	static Joystick operator = new Joystick(2);
	//public static TalonSRX leftRearDrive = new TalonSRX(2);
	//public static TalonSRX rightRearDrive = new TalonSRX(5);
	private static TalonSRX intake = new TalonSRX(9);
	private static TalonSRX index = new TalonSRX(8);
	private static TalonSRX left1 = new TalonSRX(4);
	private static TalonSRX left2 = new TalonSRX(5);
	private static TalonSRX right1 = new TalonSRX(6);
	private static TalonSRX right2 = new TalonSRX(7);

	
	public static void drive() {
		double speedModifier;
		
	
			
		
			speedModifier = 1.0;
	
		
		if(operator.getRawButton(1)) {
			intake.set(ControlMode.PercentOutput, 1.0);
		} else if(operator.getRawButton(3)){
			intake.set(ControlMode.PercentOutput, -1.0);

		} else {
			intake.set(ControlMode.PercentOutput, 0);

		}
		if(operator.getRawButton(2)) {
			index.set(ControlMode.PercentOutput, 1.0);
			left1.set(ControlMode.PercentOutput, -1.0);
			left2.set(ControlMode.PercentOutput, -1.0);
			right1.set(ControlMode.PercentOutput, 1.0);
			right2.set(ControlMode.PercentOutput, 1.0);

		} else {
			index.set(ControlMode.PercentOutput, 0);
			left1.set(ControlMode.PercentOutput, 0);
			left2.set(ControlMode.PercentOutput, 0);
			right1.set(ControlMode.PercentOutput, 0);
			right2.set(ControlMode.PercentOutput, 0);

		}
		if(operator.getRawButton(4)) {
			index.set(ControlMode.PercentOutput, -1.0);
		} 
		
		
		//CubeSightIntakeSpin.detectCube();

			
		double rightJoystickValue = -rightDrive.getRawAxis(1)*speedModifier;
		double leftJoystickValue = leftDrive.getRawAxis(1)*speedModifier;
		
		Robot.leftFrontDrive.set(ControlMode.PercentOutput, leftJoystickValue);
		Robot.leftRearDrive.set(ControlMode.PercentOutput, leftJoystickValue);

		
		//leftRearDrive.set(ControlMode.PercentOutput, leftJoystickValue);
		Robot.rightFrontDrive.set(ControlMode.PercentOutput, rightJoystickValue);
		Robot.rightRearDrive.set(ControlMode.PercentOutput, rightJoystickValue);

		//rightRearDrive.set(ControlMode.PercentOutput, rightJoystickValue);
	}
}
