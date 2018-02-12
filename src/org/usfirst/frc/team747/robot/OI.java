package org.usfirst.frc.team747.robot;

import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
//import org.usfirst.frc.team747.robot.maps.AutonomousConfig;
import org.usfirst.frc.team747.robot.maps.DriverStation;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	
	public static double distance;
	
	 public static final Joystick 
	 //Joysticks control both climb and drive
		JOYSTICK_DRIVER_LEFT = new Joystick(DriverStation.Controller.DRIVER_LEFT.getValue()),
		JOYSTICK_DRIVER_RIGHT = new Joystick(DriverStation.Controller.DRIVER_RIGHT.getValue()),
		CONTROLLER_OPERATOR = new Joystick(DriverStation.Controller.OPERATOR.getValue());
	 
	 public static final JoystickButton
	 	BUTTON_GEAR_INTAKE 
	 		= new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_LB.getValue()),
	 	BUTTON_GEAR_PICK_UP_POSITION
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_A.getValue()),
	 	BUTTON_GEAR_HOME_POSITION
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_Y.getValue()),
	 	BUTTON_GEAR_SCORE_POSITION
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_B.getValue()),
	 	BUTTON_GEAR_TRANSFER_ENCODER_RESET
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_START.getValue()),
	 	BUTTON_GEAR_HOMING_BUTTON
	 	    = new JoystickButton(CONTROLLER_OPERATOR, DriverStation.GamePad.BUTTON_BACK.getValue());
	 
	 public static final JoystickButton 
	     BUTTON_PID_TEST_BUTTON_ONE
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_3.getValue()),
	     BUTTON_PID_TEST_BUTTON_TWO
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_4.getValue()),
	     BUTTON_PID_TEST_BUTTON_THREE
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_5.getValue()),
	     BUTTON_PID_TEST_BUTTON_FOUR
	         = new JoystickButton(JOYSTICK_DRIVER_LEFT, DriverStation.Joystick.BUTTON_6.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_ONE
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_3.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_TWO
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_4.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_THREE
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_5.getValue()),
	     BUTTON_PID_TEST_REVERSE_BUTTON_FOUR
	         = new JoystickButton(JOYSTICK_DRIVER_RIGHT, DriverStation.Joystick.BUTTON_6.getValue());
	 
    static Preferences prefs;
    
	public OI() {
		new Notifier(() -> updateOI()).startPeriodic(0.1); //value in seconds
		
		BUTTON_PID_TEST_REVERSE_BUTTON_ONE.toggleWhenPressed(new PIDDriveInchesCommand(OI.distance, false));
	}
	
	public void updateOI() {
		SmartDashboard.putNumber("tx", Robot.x);
		SmartDashboard.putNumber("tv", Robot.v);
		SmartDashboard.putNumber("ty", Robot.y);
		SmartDashboard.putNumber("distance", OI.distance);
	}

	
}
