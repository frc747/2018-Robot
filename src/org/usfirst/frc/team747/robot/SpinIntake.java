package org.usfirst.frc.team747.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SpinIntake {
	static TalonSRX intake = new TalonSRX(9);
	
	public static void spinIntake() {
		intake.set(ControlMode.PercentOutput, 1);
	}
	
	public static void stopSpinIntake() {
		intake.set(ControlMode.PercentOutput, 0);
	}
}
