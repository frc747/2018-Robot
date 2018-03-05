package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneCrossAutoLine extends CommandGroup {

    public OneCrossAutoLine() {
        addSequential(new PIDDriveInchesCommand(ValueConfig.DISTANCE_TO_SWITCH-RobotMap.robotLength, false));
    }
}
