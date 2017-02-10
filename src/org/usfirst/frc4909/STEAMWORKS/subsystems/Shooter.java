package org.usfirst.frc4909.STEAMWORKS.subsystems;

import org.usfirst.frc4909.STEAMWORKS.RobotMap;
import org.usfirst.frc4909.STEAMWORKS.utils.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter extends Subsystem {
	private CANTalon shooterMotorController = RobotMap.shooterMotorController;
	
	public void setRPM(double rpm) {
		shooterMotorController.changeControlMode(TalonControlMode.Speed);
		shooterMotorController.set(rpm);
	}
	
	public void setVoltage(double percent) {
		shooterMotorController.changeControlMode(TalonControlMode.PercentVbus);
		shooterMotorController.set(percent);
	}
	
	public double getRPM(){
		return shooterMotorController.getEncVelocity();
	}
}