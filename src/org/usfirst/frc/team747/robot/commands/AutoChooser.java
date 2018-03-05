package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoChooser extends CommandGroup {
	String gameData;
	public AutoChooser() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		
		if(gameData.charAt(0) == 'L' ) {
			
		} else {
			
		}
	}
	
	
}
