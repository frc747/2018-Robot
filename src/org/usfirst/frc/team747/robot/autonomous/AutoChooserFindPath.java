package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.FindPathCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoChooserFindPath extends CommandGroup {

    public AutoChooserFindPath() {
    	if (Robot.gameData.length() > 0) {
			if (Robot.gameData.charAt(0) == 'L') {
				// Diagonal Auto for Left Side
			    addSequential(new FindPathCommand("centerSwitchLeft"));
			    SmartDashboard.putString("GameDataValue", "MPLeftAuto");
			    
			} else if (Robot.gameData.charAt(0) == 'R') {
				// Diagonal Auto for Right Side
			    addSequential(new FindPathCommand("centerSwitchRight"));
	            SmartDashboard.putString("GameDataValue", "MPRightAuto");
			}
		}
    }
}
