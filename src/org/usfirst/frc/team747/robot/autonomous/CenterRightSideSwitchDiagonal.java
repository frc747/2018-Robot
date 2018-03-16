package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.IntakeTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesForDiagonalCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

public class CenterRightSideSwitchDiagonal extends CommandGroup {
    
    public  CenterRightSideSwitchDiagonal() {
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(10, true));
        addSequential(new PIDDriveRotateCommand(36));
        addSequential(new PIDDriveInchesForDiagonalCommand("right" , "diagonal"));
        addSequential(new PIDDriveRotateCommand(0));
        addSequential(new PIDDriveInchesCommand(17.5, true));
        addSequential(new EjectTimedCommand(false, 0.5));
        addSequential(new PIDDriveInchesCommand(13.5, false));
        addSequential(new PIDDriveRotateCommand(90));
        addSequential(new PIDDriveInchesCommand(38, false));
        addSequential(new IntakeTimedCommand(true, 3)); //was parallel, but is incorrect
        addSequential(new PIDDriveInchesCommand(38, true));
        addSequential(new PIDDriveRotateCommand(0));
        addSequential(new PIDDriveInchesCommand(13.5, true));
        addSequential(new EjectTimedCommand(false, 0.5));
    }
}

/*
addSequential(new PIDDriveInchesCommand(10, true));
addSequential(new PIDDriveRotateCommand(35));
addSequential(new PIDDriveInchesCommand(93, true));
addSequential(new PIDDriveRotateCommand(0));
addSequential(new PIDDriveInchesCommand(17, true));
addSequential(new EjectTimedCommand(false, 0.5));
*/