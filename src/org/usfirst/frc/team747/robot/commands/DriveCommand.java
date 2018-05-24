package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.ControllerMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command{

    private static final double MAX_PERCENT_VOLTAGE = 1.0;
    private static final double MIN_PERCENT_VOLTAGE = 0.0;
    
    private static final int timeoutMs = 10;
    
	public DriveCommand() {
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
	}
	protected void execute() {
		double left = -OI.paradeDrive.getRawAxis(ControllerMap.Joystick.AXIS_Y.getValue());
        double right = -OI.paradeDrive.getRawAxis(5);

        if (Math.abs(left) < 0.1) {
            left = 0;
        }
        if (Math.abs(right) < 0.1) {
            right = 0;
        }
        
        double speed = 1;
        
        Robot.DRIVE_SUBSYSTEM.set(left * speed, right * speed);
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
