package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoChooserLeft extends CommandGroup {
	public AutoChooserLeft() {
		
		
		if(Robot.gameData.length() > 0) {
			if(Robot.gameData.charAt(0) == 'L') {
			addSequential(new PIDDriveInchesCommand(164-(RobotMap.robotLength/2), true));
			addSequential(new PIDDriveRotateCommand(90));
			addSequential(new PIDDriveInchesCommand(37.5, true));
			addSequential(new EjectTimedCommand(false, 0.5));
			} else if(Robot.gameData.charAt(0) == 'R'){
			
			}
		}
	}
	
	
}
