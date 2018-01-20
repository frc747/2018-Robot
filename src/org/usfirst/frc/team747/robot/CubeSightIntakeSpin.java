package org.usfirst.frc.team747.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

@SuppressWarnings("deprecation")
public class CubeSightIntakeSpin {
	static NetworkTable table = NetworkTable.getTable("limelight");
	static double targetArea;
	static boolean zeroSwitch = false;
	
	public static void detectCube() {
		targetArea = table.getNumber("ta", 0);
		
		if(!zeroSwitch) {
			if(targetArea != 0) {
			zeroSwitch = true;
			}
		}
		
		
		if(zeroSwitch) {
			if(targetArea == 0 || (targetArea < 100 && targetArea > 99.7) || targetArea > 35) {
				SpinIntake.spinIntake();
				zeroSwitch = true;
			} else {
				SpinIntake.stopSpinIntake();
				zeroSwitch = false;
			}
		}
		
		if(zeroSwitch) {
			if(targetArea < 35 && targetArea != 0) {
				zeroSwitch = false;
				SpinIntake.stopSpinIntake();
			}
			
			
		if(!zeroSwitch) {
			if(targetArea == 0) {
				SpinIntake.stopSpinIntake();
			}
			}
		}
		
		
	}
}
