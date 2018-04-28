package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooserVault extends CommandGroup {
	public AutoChooserVault() {

		if (Robot.gameData.length() > 0) {
			if (Robot.gameData.charAt(0) == 'L') {
				// Two Cube Auto for Left Side
			    addSequential(new CenterLeftSideVault());
			    SmartDashboard.putString("GameDataValue", "SwerveLeftAuto");
			    
			} else if (Robot.gameData.charAt(0) == 'R') {
				// Two Cube Auto for Right Side
			    addSequential(new CenterRightSideVault());
	            SmartDashboard.putString("GameDataValue", "SwerveRightAuto");
			}
		}
	}

}
