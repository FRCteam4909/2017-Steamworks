package org.usfirst.frc4909.STEAMWORKS.commands.climb;

import org.usfirst.frc4909.STEAMWORKS.utils.Command;
import org.usfirst.frc4909.STEAMWORKS.Robot;

public class ClimbCommand extends Command {
    public ClimbCommand() {
        requires(Robot.climber);
    }

    protected void execute() {
    	Robot.climber.climb(1.0);
    }
    
    protected void end() {
    	Robot.climber.climb(0);
    }
}