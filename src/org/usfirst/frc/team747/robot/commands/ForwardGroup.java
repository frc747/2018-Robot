package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardGroup extends CommandGroup {

    public ForwardGroup() {
        addParallel(new IntakeCommand(false));
        addParallel(new EjectCommand(false));
    }
}
