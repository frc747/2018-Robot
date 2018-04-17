package org.usfirst.frc.team747.robot.readwrite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.usfirst.frc.team747.robot.Robot;



public class BTMain 
{
	static boolean isRecording = false;
	//autoNumber defines an easy way to change the file you are recording to/playing from, in case you want to make a
	//few different auto programs
	static String autoStringWrite = "L";
	static String autoStringRead;
	//autoFile is a global constant that keeps you from recording into a different file than the one you play from
	public static String autoFileWrite = new String("/home/lvuser/recordedAuto" + autoStringWrite + ".csv");
	public static String autoFileRead;
	private static BTMacroPlay player = null;
	private static BTMacroRecord recorder;
	
    public BTMain() {
    }
    
    public static void autonomousInit() {
    	//during autonomous, create new player object to read recorded file
    	
    	//Robot.partOneFinish = false;
    	
    	//try to create a new player
    	//if there is a file, great - you have a new non-null object "player"
    	
    	autoFileRead = new String("/home/lvuser/recordedAuto" + Robot.gameData.charAt(0) + ".csv");
    	
    	try {
    		 player = new BTMacroPlay();
		} 
    	
		//if not, print out an error
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static void autonomousPeriodic() {
    	//as long as there is a file you found, then use the player to scan the .csv file
		//and set the motor values to their specific motors
		if (player != null) {
			player.play();
		} 
		
    }
	
    public static void disabledInit() {
		//if there is a player and you've disabled autonomous, then flush the rest of the values
		//and stop reading the file
		if(player != null) {
			player.end();
		}
		
		try {
    		if(recorder != null) {
    			recorder.end();
    		}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public static void teleopInit() {
		//lets make a new record object, it will feed the stuff we record into the .csv file
    	recorder = null;
        try {
			recorder = new BTMacroRecord();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void teleopPeriodic() {
    		//the statement in this "if" checks if a button you designate as your record button 
    		//has been pressed, and stores the fact that it has been pressed in a variable
    	//	if (OI.operatorController.getRawButton(5)) {
    		//	isRecording = !isRecording;
			//}  
			//if our record button has been pressed, lets start recording!
			//if (isRecording) {
    			try {
    				//if we succesfully have made the recorder object, lets start recording stuff
    				//2220 uses a storage object that we can get motors values, etc. from.
    				//if you don't need to pass an object like that in, modify the methods/classes
    				if(recorder != null) {
    					recorder.record();
    				}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			//}
    }
}

