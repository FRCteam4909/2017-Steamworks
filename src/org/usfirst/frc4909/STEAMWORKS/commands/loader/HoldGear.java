package org.usfirst.frc4909.STEAMWORKS.commands.loader;

import org.usfirst.frc4909.STEAMWORKS.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoldGear extends Command {
    public HoldGear() {
    	requires(Robot.loader);
    }

    protected void initialize() {
    	Robot.loader.targetPosition = 0;
    	SmartDashboard.putString("Loader Position", "Hold");

    }

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }
}
