package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class PIDDriveRotateCommand extends PIDCommand {

    private double angleToRotate;
    
    private int onTargetCount;
private final static int TARGET_COUNT_ONE_SECOND = 50;
    
    //Half a second is being multiplied by the user input to achieve the desired "ON_TARGET_COUNT"
    private final static double ON_TARGET_MINIMUM_COUNT = TARGET_COUNT_ONE_SECOND * 0.25; //times 10 is 5 seconds, times 20 is 10 seconds, etc...
    
    private final static double STOP_THRESHOLD_DEGREES = 3.5;
    private final static double MAX_PERCENT_VBUS = .5;
    
    private final static double DRIVE_SPEED_MINIMUM = 0.325;
    private static final double MAX_PERCENT_VOLTAGE = 1.0;
    private static final double MIN_PERCENT_VOLTAGE = 0.16;
    private static final int timeoutMs = 10;

    public PIDDriveRotateCommand(double degreesRotate) {
//        super(0.05, 0.0005, 0.5);
        super(0.0, 0.0, 0.0); //.2,0,.375     .1,0,.1       .3,0,.1
        
        this.angleToRotate = degreesRotate;
        
        requires(Robot.DRIVE_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
    	
        onTargetCount = 0;
        
        Robot.resetNavXAngle();
        
        getPIDController().setInputRange(-180, 180);
        getPIDController().setOutputRange(-MAX_PERCENT_VBUS, MAX_PERCENT_VBUS);
        getPIDController().setAbsoluteTolerance(STOP_THRESHOLD_DEGREES); //the threshold that the PID Controller abides by to consider the value as "on target"
        getPIDController().setContinuous(true); //will reset back to the minimum value after reaching the max value
        
        getPIDController().setSetpoint(angleToRotate);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
        if (getPIDController().onTarget()) {
            onTargetCount++;
        } else {
            onTargetCount = 0;
        }
                    
        return (onTargetCount > ON_TARGET_MINIMUM_COUNT);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    @Override
    protected double returnPIDInput() {
        return Robot.getNavXAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        
//        double sign = output > 0 ? 1.0 : -1.0; //google ternary operator
        
        double sign = Math.signum(output);
        
//        IF (OUTPUT != 0 && MATH.ABS(OUTPUT) < DRIVE_SPEED_MINIMUM) { //BANG MEANS "!" WHICH IS NOT IN JAVA, COMPUTER/PROGRAMMING SLANG
//            OUTPUT = SIGN * DRIVE_SPEED_MINIMUM;
//        }
        
        Robot.DRIVE_SUBSYSTEM.set(output, -output);
    }
}
