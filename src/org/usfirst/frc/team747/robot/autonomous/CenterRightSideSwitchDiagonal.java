package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.ForwardTimedGroup;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommandSlow;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesForDiagonalCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

public class CenterRightSideSwitchDiagonal extends CommandGroup {
    
    public  CenterRightSideSwitchDiagonal() {
        //do not modify the length of the diagonal drive, modify the distance driven forward beforehand
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(19, true));
        addSequential(new PIDDriveRotateCommand(36));
        addSequential(new PIDDriveInchesForDiagonalCommand("right" , "diagonal"));
        addSequential(new PIDDriveRotateCommand(0));
        addSequential(new EjectTimedCommand(false, 0.5));
        
        
        //TWO CUBE AUTO TEST OTHERS FIRST
        
        //The routine tested at the shop that was able to corral a cube between the intake arms successfully, but not intake and shoot
//        addSequential(new PIDDriveInchesCommand(11.5, false));
//        addSequential(new PIDDriveRotateCommand(90));
//        addParallel(new ForwardTimedGroup()); //was parallel, but is incorrect
//        addSequential(new PIDDriveInchesCommandSlow(50, false));
//        addSequential(new PIDDriveInchesCommand(38, true));
//        addSequential(new PIDDriveRotateCommand(0));
//        addParallel(new EjectTimedCommand(false, 0.5));
//        addSequential(new PIDDriveInchesCommand(5, true));
    }
}
