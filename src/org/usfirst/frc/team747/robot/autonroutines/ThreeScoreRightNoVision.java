package org.usfirst.frc.team747.robot.autonroutines;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeScoreRightNoVision extends CommandGroup {

    public ThreeScoreRightNoVision() {
    	addSequential(new PIDDriveInchesCommand(ValueConfig.DISTANCE_TO_SWITCH+36-RobotMap.robotLength, false));
        addSequential(new PIDDriveRotateCommand(-90));
        //addSequential(new EjectAutonCommand(1));
    }
}
