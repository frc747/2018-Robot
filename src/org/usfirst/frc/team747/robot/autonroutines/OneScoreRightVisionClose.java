package org.usfirst.frc.team747.robot.autonroutines;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneScoreRightVisionClose extends CommandGroup {

    public OneScoreRightVisionClose() {
        addSequential(new PIDDriveInchesCommand(60-RobotMap.robotLength, false));
        addSequential(new PIDDriveRotateCommand(-90));
        addSequential(new PIDDriveInchesCommand(35-(RobotMap.robotLength/2), false));
        addSequential(new PIDDriveRotateCommand(90));
        addSequential(new PIDDriveInchesCommand(ValueConfig.DISTANCE_TO_SWITCH-60, false));
        //addSequential(new EjectAutonCommand(1));
    }
}
