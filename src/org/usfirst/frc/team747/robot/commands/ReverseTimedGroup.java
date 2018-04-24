package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReverseTimedGroup extends CommandGroup {

    public ReverseTimedGroup(double time) {
        addParallel(new IntakeTimedCommand(true, time));
//        addParallel(new EjectCommand(true));
        addParallel(new RollerTimedCommand(true, time));
    }
}
