package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDDriveInchesCommand extends Command {
    
    //execute is called every 20ms and isFinished is called right after execute
    //add a button to Ryan's joystick that will default the drive train back to DriveWithJoystickCommand
    
    private double driveTicks;
    
    private double driveInches;
//    private double driveP;
//    private double driveI;
//    private double driveD;
    
    private static final int pidIdx = 0;
    private static final int timeoutMs = 10;
    private static final int slotIdx = 0;
    
    private final static double ENCODER_TICKS_PER_REVOLUTION = 4096;

    private static final double MAX_PERCENT_VOLTAGE = 1.0; //was 12 (volts previously, now the input is percent)
    private static final double MIN_PERCENT_VOLTAGE = 0.0; //was 1.9 (volts perviously, now the input is percent)

    //STOP_THRESHOLD_REAL was 3 inches and is now 8 inches in an attempt to cut back on time
    private final static double STOP_THRESHOLD_REAL = 3; //3.0;
    private final static double STOP_THRESHOLD_ADJUSTED = Robot.DRIVE_SUBSYSTEM.convertInchesToRevs(STOP_THRESHOLD_REAL * ENCODER_TICKS_PER_REVOLUTION);
    
//    private final static int I_ZONE_IN_REVOLUTIONS = 50; //100;
    
    private final static int allowableCloseLoopError = 1;
    
    private int onTargetCount = 0;
    
    private final static int TARGET_COUNT_ONE_SECOND = 50;
    
    //Half a second is being multiplied by the user input to achieve the desired "ON_TARGET_COUNT"
    private final static double ON_TARGET_MINIMUM_COUNT = TARGET_COUNT_ONE_SECOND * .1;

    private double specificDistanceP = OI.PID_VALUE_P;
    
    private double specificDistanceI = OI.PID_VALUE_I;
    
    private double specificDistanceD = OI.PID_VALUE_D;
    
    private double specificDistanceF = OI.PID_VALUE_F;
    
    public PIDDriveInchesCommand(double inches, boolean reverse) {
        requires(Robot.DRIVE_SUBSYSTEM);
          
//      this.driveTicks = inches / ENCODER_TICKS_PER_REVOLUTION;
    
        if (reverse) {
            this.driveTicks = -Robot.DRIVE_SUBSYSTEM.applyGearRatio(Robot.DRIVE_SUBSYSTEM.convertInchesToRevs(inches * ENCODER_TICKS_PER_REVOLUTION));//input now has to be ticks instead of revolutions which is why we multiply by 4096
        } else {
            this.driveTicks = Robot.DRIVE_SUBSYSTEM.applyGearRatio(Robot.DRIVE_SUBSYSTEM.convertInchesToRevs(inches * ENCODER_TICKS_PER_REVOLUTION));
        }
        
        this.driveInches = inches;
//        this.driveP = specificDistanceP;
//        this.driveI = specificDistanceI;
//        this.driveD = specificDistanceD;
    }
    
        
    protected void initialize() {
        
        onTargetCount = 0;
        
        Robot.DRIVE_SUBSYSTEM.resetBothEncoders();
//      Robot.resetNavXAngle();
        Robot.DRIVE_SUBSYSTEM.enablePositionControl();
        
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.config_kP(pidIdx, specificDistanceP, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.config_kP(pidIdx, specificDistanceP, timeoutMs);
        
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.config_kI(pidIdx, specificDistanceI, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.config_kI(pidIdx, specificDistanceI, timeoutMs);
        
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.config_kD(pidIdx, specificDistanceD, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.config_kD(pidIdx, specificDistanceD, timeoutMs);
        
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.config_kF(pidIdx, specificDistanceF, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.config_kF(pidIdx, specificDistanceF, timeoutMs);
        
//        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.ClearIaccum();
//        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.ClearIaccum();
        
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        
//        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.setCloseLoopRampRate(rampRate);
//        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.setCloseLoopRampRate(rampRate);
        
        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configAllowableClosedloopError(slotIdx, allowableCloseLoopError, timeoutMs);
        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configAllowableClosedloopError(slotIdx, allowableCloseLoopError, timeoutMs);
        
//        Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.config_IntegralZone(slotIdx, I_ZONE_IN_REVOLUTIONS, timeoutMs);
//        Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.config_IntegralZone(slotIdx, I_ZONE_IN_REVOLUTIONS, timeoutMs);
        
        if (driveInches > 30) {
            Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configMotionCruiseVelocity(7500, timeoutMs); //7500, 20500, 7500, 20000
            Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configMotionAcceleration(20500, timeoutMs); //test 5000
            Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configMotionCruiseVelocity(7500, timeoutMs);
            Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configMotionAcceleration(20000, timeoutMs);
        } else if (driveInches <= 30) {
            Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configMotionCruiseVelocity(7500, timeoutMs); //7500, 15500, 7500, 15000
            Robot.DRIVE_SUBSYSTEM.talonDriveLeftPrimary.configMotionAcceleration(15500, timeoutMs);
            Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configMotionCruiseVelocity(7500, timeoutMs);
            Robot.DRIVE_SUBSYSTEM.talonDriveRightPrimary.configMotionAcceleration(15000, timeoutMs);
        }

        Robot.DRIVE_SUBSYSTEM.setPID(driveTicks, driveTicks);
    }
    
    protected void execute() {
    }
    
    @Override
    protected boolean isFinished() {
        double leftPosition = Robot.DRIVE_SUBSYSTEM.getLeftPosition();
        double rightPosition = Robot.DRIVE_SUBSYSTEM.getRightPosition();
        
        if (leftPosition > (driveTicks - STOP_THRESHOLD_ADJUSTED) && leftPosition < (driveTicks + STOP_THRESHOLD_ADJUSTED) &&
            rightPosition > (driveTicks - STOP_THRESHOLD_ADJUSTED) && rightPosition < (driveTicks + STOP_THRESHOLD_ADJUSTED)) {
            onTargetCount++;
        } else {
            onTargetCount = 0;
        }
        
        return (onTargetCount > ON_TARGET_MINIMUM_COUNT);
    }
    
    protected void end() {
//        SmartDashboard.putNumber("LEFT FINAL Drive Distance: Inches", Robot.DRIVE_SUBSYSTEM.applyGearRatio(Robot.DRIVE_SUBSYSTEM.convertRevsToInches(Robot.DRIVE_SUBSYSTEM.getLeftPosition())));
//        SmartDashboard.putNumber("RIGHT FINAL Drive Distance: Inches", Robot.DRIVE_SUBSYSTEM.applyGearRatio(Robot.DRIVE_SUBSYSTEM.convertRevsToInches(Robot.DRIVE_SUBSYSTEM.getRightPosition())));
        OI.latestDistanceDriven = Math.abs(Robot.DRIVE_SUBSYSTEM.averageInchesDriven());
//        SmartDashboard.putNumber("Straight", OI.latestDistanceDriven);
        Robot.DRIVE_SUBSYSTEM.enableVBusControl();
        Robot.DRIVE_SUBSYSTEM.resetBothEncoders();
//      Robot.resetNavXAngle();
        Robot.DRIVE_SUBSYSTEM.stop();
    }
    
    protected void interrupted() {
        end();
    }

}
