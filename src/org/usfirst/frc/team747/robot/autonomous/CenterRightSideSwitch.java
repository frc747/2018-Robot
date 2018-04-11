package org.usfirst.frc.team747.robot.autonomous;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team747.robot.maps.RobotMap;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.commands.EjectTimedCommand;
import org.usfirst.frc.team747.robot.commands.MoveOnPathCommand;
import org.usfirst.frc.team747.robot.commands.MoveOnPathCommand.Direction;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;

public class CenterRightSideSwitch extends CommandGroup {
    
    public  CenterRightSideSwitch() {
        
        requires(Robot.DRIVE_SUBSYSTEM);
//      addSequential(new PIDDriveInchesCommand(90-RobotMap.robotLength, true));
//      addSequential(new PIDDriveRotateCommand(90));
//      addSequential(new PIDDriveInchesCommand(93-RobotMap.robotLength, true));
//      addSequential(new PIDDriveRotateCommand(0));//-90
//      addSequential(new PIDDriveInchesCommand(40, true));
        addSequential(new MoveOnPathCommand("centerSwitchRight", Direction.FORWARD));
        addSequential(new EjectTimedCommand(false, 0.5));
        // cube is scored at this point
        addSequential(new PIDDriveInchesCommand(11.5, false));
        addSequential(new PIDDriveRotateCommand(90));
        // these two lines above drive the robot away from the switch and then rotate towards the cube pile
    }
}
