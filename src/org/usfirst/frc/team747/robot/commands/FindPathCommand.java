package org.usfirst.frc.team747.robot.commands;

import java.io.File;

import org.usfirst.frc.team747.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class FindPathCommand extends Command {

	private static final int MAX_VELOCITY = 12;
	private Trajectory trajectoryR, trajectoryL;
	private TankModifier modifierR, modifierL;
	private EncoderFollower left, right;
	
    public FindPathCommand(String name) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	trajectoryL = Pathfinder.readFromCSV(new File("/home/lvuser/trajectories/" + name + "_left_detailed.csv"));
        trajectoryR = Pathfinder.readFromCSV(new File("/home/lvuser/trajectories/" + name + "_right_detailed.csv"));
    	
        modifierL = new TankModifier(trajectoryL).modify(0.5);
        modifierR = new TankModifier(trajectoryR).modify(0.5);
        
        left = new EncoderFollower(modifierL.getSourceTrajectory());
    	right = new EncoderFollower(modifierR.getSourceTrajectory());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	left.configureEncoder((int) Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition(), 22118, 0.1524);
    	right.configureEncoder((int) Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition(), 22118, 0.1524);
    	
    	left.configurePIDVA(1.0, 0.0, 0.0, 1 / MAX_VELOCITY, 0);
    	right.configurePIDVA(1.0, 0.0, 0.0, 1 / MAX_VELOCITY, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double l = left.calculate((int) Robot.DRIVE_SUBSYSTEM.getLeftEncoderPosition());
    	double r = right.calculate((int) Robot.DRIVE_SUBSYSTEM.getRightEncoderPosition());

    	double gyro_heading = Robot.getNavXAngle();    // Assuming the gyro is giving a value in degrees
    	double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

    	double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
    	double turn = 0.8 * (-1.0/80.0) * angleDifference;

    	Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.set(ControlMode.PercentOutput, l + turn);
    	Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.set(ControlMode.PercentOutput, r + turn);
    }

    // Make this return true when this Command no longer needs to run execute()
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
