package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ForwardSlowTimedGroup extends CommandGroup {

    public ForwardSlowTimedGroup(double time) {
        addParallel(new IntakeSlowTimedCommand(false, time));
//        addParallel(new EjectCommand(false));
        addParallel(new RollerTimedCommand(false, time));
    }
}
