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

public class CenterRightSideVault extends CommandGroup {
    
    public  CenterRightSideVault() {
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
        
        
        
        addSequential(new PIDDriveRotateCommand(50));

        addSequential(new PIDDriveInchesCommand(65, false));
        
    }
}
