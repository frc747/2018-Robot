package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesForDiagonalCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PauseCommand;

public class CenterLeftSideSwitchDiagonal extends CommandGroup {
    
    public  CenterLeftSideSwitchDiagonal() {
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(10, true));
        addSequential(new PIDDriveRotateCommand(-39));
        addSequential(new PIDDriveInchesForDiagonalCommand("left" , "diagonal"));
        addSequential(new PIDDriveRotateCommand(0));
        addSequential(new PIDDriveInchesCommand(14, true));
        addSequential(new EjectTimedCommand(false, 0.5));
    }
}

/*
addSequential(new PIDDriveInchesCommand(10, true));
addSequential(new PIDDriveRotateCommand(-30));
addSequential(new PIDDriveInchesCommand(90, true));
addSequential(new PIDDriveRotateCommand(0));
addSequential(new PIDDriveInchesCommand(15, true));
addSequential(new EjectTimedCommand(false, 0.5));
*/