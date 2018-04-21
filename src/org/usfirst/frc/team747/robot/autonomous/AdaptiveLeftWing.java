package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AdaptiveLeftWing extends CommandGroup {

    public AdaptiveLeftWing() {
    	
    	if(Robot.gameData.charAt(0) == 'L') {
        addSequential(new PIDDriveInchesCommand(146, true));
        addSequential(new PIDDriveRotateCommand(90));
        addSequential(new PIDDriveInchesCommand(18, true));
        addSequential(new EjectTimedCommand(false, 1.0));
        
    	} else if(Robot.gameData.charAt(0) == 'R'){
    	addSequential(new PIDDriveInchesCommand(146, true));
        addSequential(new PIDDriveRotateCommand(-180));
    	}
    }
    
}
