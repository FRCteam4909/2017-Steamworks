package org.usfirst.frc4909.STEAMWORKS.commands.loader;

import org.usfirst.frc4909.STEAMWORKS.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PegGear extends Command {
    public PegGear() {
    	requires(Robot.loader);
    }

    protected void initialize() {
    	Robot.loader.initPID();
    }

    protected void execute() {
    	Robot.loader.setPosition(3);
    }

    protected boolean isFinished() {
        return Robot.loader.isFinished();
    }
}