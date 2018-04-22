package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.OI;
import org.usfirst.frc.team747.robot.commands.IntakeDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeSubsystem extends Subsystem {
	
	public TalonSRX intakeLeft = new TalonSRX(4); // = new TalonSRX(?); 5
	public TalonSRX intakeRight = new TalonSRX(7); // = new TalonSRX(?); 6 // 7 is reversed
	
	public TalonSRX ejectLeft = new TalonSRX(5); // = new TalonSRX(?); 7
	public TalonSRX ejectRight = new TalonSRX(6); // = new TalonSRX(?); 8
	
	public TalonSRX roller = new TalonSRX(11);
	
	public TalonSRX leftIntakeArm = new TalonSRX(12);
	public TalonSRX rightIntakeArm = new TalonSRX(13);

	
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
	
	public void setIntakeArms(boolean enable, boolean reverse) {
		int mod;
		if (reverse == true) {
			mod = -1;
		} else {
			mod = 1;
		}
		if (enable) {
			leftIntakeArm.set(ControlMode.PercentOutput, OI.intLeft * 1.0 * mod);
			rightIntakeArm.set(ControlMode.PercentOutput, OI.intRight * 1.0 * mod);
//			roller.set(ControlMode.PercentOutput, OI.rol * -1.0 * mod);
		} else {
			intakeLeft.set(ControlMode.PercentOutput, 0);
			intakeRight.set(ControlMode.PercentOutput, 0);
//			roller.set(ControlMode.PercentOutput, 0);
		}
	}
	
	public void setIntake(boolean enable, boolean reverse) {
		int mod;
		if (reverse == true) {
			mod = -1;
		} else {
			mod = 1;
		}
		if (enable) {
			intakeLeft.set(ControlMode.PercentOutput, OI.intLeft * 1.0 * mod);
			intakeRight.set(ControlMode.PercentOutput, OI.intRight * 1.0 * mod);
//			roller.set(ControlMode.PercentOutput, OI.rol * -1.0 * mod);
		} else {
			intakeLeft.set(ControlMode.PercentOutput, 0);
			intakeRight.set(ControlMode.PercentOutput, 0);
//			roller.set(ControlMode.PercentOutput, 0);
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
			ejectLeft.set(ControlMode.PercentOutput, OI.extLeft * 0.5 * mod);
			ejectRight.set(ControlMode.PercentOutput, OI.extRight * 0.5 * mod);
		} else {
			ejectLeft.set(ControlMode.PercentOutput, 0);
			ejectRight.set(ControlMode.PercentOutput, 0);
		}
	}
	
	public void setRollers(boolean enable, boolean reverse) {
        int mod;
        if (reverse == true) {
            mod = -1;
        } else {
            mod = 1;
        }
        if (enable) {
            roller.set(ControlMode.PercentOutput, OI.rol * -1.0 * mod);
        } else {
            roller.set(ControlMode.PercentOutput, 0);            
        }
    }
}

