package org.usfirst.frc.team747.robot;


import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team747.robot.autonomous.*;




public class Autonomous{
    
    public enum AutoMode{
        AUTOMODE_NONE,
        AUTOMODE_LEFT,
        AUTOMODE_RIGHT,
//        AUTOMODE_LEFT,
//        AUTOMODE_RIGHT,
        AUTOMODE_CROSS_LINE,
        AUTOMODE_TWO_CUBE_CENTER,
        AUTOMODE_SWERVE,
        AUTOMODE_VAULT
    }
    
    private SendableChooser autoChooser1;
    
    public Autonomous(){
        autoChooser1 = new SendableChooser();
        
        
        autoChooser1.addObject("No autonomous", AutoMode.AUTOMODE_NONE);
        autoChooser1.addObject("Cross Auto Line", AutoMode.AUTOMODE_CROSS_LINE);
        autoChooser1.addObject("Bridgewater Autonomous", AutoMode.AUTOMODE_TWO_CUBE_CENTER);        
        autoChooser1.addObject("Left Wing Autonomous", AutoMode.AUTOMODE_LEFT);
        autoChooser1.addObject("Right Wing Autonomous", AutoMode.AUTOMODE_RIGHT);
        autoChooser1.addDefault("Adaptive Center Arc Drive", AutoMode.AUTOMODE_SWERVE);        
        autoChooser1.addObject("Vault Auto (Agua de Coco 2.0 Baby)", AutoMode.AUTOMODE_VAULT);        

        SmartDashboard.putData("Auto mode", autoChooser1);
    }
    
    public void startMode(){
        
    	System.out.println("In Auto.StartMode");
    	
    	
        AutoMode selectedAutoMode = (AutoMode)(autoChooser1.getSelected());
                    
        switch (selectedAutoMode){
            case AUTOMODE_CROSS_LINE:
            	new CrossAuto().start();
            	break;
            case AUTOMODE_LEFT:
            	new AdaptiveLeftWing().start();
            	break;
            case AUTOMODE_RIGHT:
            	new AdaptiveRightWing().start();
            	break;
            case AUTOMODE_SWERVE: //arc drive
            	new AutoChooserSwerveCenter().start();
            	break;
            case AUTOMODE_VAULT: //vault
                new AutoChooserVault().start();
                break;
            case AUTOMODE_TWO_CUBE_CENTER: //bridgewater code
                new AutoChooserTwoCubeCenter().start();
                break;
            case AUTOMODE_NONE:
                //DO NOTHING

            default:
                break;
            }
    }
}