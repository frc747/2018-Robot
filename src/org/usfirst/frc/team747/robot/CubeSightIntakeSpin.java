package org.usfirst.frc.team747.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

@SuppressWarnings("deprecation")
public class CubeSightIntakeSpin {
	static NetworkTable table = NetworkTable.getTable("limelight");
	static double targetArea;
	
	public static void detectCube() {
		targetArea = table.getNumber("ta", 0);
		if(targetArea >= 50 && targetArea <= 65) {
			SpinIntake.spinIntake();
		} else {
			SpinIntake.stopSpinIntake();
		}
	}
}
