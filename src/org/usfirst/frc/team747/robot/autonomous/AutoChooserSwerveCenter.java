package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.AutonomousPlayback;
import org.usfirst.frc.team747.robot.commands.BTMacroPlayCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooserSwerveCenter extends CommandGroup {
	public AutoChooserSwerveCenter() {

		if (Robot.gameData.length() > 0) {
			if (Robot.gameData.charAt(0) == 'L') {
				// Two Cube Auto for Left Side
			    //addSequential(new CenterLeftSideSwitchSwerve());
				addSequential(new BTMacroPlayCommand());
			    SmartDashboard.putString("GameDataValue", "SwerveLeftAuto");
			    
			} else if (Robot.gameData.charAt(0) == 'R') {
				// Two Cube Auto for Right Side
			    addSequential(new CenterRightSideSwitchSwerve());
	            SmartDashboard.putString("GameDataValue", "SwerveRightAuto");
			}
		}
	}

}
