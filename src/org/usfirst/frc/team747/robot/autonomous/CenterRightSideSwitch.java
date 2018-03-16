package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

public class CenterRightSideSwitch extends CommandGroup {
    
    public  CenterRightSideSwitch() {
        
        requires(Robot.DRIVE_SUBSYSTEM);
        addSequential(new PIDDriveInchesCommand(90-RobotMap.robotLength, true));
        addSequential(new PIDDriveRotateCommand(90));
        addSequential(new PIDDriveInchesCommand(93-RobotMap.robotLength, true));
        addSequential(new PIDDriveRotateCommand(0));//-90
        addSequential(new PIDDriveInchesCommand(43, true));
        addSequential(new EjectTimedCommand(false, 0.5));
    }
}
