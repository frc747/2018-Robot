package org.usfirst.frc.team747.robot;


import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team747.robot.Autonomous.AutoMode;
import org.usfirst.frc.team747.robot.autonomous.*;
//import org.usfirst.frc.team747.robot.autonomous.CrossLine;
//import org.usfirst.frc.team747.robot.autonomous.ScoreGear;
//import org.usfirst.frc.team747.robot.autonomous.VisionGear;




public class Autonomous{
    
    public enum AutoMode{
        AUTOMODE_NONE,
        AUTOMODE_RIGHT_SWITCH_FROM_CENTER,
        AUTOMODE_LEFT_SWITCH_FROM_CENTER,
        AUTOMODE_CENTER

    }
    
    private SendableChooser autoChooser1;
//    private SendableChooser autoChooser2;
    
    public Autonomous(){
        autoChooser1 = new SendableChooser();
//        autoChooser2 = new SendableChooser();
        
        autoChooser1.addDefault("No autonomous", AutoMode.AUTOMODE_NONE);
        autoChooser1.addObject("Center, Right Switch", AutoMode.AUTOMODE_RIGHT_SWITCH_FROM_CENTER);
        autoChooser1.addObject("Center, Left Switch", AutoMode.AUTOMODE_LEFT_SWITCH_FROM_CENTER);
        autoChooser1.addObject("Adaptive Center", AutoMode.AUTOMODE_CENTER);
        SmartDashboard.putData("Auto mode", autoChooser1);
    }
    
    public void startMode(){
        
    	System.out.println("In Auto.StartMode");
    	
    	
        AutoMode selectedAutoMode = (AutoMode)(autoChooser1.getSelected());
                    
        switch (selectedAutoMode){
            case AUTOMODE_RIGHT_SWITCH_FROM_CENTER:
                new CenterRightSideSwitch().start();
                break;
            case AUTOMODE_LEFT_SWITCH_FROM_CENTER:
                new CenterLeftSideSwitch().start();
                break;
            case AUTOMODE_CENTER:
                new AutoChooserCenter().start();
                break;
            case AUTOMODE_NONE:
                //DO NOTHING

            default:
                break;
            }
    }
}