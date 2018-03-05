package org.usfirst.frc.team747.robot.autonomous;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OneScoreRightNoVision extends CommandGroup {

    public OneScoreRightNoVision() {
    	addSequential(new PIDDriveInchesCommand(90-RobotMap.robotLength, true));
    	addSequential(new PIDDriveRotateCommand(90));
    	addSequential(new PIDDriveInchesCommand(154+(RobotMap.robotLength/2), false));
    	addSequential(new PIDDriveRotateCommand(-90));
    	addSequential(new PIDDriveInchesCommand(50, false));
        //addSequential(new EjectCubeCommand(1));
    }
}
