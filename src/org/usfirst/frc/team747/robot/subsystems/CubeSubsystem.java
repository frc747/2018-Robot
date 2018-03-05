package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.commands.IntakeDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeSubsystem extends Subsystem {

	public TalonSRX intakeArmLeft = new TalonSRX(3); // = new TalonSRX(?); 4
	public TalonSRX intakeArmRight = new TalonSRX(8); 
	
	public TalonSRX intakeLeft = new TalonSRX(4); // = new TalonSRX(?); 5
	public TalonSRX intakeRight = new TalonSRX(7); // = new TalonSRX(?); 6 // 7 is reversed
	
	public TalonSRX ejectLeft = new TalonSRX(5); // = new TalonSRX(?); 7
	public TalonSRX ejectRight = new TalonSRX(6); // = new TalonSRX(?); 8
	
	public TalonSRX roller = new TalonSRX(11);
	
    private static final double MAX_PERCENT_VOLTAGE = 1.0;
    private static final double MIN_PERCENT_VOLTAGE = 0.0;
    
    private static final int timeoutMs = 10;
	

	public CubeSubsystem() {
		super();
        this.intakeLeft.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        this.intakeLeft.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        this.intakeLeft.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        this.intakeLeft.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        this.intakeRight.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        this.intakeRight.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        this.intakeRight.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        this.intakeRight.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
        this.roller.configNominalOutputForward(+MIN_PERCENT_VOLTAGE, timeoutMs);
        this.roller.configNominalOutputReverse(-MIN_PERCENT_VOLTAGE, timeoutMs);
        this.roller.configPeakOutputForward(+MAX_PERCENT_VOLTAGE, timeoutMs);
        this.roller.configPeakOutputReverse(-MAX_PERCENT_VOLTAGE, timeoutMs);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new IntakeDriveCommand());
    }
	
	public void intakeArms(double left, double right) {
	    //TODO Based on a conversation with (Jeff and George 3/3/2018 2:35), the speeds for intake motors while being controlled by joysticks are unknown. Setting to 75% speeds by default.
	    intakeLeft.set(ControlMode.PercentOutput, (left * 0.75));
	    intakeRight.set(ControlMode.PercentOutput, (-right * 0.75));
	    roller.set(ControlMode.PercentOutput, (left+right)/2);
	}
	
	public void setIntake(boolean enable, boolean reverse) {
		int mod;
		if (reverse == true) {
			mod = -1;
		} else {
			mod = 1;
		}
		if (enable) {
			intakeLeft.set(ControlMode.PercentOutput, -.5*mod);
			intakeRight.set(ControlMode.PercentOutput, .5*mod);
			roller.set(ControlMode.PercentOutput, -.5*mod);
		} else {
			intakeLeft.set(ControlMode.PercentOutput, 0);
			intakeRight.set(ControlMode.PercentOutput, 0);
			roller.set(ControlMode.PercentOutput, 0);
			
		}
	}
	
	public void setEject(boolean enable, boolean reverse) {
		int mod;
		if (reverse == true) {
			mod = -1;
		} else {
			mod = 1;
		}
		if (enable) {
			ejectLeft.set(ControlMode.PercentOutput, -.5*mod);
			ejectRight.set(ControlMode.PercentOutput, .5*mod);
		} else {
			ejectLeft.set(ControlMode.PercentOutput, 0);
			ejectRight.set(ControlMode.PercentOutput, 0);
		}
	}
	
}

