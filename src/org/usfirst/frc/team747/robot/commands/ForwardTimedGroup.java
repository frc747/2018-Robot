package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardTimedGroup extends CommandGroup {

    public ForwardTimedGroup() {
        addParallel(new IntakeTimedCommand(false, 3));
//        addParallel(new EjectCommand(false));
        addParallel(new RollerTimedCommand(false, 3));
    }
}
