package org.usfirst.frc4909.STEAMWORKS.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4909.STEAMWORKS.Robot;
import org.usfirst.frc4909.STEAMWORKS.RobotMap;
import org.usfirst.frc4909.STEAMWORKS.commands.*;

public class Climber extends Subsystem {
    private final SpeedController climberMotorController = RobotMap.climberClimberMotorController;
    private final Encoder climberEncoder = RobotMap.climberClimberEncoder;

    private final PowerDistributionPanel PDP = RobotMap.PDP;

    public void initDefaultCommand() {
    	setDefaultCommand(new ClimbCommand());
    }
    
    public void climb(double speed){
    	SmartDashboard.putNumber("Current",PDP.getCurrent(11));
    	
    	climberMotorController.set(speed);	
    }
}