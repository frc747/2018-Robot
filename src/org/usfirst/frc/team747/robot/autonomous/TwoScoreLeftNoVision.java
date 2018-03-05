package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TwoScoreLeftNoVision extends CommandGroup {

    public TwoScoreLeftNoVision() {
    	addSequential(new PIDDriveInchesCommand(90-RobotMap.robotLength, false));
    	addSequential(new PIDDriveRotateCommand(-90));
    	addSequential(new PIDDriveInchesCommand(81+(RobotMap.robotLength/2), false));
    	addSequential(new PIDDriveRotateCommand(90));
    	addSequential(new PIDDriveInchesCommand(50, false));

        //addSequential(new EjectCubeCommand(1));


    }
}

