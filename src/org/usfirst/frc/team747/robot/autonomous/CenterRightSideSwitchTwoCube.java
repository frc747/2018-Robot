package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.ForwardTimedGroup;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommandSlow;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesForDiagonalCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.ReverseTimedGroup;

public class CenterRightSideSwitchTwoCube extends CommandGroup {
    
    public  CenterRightSideSwitchTwoCube() {
        //do not modify the length of the diagonal drive, modify the distance driven forward beforehand
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(25, true));
        addSequential(new PIDDriveRotateCommand(36));
        addParallel(new ReverseTimedGroup(1));
        addSequential(new PIDDriveInchesForDiagonalCommand("right" , "diagonal"));
        addSequential(new PIDDriveRotateCommand(0));
        addSequential(new EjectTimedCommand(false, 1));
        // cube is scored at this point
        addSequential(new PIDDriveInchesCommand(15.5, false));
        addSequential(new PIDDriveRotateCommand(90));
        // these two lines above drive the robot away from the switch and then rotate towards the cube pile
        
        //TWO CUBE AUTO TEST OTHERS FIRST
        
        //commands for the one movement approach
        addParallel(new ForwardTimedGroup(3));
        addSequential(new PIDDriveInchesCommandSlow(40, false));
        addParallel(new ForwardTimedGroup(4));
        addSequential(new PIDDriveInchesCommand(36, true));
//        addParallel(new ForwardTimedGroup(2));
//        addSequential(new PIDDriveRotateCommand(0));
//        addParallel(new EjectTimedCommand(false, 1.0));
//        addSequential(new PIDDriveInchesCommand(5, true));
        
        //commands for the two movement approach
//      addParallel(new ForwardTimedGroup(3.5));
//      addSequential(new PIDDriveInchesCommand(30, true));
//      addSequential(new PIDDriveInchesCommandSlow(10, false));
//      addParallel(new ForwardTimedGroup(3));
//      addSequential(new PIDDriveInchesCommand(34, true));
//      addSequential(new PIDDriveRotateCommand(0));
//      addParallel(new EjectTimedCommand(false, 1.0));
//      addSequential(new PIDDriveInchesCommand(5, true));
    }
}
