package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoChooserCenter extends CommandGroup {
	public AutoChooserCenter() {

		if (Robot.gameData.length() > 0) {
			if (Robot.gameData.charAt(0) == 'L') {
				// Snake Auto for Left Side
				addSequential(new PIDDriveInchesCommand(90 - RobotMap.robotLength, true));
				addSequential(new PIDDriveRotateCommand(-90));
				addSequential(new PIDDriveInchesCommand(81 - RobotMap.robotLength, true));
				addSequential(new PIDDriveRotateCommand(90));
				addSequential(new PIDDriveInchesCommand(45, true));
				addSequential(new EjectTimedCommand(false, 0.5));
			} else if (Robot.gameData.charAt(0) == 'R') {
				// Snake Auto for Right Side
				addSequential(new PIDDriveInchesCommand(90 - RobotMap.robotLength, true));
				addSequential(new PIDDriveRotateCommand(90));
				addSequential(new PIDDriveInchesCommand(81 - RobotMap.robotLength, true));
				addSequential(new PIDDriveRotateCommand(-90));
				addSequential(new PIDDriveInchesCommand(45, true));
				addSequential(new EjectTimedCommand(false, 0.5));
			}
		}
	}

}
