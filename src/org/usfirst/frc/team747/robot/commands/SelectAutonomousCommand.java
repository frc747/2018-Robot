package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.auton.Left1AutonCommand;
import org.usfirst.frc.team747.robot.auton.Left2AutonCommand;
import org.usfirst.frc.team747.robot.auton.Left3AutonCommand;
import org.usfirst.frc.team747.robot.auton.Right1AutonCommand;
import org.usfirst.frc.team747.robot.auton.Right2AutonCommand;
import org.usfirst.frc.team747.robot.auton.Right3AutonCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SelectAutonomousCommand extends Command{
	private static boolean finished = false;

    @SuppressWarnings("unused")
	public SelectAutonomousCommand(int station) {
        runAuton(station);
    }
    public void runAuton(int i) {
    	switch(i) {
    	case 1:
    		new Left1AutonCommand();
    		finished = true;
    		break;
    	case 2:
    		new Left3AutonCommand();
    		finished = true;
    		break;
    	}
    }
    public boolean isFinished() {
    	if(finished) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
