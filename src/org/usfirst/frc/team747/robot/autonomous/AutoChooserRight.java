package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoChooserRight extends CommandGroup {
	public AutoChooserRight() {
		
		
		if(Robot.gameData.length() > 0) {
			if(Robot.gameData.charAt(0) == 'L') {
			
			} else if(Robot.gameData.charAt(0) == 'R'){
			
			}
		}
	}
	
	
}
