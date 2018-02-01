package org.usfirst.frc.team747.robot.auton;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.maps.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Left1AutonCommand extends CommandGroup {
	private static boolean finished = false;
    public Left1AutonCommand() {
    	//addSequential(new PIDDriveInchesCommand(70-RobotMap.robotLength, true));
    	addSequential(new PIDDriveInchesCommand(60, false));
    	addSequential(new PIDDriveRotateCommand(180));
    	addSequential(new PIDDriveInchesCommand(30, false));
    	finished = true;     // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
    public boolean isFinished() {
		if(finished) {
			return true;
		} else {
			return false;
		}
	}
}