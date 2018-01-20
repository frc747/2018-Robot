package org.usfirst.frc.team747.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

@SuppressWarnings("deprecation")
public class CubeSightIntakeSpin {
	static NetworkTable table = NetworkTable.getTable("limelight");
	static double targetArea;
	
	public static void detectCube() {
		targetArea = table.getNumber("ta", 0);
		if(targetArea >= 20 && targetArea <= 35) {
			SpinIntake.spinIntake();
		} else {
			SpinIntake.stopSpinIntake();
		}
	}
}
