package org.usfirst.frc.team747.robot.commands;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;
import java.io.File;
//import org.usfirst.frc.team1089.util.MercMath;
//import org.usfirst.frc.team1089.util.config.DriveTrainSettings;


/**
 *
 */
public class MoveOnPathCommand extends Command {

	private static Logger log = LogManager.getLogger(MoveOnPathCommand.class);
	private TalonSRX left;
	private TalonSRX right;

	private final int TRAJECTORY_SIZE;

    private Trajectory trajectoryR, trajectoryL;

    private MotionProfileStatus statusLeft, statusRight;
    private static Notifier trajectoryProcessor;

    private boolean isRunning;
    private int dir;

    public enum Direction {
        BACKWARD,
        FORWARD
    }
	
    public MoveOnPathCommand(String name, Direction direction) {
        requires(Robot.DRIVE_SUBSYSTEM);
        setName("MoveOnPath-" + name);
        log.info(getName() + " Beginning constructor");

        left = Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary;
        right = Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary;

        switch(direction) {
            case BACKWARD:
                dir = -1;
                break;
            case FORWARD:
            default:
                dir = 1;
                break;
        }
        
        trajectoryL = Pathfinder.readFromFile(new File("/home/lvuser/trajectories/" + name + "_left_detailed.traj"));
        trajectoryR = Pathfinder.readFromFile(new File("/home/lvuser/trajectories/" + name + "_right_detailed.traj"));
//        trajectoryL = Pathfinder.readFromCSV(new File("/home/lvuser/trajectories/" + name + "_left_detailed.csv"));
//        trajectoryR = Pathfinder.readFromCSV(new File("/home/lvuser/trajectories/" + name + "_right_detailed.csv"));

        if (trajectoryProcessor == null) {
            trajectoryProcessor = new Notifier(() -> {
                left.processMotionProfileBuffer();
                right.processMotionProfileBuffer();
            });
        }

        statusLeft = new MotionProfileStatus();
        statusRight = new MotionProfileStatus();

        if (trajectoryL != null) {
            TRAJECTORY_SIZE = trajectoryL.length();

            log.info(getName() + " construced: " + TRAJECTORY_SIZE);
        } else {
            TRAJECTORY_SIZE = 0;
            log.info(getName() + " could not be constructed!");
            end();
        }
	}

