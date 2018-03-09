package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PauseCommand;

public class CenterLeftSideSwitchDiagonal extends CommandGroup {
    
    public  CenterLeftSideSwitchDiagonal() {
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(10, true));
   		addSequential(new PIDDriveRotateCommand(-30));
        addSequential(new PIDDriveInchesCommand(90, true));
   		addSequential(new PIDDriveRotateCommand(30));
        addSequential(new PIDDriveInchesCommand(25, true));
        addSequential(new EjectTimedCommand(false, 0.5));
    }
}
