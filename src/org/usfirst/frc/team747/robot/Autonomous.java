package org.usfirst.frc.team747.robot;


import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team747.robot.autonomous.*;




public class Autonomous{
    
    public enum AutoMode{
        AUTOMODE_NONE,
//        AUTOMODE_LEFT,
//        AUTOMODE_RIGHT,
        AUTOMODE_CENTER,
        AUTOMODE_CROSS_LINE,
        AUTOMODE_DIAGONAL_CENTER,
        AUTOMODE_TWO_CUBE_CENTER,
        AUTOMODE_SWERVE
    }
    
    private SendableChooser autoChooser1;
    
    public Autonomous(){
        autoChooser1 = new SendableChooser();
        
        autoChooser1.addDefault("No autonomous", AutoMode.AUTOMODE_NONE);
//        autoChooser1.addObject("Adaptive Left", AutoMode.AUTOMODE_LEFT);
        autoChooser1.addObject("Adaptive Center", AutoMode.AUTOMODE_CENTER);
        autoChooser1.addObject("SWERVE AUTO", AutoMode.AUTOMODE_SWERVE);

//        autoChooser1.addObject("Adaptive Right", AutoMode.AUTOMODE_RIGHT);
        autoChooser1.addObject("Cross Auto Line", AutoMode.AUTOMODE_CROSS_LINE);
        autoChooser1.addObject("Adaptive Diagonal Center", AutoMode.AUTOMODE_DIAGONAL_CENTER);
        autoChooser1.addObject("Adaptive Two Cube Center", AutoMode.AUTOMODE_TWO_CUBE_CENTER);        
        SmartDashboard.putData("Auto mode", autoChooser1);
    }
    
    public void startMode(){
        
    	System.out.println("In Auto.StartMode");
    	
    	
        AutoMode selectedAutoMode = (AutoMode)(autoChooser1.getSelected());
                    
        switch (selectedAutoMode){
            case AUTOMODE_CENTER:
                new AutoChooserCenter().start();
                break;
//            case AUTOMODE_LEFT:
//            	new AutoChooserLeft().start();
//            	break;
//            case AUTOMODE_RIGHT:
//            	new AutoChooserRight().start();
//            	break;
            case AUTOMODE_CROSS_LINE:
            	new CrossAuto().start();
            	break;
            case AUTOMODE_SWERVE:
            	new AutoChooserSwerveCenter().start();
            	break;
            case AUTOMODE_DIAGONAL_CENTER:
                new AutoChooserDiagonalCenter().start();
                break;
            case AUTOMODE_TWO_CUBE_CENTER:
                new AutoChooserTwoCubeCenter().start();
                break;
            case AUTOMODE_NONE:
                //DO NOTHING

            default:
                break;
            }
    }
}