    // Called just before this Command runs the first time
    protected void initialize() {  
		System.out.println("MoveOnPath: Initializing...");
		
		// Reset command state
		reset();
		
		// Configure PID values
		//double[] pid = DriveTrainSettings.getPIDValues("moveOnPath");
		configurePID(0.1, 0.0, 0.0, Robot.DRIVE_SUBSYSTEM.getFeedForward(2055)); //205.56 in/s = 34.26 rps = 2055.6 rpm
		
		// Change motion control frame period
		left.changeMotionControlFramePeriod(10);
		right.changeMotionControlFramePeriod(10);
		
		// Fill TOP (API-level) buffer
		fillTopBuffer();
		
		// Start processing
		// i.e.: moving API points to RAM
		trajectoryProcessor.startPeriodic(0.005);
		log.info(getName() + " Initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	left.getMotionProfileStatus(statusLeft);
        right.getMotionProfileStatus(statusRight);

        // Give a slight buffer when we process to make sure we don't bite off more than
        // we can chew or however that metaphor goes.
        if (!isRunning && statusLeft.btmBufferCnt >= 5 && statusRight.btmBufferCnt >= 5) {
            setMotionProfileMode(SetValueMotionProfile.Enable);

            log.log(Level.INFO, "Starting motion profile...");

            isRunning = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 // If we're running, only finish if both talons
        // reach their last valid point
	    return
            isRunning &&
            statusLeft.activePointValid &&
            statusLeft.isLast &&
            statusRight.activePointValid &&
            statusRight.isLast;
    }

    // Called once after isFinished returns true
    protected void end() {
        // Stop processing trajectories
        trajectoryProcessor.stop();

		left.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 10, DriveSubsystem.timeoutMs);
        right.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 10, DriveSubsystem.timeoutMs);

        Robot.DRIVE_SUBSYSTEM.stop();

        log.log(Level.INFO, "Finished running");
    }
    private void fillTopBuffer() {
	    for (int i = 0; i < TRAJECTORY_SIZE; i++) {
            TrajectoryPoint trajPointL = new TrajectoryPoint();
            TrajectoryPoint trajPointR = new TrajectoryPoint();

	        // NOTE: Encoder ticks are backwards, we need to work with that.
            double currentPosL = -trajectoryL.segments[i].position * dir;
            double currentPosR = -trajectoryR.segments[i].position * dir;

            double velocityL = trajectoryL.segments[i].velocity;
            double velocityR = trajectoryR.segments[i].velocity;

            boolean isLastPointL = TRAJECTORY_SIZE == i + 1;
            boolean isLastPointR = TRAJECTORY_SIZE == i + 1;
            boolean isZero = i == 0;

            // For each point, fill our structure and pass it to API
            trajPointL.position = Robot.DRIVE_SUBSYSTEM.convertInchesToTicks(currentPosL*12); //Convert Revolutions to Units
            trajPointR.position = Robot.DRIVE_SUBSYSTEM.convertInchesToTicks(currentPosR*12);
            trajPointL.velocity = Robot.DRIVE_SUBSYSTEM.revsPerMinuteToTicksPerTenth(velocityL); //Convert RPM to Units/100ms
            trajPointR.velocity = Robot.DRIVE_SUBSYSTEM.revsPerMinuteToTicksPerTenth(velocityR);
            trajPointL.profileSlotSelect0 = 0;
            trajPointR.profileSlotSelect0 = 0;

            // Sets the duration of each trajectory point to 20ms
            trajPointL.timeDur = TrajectoryPoint.TrajectoryDuration.Trajectory_Duration_20ms;
            trajPointR.timeDur = TrajectoryPoint.TrajectoryDuration.Trajectory_Duration_20ms;

            // Set these to true on the first point
            trajPointL.zeroPos = isZero;
            trajPointR.zeroPos = isZero;

            // Set these to true on the last point
            trajPointL.isLastPoint = isLastPointL;
            trajPointR.isLastPoint = isLastPointR;

            // Push to API level buffer
            left.pushMotionProfileTrajectory(trajPointL);
            right.pushMotionProfileTrajectory(trajPointR);
        }
    }

    private void configurePID(double p, double i, double d, double f) {
        left.config_kP(0, p, DriveSubsystem.timeoutMs);
        right.config_kP(0, p, DriveSubsystem.timeoutMs);

        left.config_kI(0, i, DriveSubsystem.timeoutMs);
        right.config_kI(0, i, DriveSubsystem.timeoutMs);

        left.config_kD(0, d, DriveSubsystem.timeoutMs);
        right.config_kD(0, d, DriveSubsystem.timeoutMs);

        left.config_kF(0, f, DriveSubsystem.timeoutMs);
        right.config_kF(0, f, DriveSubsystem.timeoutMs);
    }

    private void setMotionProfileMode(SetValueMotionProfile value) {
        left.set(ControlMode.MotionProfile, value.value);
        right.set(ControlMode.MotionProfile, value.value);
    }

    private void reset() {
        // Reset flags and motion profile modes
        isRunning = false;
        setMotionProfileMode(SetValueMotionProfile.Disable);
        Robot.DRIVE_SUBSYSTEM.resetBothEncoders();

        // Clear the trajectory buffer
        left.clearMotionProfileTrajectories();
        right.clearMotionProfileTrajectories();

        log.log(Level.INFO, "Cleared trajectories; check: " + statusLeft.btmBufferCnt);
    }
}
