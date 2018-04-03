/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team747.readwrite.BTMain;
import org.usfirst.frc.team747.robot.subsystems.CubeSubsystem;
import org.usfirst.frc.team747.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team747.robot.subsystems.PneumaticsSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem();
	public static final CubeSubsystem cube = new CubeSubsystem();
	public static final PneumaticsSubsystem pneu = new PneumaticsSubsystem();
	public static boolean switchb = true;

    public static OI oi = null;
	
	public static String gameData;
	private Command autonomousCommand;
	private Autonomous autonomous;
//	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
    private static final AHRS NAV_X = new AHRS (SPI.Port.kMXP);
    
    public static double getNavXAngle() {
    	return NAV_X.getYaw();
    }
    
    public static double getNavXAngleRadians() {
    	return Math.toRadians(getNavXAngle());
    }
    
    public static void resetNavXAngle() {
    	NAV_X.zeroYaw();
    	try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

	    DRIVE_SUBSYSTEM.changeControlMode(ControlMode.PercentOutput);
	    
        UsbCamera ucamera = CameraServer.getInstance().startAutomaticCapture("cam1", 0);
        ucamera.setResolution(180, 240);
        
        pneu.leftHIGH.set(false);
        pneu.rightHIGH.set(true);
        Robot.switchb = true;
        
	    this.autonomous = new Autonomous();
        
        if (oi == null) {
            oi = new OI();
        }
        
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	    
        pneu.leftHIGH.set(false);
        pneu.rightHIGH.set(true);
        Robot.switchb = true;
        BTMain.disabledInit();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		resetNavXAngle();
		
		DRIVE_SUBSYSTEM.resetBothEncoders();
		
		pneu.leftHIGH.set(false);
		pneu.rightHIGH.set(true);
		Robot.switchb = true;
        
//		autonomous.startMode();
//        if (autonomousCommand != null) {
//            autonomousCommand.start();
//        }
//        
//        if (oi == null) {
//            oi = new OI();
//        }
        BTMain.autonomousInit();
        
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
//		Scheduler.getInstance().run();
		BTMain.autonomousPeriodic();
	}

	@Override
	public void teleopInit() {
//	    resetNavXAngle();
	    Robot.DRIVE_SUBSYSTEM.resetBothEncoders();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

	    pneu.leftHIGH.set(false);
		pneu.rightHIGH.set(true);
		Robot.switchb = true;
		
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		
		BTMain.teleopInit();
		
//		DriveSubsystem.talonDriveLeftPrimary.enableCurrentLimit(false);
//		DriveSubsystem.talonDriveRightPrimary.enableCurrentLimit(false);
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		BTMain.teleopPeriodic();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
