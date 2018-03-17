package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.ForwardTimedGroup;
import org.usfirst.frc.team747.robot.commands.IntakeTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommandSlow;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesForDiagonalCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

public class CenterLeftSideSwitchDiagonal extends CommandGroup {
    
    public  CenterLeftSideSwitchDiagonal() {
        //do not modify the length of the diagonal drive, modify the distance driven forward beforehand
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(28, true));
        addSequential(new PIDDriveRotateCommand(-39));
        addSequential(new PIDDriveInchesForDiagonalCommand("left" , "diagonal"));
        addSequential(new PIDDriveRotateCommand(0));
        addSequential(new EjectTimedCommand(false, 2));
        // cube is scored at this point
        addSequential(new PIDDriveInchesCommand(11.5, false));
        addSequential(new PIDDriveRotateCommand(-90));
        // these two lines above drive the robot away from the switch and then rotate towards the cube pile
        
        //TWO CUBE AUTO TEST OTHERS FIRST
        
        //values for the right side, adapted for the left side
//        addParallel(new ForwardTimedGroup());
//        addSequential(new PIDDriveInchesCommandSlow(50, false));
//        addSequential(new PIDDriveInchesCommand(38, true));
//        addSequential(new PIDDriveRotateCommand(0));
//        addParallel(new EjectTimedCommand(false, 0.5));
//        addSequential(new PIDDriveInchesCommand(5, true));
        
        
        
        //WIP values new values for the left side
//        addSequential(new PIDDriveInchesCommand(15.5, false));
//        addSequential(new PIDDriveRotateCommand(-90));
//        addParallel(new ForwardTimedGroup()); //was parallel, but is incorrect
//        addSequential(new PIDDriveInchesCommand(38, false));
//        addSequential(new PIDDriveInchesCommand(38, true));
//        addSequential(new PIDDriveRotateCommand(0));
//        addSequential(new PIDDriveInchesCommand(13.5, true));
//        addSequential(new EjectTimedCommand(false, 0.5));
    }
}
