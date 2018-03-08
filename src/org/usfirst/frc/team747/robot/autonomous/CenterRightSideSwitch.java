package org.usfirst.frc.team747.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PauseCommand;

public class CenterRightSideSwitch extends CommandGroup {
    
    public  CenterRightSideSwitch() {
        
        requires(Robot.DRIVE_SUBSYSTEM);
          addSequential(new PIDDriveInchesCommand(5, true));
          addSequential(new PIDDriveRotateCommand(-30));
          addSequential(new PIDDriveInchesCommand(90, true));
          addSequential(new PIDDriveRotateCommand(30));
          addSequential(new PIDDriveInchesCommand(4, true));
          //addSequential(new PIDDriveInchesCommand(5, true));
          addSequential(new EjectTimedCommand(false, 0.5));
//        addSequential(new PIDDriveInchesCommand(90-RobotMap.robotLength, true));
//        addSequential(new PIDDriveRotateCommand(90));
//        addSequential(new PIDDriveInchesCommand(81-RobotMap.robotLength, true));
//        addSequential(new PIDDriveRotateCommand(-90));
//        addSequential(new PIDDriveInchesCommand(45, true));
//        addSequential(new EjectTimedCommand(false, 0.5));
//        addSequential(new PIDDriveInchesCommand(45, false));
//        addSequential(new PIDDriveRotateCommand(130));
//        addSequential(new PIDDriveInchesCommand(30, false));
        }
}
