/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team747.robot;


import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.usfirst.frc.team747.robot.commands.EjectCommand;
import org.usfirst.frc.team747.robot.commands.ForwardGroup;
import org.usfirst.frc.team747.robot.commands.IntakeCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveInchesCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommand;
import org.usfirst.frc.team747.robot.commands.PIDDriveRotateCommandVision;
import org.usfirst.frc.team747.robot.commands.ReverseGroup;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick operatorController = new Joystick(2);
	public static double degrees;
	Button OP_A = new JoystickButton(operatorController, 1);
	Button OP_B = new JoystickButton(operatorController, 2);
	Button OP_X = new JoystickButton(operatorController, 3);
	Button OP_Y = new JoystickButton(operatorController, 4);
	Button OP_START = new JoystickButton(operatorController, 8);
	Button OP_PRESS = new JoystickButton(operatorController, 9);
	
	private Random randGen = new Random();
	static TimeSeries ts = new TimeSeries("data", Millisecond.class);
	
	public OI() {
		new Notifier(() -> updateOI()).startPeriodic(.1);
		
		/*
		OP_A.whileHeld(new IntakeCommand(false));
		OP_B.whileHeld(new EjectCommand(false));
		OP_X.toggleWhenPressed(new PIDDriveRotateCommand(-180));
		OP_Y.toggleWhenPressed(new PIDDriveInchesCommand(Robot.distance, false));
		OP_START.toggleWhenPressed(new PIDDriveRotateCommand(OI.getDegrees()));
		*/
		
		OP_Y.whileHeld(new ForwardGroup());
		OP_A.whileHeld(new ReverseGroup());
		OP_X.whileHeld(new IntakeCommand(false));
		OP_START.toggleWhenPressed(new PIDDriveInchesCommand(1, false));
		

        TimeSeriesCollection dataset = new TimeSeriesCollection(ts);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "GraphTest",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );
        final XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);

        JFrame frame = new JFrame("GraphTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel label = new ChartPanel(chart);
        frame.getContentPane().add(label);
        //Suppose I add combo boxes and buttons here later

        frame.pack();
        frame.setVisible(true);
	}
	
	public void updateOI() {
	    SmartDashboard.putNumber("Value of TX:", getDegrees());
	    SmartDashboard.putNumber("Value of TV:", Robot.v);
	    SmartDashboard.putNumber("Distance:", Robot.distance);
	    
	    int num = randGen.nextInt(1000);
        System.out.println(num);
        ts.addOrUpdate(new Millisecond(), num);

	}
	
	public static double getDegrees() {
		return OI.degrees;
	}
}
