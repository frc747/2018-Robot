package org.usfirst.frc.team747.robot.autonroutines;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TwoCrossAutoLine extends CommandGroup {

    public TwoCrossAutoLine() {
        addSequential(new PIDDriveInchesCommand(ValueConfig.DISTANCE_TO_SWITCH-RobotMap.robotLength, false));
    }
}
