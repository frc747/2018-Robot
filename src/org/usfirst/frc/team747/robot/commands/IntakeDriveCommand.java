package org.usfirst.frc.team747.robot.commands;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.Robot;
import org.usfirst.frc.team747.robot.maps.ControllerMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeDriveCommand extends Command {

    private static final double MAX_PERCENT_VOLTAGE = 1.0;
    private static final double MIN_PERCENT_VOLTAGE = 0.0;

    private static final int timeoutMs = 10;
    
    public IntakeDriveCommand() {
        requires(Robot.cube);
    }

    // Called just befoe this Command runs the first time
    protected void initialize() {
        Robot.cube.intakeLeft.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.intakeLeft.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.intakeLeft.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.intakeLeft.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.intakeRight.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.intakeRight.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.intakeRight.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.intakeRight.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.roller.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.roller.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.roller.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        Robot.cube.roller.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
         double left = -OI.operatorController.getRawAxis(ControllerMap.GamePad.AXIS_LEFT_Y.getValue());
         double right = OI.operatorStickMod * OI.operatorController.getRawAxis(ControllerMap.GamePad.AXIS_RIGHT_Y.getValue());

         if (Math.abs(left) < 0.1) {
             left = 0;
         }
         if (Math.abs(right) < 0.1) {
             right = 0;
         }
         
         
         double speed = 1;
         
         Robot.cube.leftIntakeArm.set(ControlMode.PercentOutput, -left * speed);     
         Robot.cube.rightIntakeArm.set(ControlMode.PercentOutput, right * speed);         

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