package org.usfirst.frc4909.STEAMWORKS.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc4909.STEAMWORKS.Robot;

public class ShiftUpCommand extends InstantCommand {
    public ShiftUpCommand() {
        requires(Robot.drivetrain);
    }

    protected void initialize() {}
}