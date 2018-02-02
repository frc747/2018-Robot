package org.usfirst.frc.team747.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestCommandGroup extends CommandGroup {

    public TestCommandGroup(int num) {
      switch(num) {
      case 1:
    	addSequential(new PIDDriveInchesCommand(24, false));
      	addSequential(new PIDDriveRotateCommand(90));
      	addSequential(new PIDDriveInchesCommand(10, false));
      	addSequential(new PIDDriveRotateCommand(-90));
      	addSequential(new PIDDriveInchesCommand(30, false));
    	  break;
      case 2:
    	addSequential(new PIDDriveInchesCommand(60, false));
    	  break;
      case 3:
    	addSequential(new PIDDriveInchesCommand(60, false));
    	addSequential(new PIDDriveRotateCommand(180));
    	addSequential(new PIDDriveInchesCommand(30, false));
    	  break;
      case 4:
    	addSequential(new PIDDriveInchesCommand(30, false));
    	addSequential(new PIDDriveRotateCommand(90));
    	addSequential(new PIDDriveInchesCommand(15, false));
    	addSequential(new PIDDriveRotateCommand(-90));
    	addSequential(new PIDDriveInchesCommand(30, false));
    	addSequential(new PIDDriveRotateCommand(-90));
    	addSequential(new PIDDriveInchesCommand(30, false));
    	addSequential(new PIDDriveRotateCommand(-90));
    	addSequential(new PIDDriveInchesCommand(30, false));
    	addSequential(new PIDDriveRotateCommand(-90));
    	addSequential(new PIDDriveInchesCommand(15, false));
    	addSequential(new PIDDriveRotateCommand(90));
    	addSequential(new PIDDriveInchesCommand(15, false));

    	  break;
      case 5:
    	  addSequential(new PIDDriveRotateCommand(359));
    	  break;
      case 6:
    	  addSequential(new PIDDriveRotateCommand(-90));
    	  addSequential(new PIDDriveRotateCommand(180));

    	  break;
      }
    	addSequential(new PIDDriveInchesCommand(24, false));
    	addSequential(new PIDDriveRotateCommand(90));
    	addSequential(new PIDDriveInchesCommand(10, false));
    	addSequential(new PIDDriveRotateCommand(-90));
    	addSequential(new PIDDriveInchesCommand(30, false));

    }
}
