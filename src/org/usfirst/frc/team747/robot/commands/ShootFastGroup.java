package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootFastGroup extends CommandGroup {

    public ShootFastGroup() {
        addParallel(new IntakeCommand(false));
        addParallel(new EjectFastCommand(false));
    }
}
