package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardTimedGroup extends CommandGroup {

    public ForwardTimedGroup(double time) {
        addParallel(new IntakeTimedCommand(false, time));
//        addParallel(new EjectCommand(false));
        addParallel(new RollerTimedCommand(false, time));
    }
}
