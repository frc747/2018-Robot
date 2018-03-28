package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooserCenter extends CommandGroup {
	public AutoChooserCenter() {

		if (Robot.gameData.length() > 0) {
			if (Robot.gameData.charAt(0) == 'L') {
				// Snake Auto for Left Side
			    addSequential(new CenterLeftSideSwitch());
			    SmartDashboard.putString("GameDataValue", "SnakeLeftAuto");
			    
			} else if (Robot.gameData.charAt(0) == 'R') {
				// Snake Auto for Right Side
			    addSequential(new CenterRightSideSwitch());
	            SmartDashboard.putString("GameDataValue", "SnakeRightAuto");
			}
		}
	}

}
