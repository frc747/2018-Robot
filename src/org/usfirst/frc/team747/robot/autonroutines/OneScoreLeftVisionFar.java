package org.usfirst.frc.team747.robot.autonroutines;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommandWithVision;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommandVision;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneScoreLeftVisionFar extends CommandGroup {

    public OneScoreLeftVisionFar() {
    	addSequential(new PIDDriveInchesCommand(ValueConfig.DISTANCE_TO_SWITCH+40-RobotMap.robotLength, false));
    	addSequential(new PIDDriveRotateCommand(90));
    	//addSequential(new EjectAutonCommand(1);
    	
    	addSequential(new PIDDriveRotateCommandVision());
    	addSequential(new PIDDriveInchesCommandWithVision());
    	//addSequential(new IntakeAutonCommand(1);
    	
    	addSequential(new PIDDriveRotateCommand(-Robot.degreesRotated));
    	addSequential(new PIDDriveInchesCommand(Robot.inchesMoved, false));
    	//addSequential(new EjectAutonCommand(1);
    	
    }
}
