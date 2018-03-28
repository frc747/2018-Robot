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

public class CenterRightSideSwitchDiagonal extends CommandGroup {
    
    public  CenterRightSideSwitchDiagonal() {
        //do not modify the length of the diagonal drive, modify the distance driven forward beforehand
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(25, true));
        addSequential(new PIDDriveRotateCommand(36));
        addParallel(new ReverseTimedGroup(1));
        addSequential(new PIDDriveInchesForDiagonalCommand("right" , "diagonal"));
        addSequential(new PIDDriveRotateCommand(0));
        addSequential(new EjectTimedCommand(false, 2));
        // cube is scored at this point
        addSequential(new PIDDriveInchesCommand(15.5, false));
        addSequential(new PIDDriveRotateCommand(90));
        // these two lines above drive the robot away from the switch and then rotate towards the cube pile
    }
}
