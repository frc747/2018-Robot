package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooserDiagonalCenter extends CommandGroup {
	public AutoChooserDiagonalCenter() {

		if (Robot.gameData.length() > 0) {
			if (Robot.gameData.charAt(0) == 'L') {
				// Diagonal Auto for Left Side
			    addSequential(new CenterLeftSideSwitchDiagonal());
			    SmartDashboard.putString("GameDataValue", "DiagonalLeftAuto");
			    
			} else if (Robot.gameData.charAt(0) == 'R') {
				// Diagonal Auto for Right Side
			    addSequential(new CenterRightSideSwitchDiagonal());
	            SmartDashboard.putString("GameDataValue", "DiagonalRightAuto");
			}
		}
	}

}
