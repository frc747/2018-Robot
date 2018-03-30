package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.commands.JoystickMappingCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossAuto extends CommandGroup {

    public CrossAuto() {
        addSequential(new PIDDriveInchesCommand(150, true));
        
    }
}
