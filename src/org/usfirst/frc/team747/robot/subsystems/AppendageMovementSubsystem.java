package org.usfirst.frc.team747.robot.subsystems;

import org.usfirst.frc.team747.robot.commands.AppendageMovementCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AppendageMovementSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public TalonSRX talonLeftMotor = new TalonSRX(6), //Gear Intake ("Bag Motor 1")
                    talonRightMotor = new TalonSRX(7); //Gear Transfer ("Bag Motor 2")
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new AppendageMovementCommand());
    }
    
    public void set(double left, double right) {
        this.talonLeftMotor.set(ControlMode.PercentOutput, left);
        this.talonRightMotor.set(ControlMode.PercentOutput, right);
    }
}

