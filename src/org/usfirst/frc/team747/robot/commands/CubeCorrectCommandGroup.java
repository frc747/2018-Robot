package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CubeCorrectCommandGroup extends CommandGroup {

    public CubeCorrectCommandGroup(double arms, double tunnel) {
    	addSequential(new ForwardTimedGroup(1.5));
    	addSequential(new ForwardTimedGroup(tunnel));
        
    }
}
