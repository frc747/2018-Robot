package org.usfirst.frc.team747.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeSubsystem extends Subsystem {

	public static TalonSRX intakeRoller; // = new TalonSRX(?);
	
	public static TalonSRX intakeLeft; // = new TalonSRX(?);
	public static TalonSRX intakeRight; // = new TalonSRX(?);
	
	public static TalonSRX ejectLeft; // = new TalonSRX(?);
	public static TalonSRX ejectRight; // = new TalonSRX(?);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public CubeSubsystem() {
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

