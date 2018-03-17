package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootGroup extends CommandGroup {

    public ShootGroup() {
        addParallel(new IntakeCommand(false));
        addParallel(new EjectCommand(false));
    }
}
