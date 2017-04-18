package org.team4909.boxtop.subsystems;

import org.team4909.boxtop.RobotMap;
import org.team4909.utils.PID.Position.EasyPotentiometerPIDController;
import org.team4909.utils.PID.Position.EasyPotentiometerPIDSubsystem;

public class Loader extends EasyPotentiometerPIDSubsystem {
    public EasyPotentiometerPIDController getPotentiometerPIDController(){
    	return RobotMap.loaderPotPIDController;
    }

	protected void initDefaultCommand() {}
}