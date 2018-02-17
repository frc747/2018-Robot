package org.usfirst.frc.team747.robot.autonroutines;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.RotateToCubeCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.maps.ValueConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;



public class OneScoreLeftVisionClose extends CommandGroup {

    public OneScoreLeftVisionClose() {
    	double distanceToCube;
    	addSequential(new PIDDriveInchesCommand(60-RobotMap.robotLength, false));
        addSequential(new PIDDriveRotateCommand(90));
        addSequential(new PIDDriveInchesCommand(48.75-(RobotMap.robotLength/2), false));
        addSequential(new PIDDriveRotateCommand(-90));
        addSequential(new PIDDriveInchesCommand(ValueConfig.DISTANCE_TO_SWITCH-60, false));
        //addSequential(new EjectCubeCommand(1));
        addSequential(new PIDDriveInchesCommand(55, true));
        addSequential(new PIDDriveRotateCommand(-135));
        addSequential(new PIDDriveInchesCommand(10, false));
        addSequential(new RotateToCubeCommand());
        //addSequential(new PIDDriveInchesCommand(distanceToCube, false));
        //addSequential(new IntakeCubeCommand));h
        
        //addSequential(new PIDDriveInchesCommand(distanceToCube, true));
        addSequential(new PIDDriveInchesCommand(10, true));
        addSequential(new PIDDriveRotateCommand(135));
        addSequential(new PIDDriveInchesCommand(55, false));
        //addSequential(new EjectCubeCommand(1));
        
    }
}
