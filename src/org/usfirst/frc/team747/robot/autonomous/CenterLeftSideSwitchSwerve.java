package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.ForwardTimedGroup;
import org.usfirst.frc.team747.robot.commands.PIDDriveArcLeftCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommandSlow;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesShootCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

public class CenterLeftSideSwitchSwerve extends CommandGroup {
    
    public  CenterLeftSideSwitchSwerve() {
        //do not modify the length of the diagonal drive, modify the distance driven forward beforehand
        
        requires(Robot.DRIVE_SUBSYSTEM);
       // addSequential(new AutonomousPlayback());
        addSequential(new PIDDriveArcLeftCommand(116, true));
        //addParallel(new EjectTimedCommand(false, 1)); //remove after testing
        addSequential(new PIDDriveInchesCommandSlow(24.5, false));
        
        
        // cube is scored at this point
//        addSequential(new PIDDriveInchesCommand(9.75, false));
        addSequential(new PIDDriveRotateCommand(-90));
        // these two lines above drive the robot away from the switch and then rotate towards the cube pile
        
        //TWO CUBE AUTO TEST OTHERS FIRST
        
        //commands for the one movement approach
        addParallel(new ForwardTimedGroup(4.5));
        addSequential(new PIDDriveInchesCommandSlow(58, false));
        addParallel(new ForwardTimedGroup(4));
        addSequential(new PIDDriveInchesCommand(58, true));
        addParallel(new ForwardTimedGroup(4));
        addSequential(new PIDDriveRotateCommand(0));
        addParallel(new ForwardTimedGroup(4));
        addSequential(new PIDDriveInchesCommand(20, true));
        addSequential(new EjectTimedCommand(false, 0.5));        
        
        addSequential(new PIDDriveInchesCommandSlow(7.75, false));
        addSequential(new PIDDriveRotateCommand(90));
//        addParallel(new ForwardTimedGroup(4.5));
//        addSequential(new PIDDriveInchesCommandSlow(58, false));
//        addParallel(new ForwardTimedGroup(4));
//        addSequential(new PIDDriveInchesCommand(58, true));
//        addParallel(new ForwardTimedGroup(2));
//        addSequential(new PIDDriveRotateCommand(0));
//        addSequential(new PIDDriveInchesShootCommand(15, true));
    }
}
