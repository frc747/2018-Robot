package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReverseGroup extends CommandGroup {

    public ReverseGroup() {
    	addParallel(new IntakeCommand(true));
        addParallel(new EjectCommand(true));
    }
}
