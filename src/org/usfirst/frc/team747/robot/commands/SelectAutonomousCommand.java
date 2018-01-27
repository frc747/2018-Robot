package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.auton.Left1AutonCommand;
import org.usfirst.frc.team747.robot.auton.Left2AutonCommand;
import org.usfirst.frc.team747.robot.auton.Left3AutonCommand;
import org.usfirst.frc.team747.robot.auton.Right1AutonCommand;
import org.usfirst.frc.team747.robot.auton.Right2AutonCommand;
import org.usfirst.frc.team747.robot.auton.Right3AutonCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SelectAutonomousCommand extends CommandGroup {

    @SuppressWarnings("unused")
	public SelectAutonomousCommand(int station) {
        // Add Commands here:
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
    	
    	
    	if('L' == 'L') {
    		switch(station) {
    		case 1:
    			new Left1AutonCommand();
    			Robot.autonomousCommand.cancel();
    			break;
    		case 2:
    			new Left2AutonCommand();
    			Robot.autonomousCommand.cancel();
    			break;
    		case 3:
    			new Left3AutonCommand();
    			Robot.autonomousCommand.cancel();  
    			break;
    		}
    	} else {
    		switch(station) {
    		case 1:
    		 	
    			new Right1AutonCommand();
    			Robot.autonomousCommand.cancel();
    			break;
    		case 2:
    			new Right2AutonCommand();
    			Robot.autonomousCommand.cancel();
    			break;
    		case 3:
    			new Right3AutonCommand();
    			Robot.autonomousCommand.cancel();
    			break;
    		}
    	}
    }
}
