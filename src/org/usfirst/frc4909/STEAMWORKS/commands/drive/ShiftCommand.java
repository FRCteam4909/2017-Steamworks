package org.usfirst.frc4909.STEAMWORKS.commands.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4909.STEAMWORKS.Robot;

public class ShiftCommand extends InstantCommand {
    public ShiftCommand() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Robot.drivetrain.robotDrive.shift();
    	
    	SmartDashboard.putNumber("Times Shifted", SmartDashboard.getNumber("Times Shifted", 0) + 1);
    }
}