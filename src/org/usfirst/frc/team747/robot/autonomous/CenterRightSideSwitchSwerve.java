package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.ForwardTimedGroup;
import org.usfirst.frc.team747.robot.commands.PIDDriveArcRightCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommandSlow;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesShootCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

public class CenterRightSideSwitchSwerve extends CommandGroup {
    
    public  CenterRightSideSwitchSwerve() {
        //do not modify the length of the diagonal drive, modify the distance driven forward beforehand
        
        requires(Robot.DRIVE_SUBSYSTEM);
        //addSequential(new AutonomousPlayback());
        addSequential(new PIDDriveArcRightCommand(113, true)); //was 110 //changed to 116 (with old wheels)
        addParallel(new EjectTimedCommand(false, 1));
        addSequential(new PIDDriveInchesCommandSlow(25, false)); //23.75
        
        
        
        // cube is scored at this point
//        addSequential(new PIDDriveInchesCommand(13.75, false));
        addSequential(new PIDDriveRotateCommand(90));
        // these two lines above drive the robot away from the switch and then rotate towards the cube pile
        
        //TWO CUBE AUTO TEST OTHERS FIRST
        
        //commands for the one movement approach
        addParallel(new ForwardTimedGroup(4.5));
        addSequential(new PIDDriveInchesCommandSlow(48, false));
        addParallel(new ForwardTimedGroup(4));
        addSequential(new PIDDriveInchesCommand(40, true));
        addParallel(new ForwardTimedGroup(4));
        addSequential(new PIDDriveRotateCommand(0));
        addParallel(new ForwardTimedGroup(4));
        addSequential(new PIDDriveInchesCommand(22, true)); //was 20 //changed to 26 (with old wheels)
        addSequential(new EjectTimedCommand(false, 0.5));
        
        addSequential(new PIDDriveInchesCommandSlow(7.75, false));
        addSequential(new PIDDriveRotateCommand(-90));
//        addSequential(new PIDDriveInchesCommand(10, true));
//        addParallel(new EjectTimedCommand(false, 1.0));
//        addSequential(new PIDDriveInchesCommand(5, true));
//        addSequential(new PIDDriveInchesCommand(20, false));
        
        //addSequential(new PIDDriveInchesCommand(5, true));
//        addSequential(new AutonomousPlaybackPart3("auton_part_3/"));
       /* addParallel(new ForwardTimedGroup(2.0));
        addSequential(new PIDDriveRotateCommand(0));
        addParallel(new EjectTimedCommand(false, 1.0));
        addSequential(new PIDDriveInchesCommand(14, true));*/
        
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
