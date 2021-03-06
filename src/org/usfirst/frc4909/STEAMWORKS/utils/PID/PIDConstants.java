package org.usfirst.frc4909.STEAMWORKS.utils.PID;

public class PIDConstants {
	public double p = 0;
	public double i = 0;
	public double d = 0;
	public double max = 0;
	
	public PIDConstants(double pConst, double iConst, double dConst){
		p = pConst;
		i = iConst;
		d = dConst;
		
		max = 1.0;
	}
	
	public PIDConstants(double pConst, double iConst, double dConst, double maxConst){
		p = pConst;
		i = iConst;
		d = dConst;
		
		max = maxConst;
	}
}
