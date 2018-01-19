package org.usfirst.frc.team747.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Auton {
	static int timeoutMs = 10;
	static int kP;
	static int kI;
	static int kD;
	static int izone = 50;
	public static void autonInit() {
		Robot.leftFrontDrive.set(ControlMode.MotionMagic, 10); //it will hone onto 
		//that position to the best of its ability until you take it out of motion magic mode or change what position (in ticks) that you want to go to		
		Robot.leftFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeoutMs);
        
		Robot.leftFrontDrive.configNominalOutputForward(0, timeoutMs);
		Robot.leftFrontDrive.configNominalOutputReverse(0, timeoutMs);
		Robot.leftFrontDrive.configPeakOutputForward(0, timeoutMs);
		Robot.leftFrontDrive.configPeakOutputReverse(0, timeoutMs);
        
		Robot.leftFrontDrive.config_IntegralZone(0, izone, timeoutMs);
		Robot.leftFrontDrive.configMaxIntegralAccumulator(0, arg1, timeoutMs);
        
		Robot.leftFrontDrive.configMotionAcceleration(0, timeoutMs);
		Robot.leftFrontDrive.configMotionCruiseVelocity(0, timeoutMs);

		Robot.leftFrontDrive.config_kP(0, 1.5, timeoutMs);
		Robot.leftFrontDrive.config_kI(0, .08, timeoutMs);
		Robot.leftFrontDrive.config_kD(0, 60, timeoutMs);
		Robot.leftFrontDrive.config_kF(0, arg1, timeoutMs); 
	}
	
	public static void autonPeriodic() {
		
	}
}
