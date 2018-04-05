package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossAuto extends CommandGroup {

    public CrossAuto() {
        addSequential(new PIDDriveInchesCommand(150, true));
        
    }
}
