package org.usfirst.frc.team747.robot.commands;

import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.ControllerMap;
import org.usfirst.frc.team747.robot.readwrite.BTMain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRecordCommand extends Command{

    private static final double MAX_PERCENT_VOLTAGE = 1.0;
    private static final double MIN_PERCENT_VOLTAGE = 0.0;
    
    private static final int timeoutMs = 10;
    
	FileWriter writer;
	
	long startTime;
    
	public DriveRecordCommand() {
		requires(Robot.DRIVE_SUBSYSTEM);
		
	}
	protected void initalize() {
		System.out.println("Initialized DriveCommand.");
		Robot.DRIVE_SUBSYSTEM.changeControlMode(ControlMode.PercentOutput);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        
      //record the time we started recording
		startTime = System.currentTimeMillis();
		
		//put the filesystem location you are supposed to write to as a string 
		//as the argument in this method, as of 2015 it is /home/lvuser/recordedAuto.csv
		try {
			writer = new FileWriter(BTMain.autoFileWrite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void execute() {
		double left = -OI.leftStick.getRawAxis(ControllerMap.Joystick.AXIS_Y.getValue());
        double right = -OI.rightStick.getRawAxis(ControllerMap.Joystick.AXIS_Y.getValue());

        if (Math.abs(left) < 0.1) {
            left = 0;
        }
        if (Math.abs(right) < 0.1) {
            right = 0;
        }
        
        double speed = 1;
        
        Robot.DRIVE_SUBSYSTEM.set(left * speed, right * speed);
        
        if (writer != null) {
			try {
				writer.append("" + (System.currentTimeMillis() - startTime));
				writer.append("," + OI.leftStick.getRawAxis(1));
				writer.append("," + OI.rightStick.getRawAxis(1));
				writer.append("," + OI.operatorController.getRawButton(4) + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
	
	
	@Override
	protected boolean isFinished() {
		return false;
	}

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
	
}
