package org.usfirst.frc4909.STEAMWORKS.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4909.STEAMWORKS.Robot;
import org.usfirst.frc4909.STEAMWORKS.RobotMap;
import org.usfirst.frc4909.STEAMWORKS.PID.PIDController;
import org.usfirst.frc4909.STEAMWORKS.commands.*;

import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends Subsystem {
    private double rotateP = 0.15;
	private double rotateI = 0.00000;
	private double rotateD = 0.00000;

    private double navP = 0.0002;
	private double navI = 0.00000;
	private double navD = 0.00000;
	
	private double encP = 0.15;
	private double encI = 0.00000;
	private double encD = 0.00000;

	//in inches
	private double wheelDiameter = 4.0;
	
	private double pulsesPerRev = 1440.0;

    private final SpeedController leftFrontDriveMotorController = RobotMap.drivetrainLeftFrontDriveMotorController;
    private final SpeedController leftBackDriveMotorController = RobotMap.drivetrainLeftBackDriveMotorController;
    private final SpeedController rightBackDriveMotorController = RobotMap.drivetrainRightBackDriveMotorController;
    private final SpeedController rightFrontDriveMotorController = RobotMap.drivetrainRightFrontDriveMotorController;
    
    private final Encoder leftEncoder = RobotMap.drivetrainLeftEncoder;
    private final Encoder rightEncoder = RobotMap.drivetrainRightEncoder;
    
    private final RobotDrive robotDrive = RobotMap.drivetrainRobotDrive;

    private final PIDController rotatePID = new PIDController(rotateP, rotateI, rotateD,.5);
    private final PIDController navxPID = new PIDController(navP, navI, navD,.6);
<<<<<<< HEAD
    private final PIDController encPID = new PIDController(encP, encI, encD,.6);
=======
    private final PIDController encPID = new PIDController(encP, encI, encD,.8);

>>>>>>> master
    
    private final AHRS navx = RobotMap.navx;

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
    
    public void moveTank(){
    	double leftY = Robot.oi.getLeftDriveY();
    	double rightY = Robot.oi.getRightDriveY();
    	robotDrive.tankDrive(leftY, rightY);
    }
    
    /**
     * 
     * @param angle Angle in degrees
     */
    public void rotateAngle(double angle){
    	rotatePID.resetPID();
        
    	SmartDashboard.putBoolean("rotate", true);
    	
        double targetTime=Timer.getFPGATimestamp();    	
        while(Timer.getFPGATimestamp()-targetTime<.5){
    		rotatePID.atTarget=false;
        	rotateP=SmartDashboard.getNumber("rP", 0);
        	rotateI=SmartDashboard.getNumber("rI", 0);
        	rotateD=SmartDashboard.getNumber("rD", 0);
        	rotatePID.changePIDGains(rotateP, rotateI, rotateD);

    		double currentAngle=getYaw();
    		if(Math.abs(angle-currentAngle) > 180)
    			angle = angle + (currentAngle / Math.abs(currentAngle)) * 360;
            
    		SmartDashboard.putNumber("current angle",currentAngle);
    		SmartDashboard.putNumber("PID out",rotatePID.calcPID(angle, currentAngle, 4));

    		robotDrive.arcadeDrive(0,rotatePID.calcPID(angle, currentAngle, 4));
    		if(!rotatePID.isDone())
                targetTime=Timer.getFPGATimestamp();
    	}
    	
    	SmartDashboard.putBoolean("rotate", false);
    }
    
    /**
     * 
     * @param dist Distance in inches
     */
    public void driveStraightNavX(double dist){
    	navxPID.resetPID();
    	navx.resetDisplacement();
        
    	SmartDashboard.putNumber("nP", navP);
    	SmartDashboard.putNumber("nI", navI);
    	SmartDashboard.putNumber("nD", navD);

    	double targetTime=Timer.getFPGATimestamp();
    	while(Timer.getFPGATimestamp()-targetTime<.5){
    		navxPID.atTarget=false;
        	navP=SmartDashboard.getNumber("nP", 0);
        	navI=SmartDashboard.getNumber("nI", 0);
        	navD=SmartDashboard.getNumber("nD", 0);
        	navxPID.changePIDGains(navP, navI, navD);

    		double currentDist=getDisplacementY();
    		SmartDashboard.putNumber("current dist",currentDist);
    		SmartDashboard.putNumber("nPID out",navxPID.calcPID(dist, currentDist, .05));

    		robotDrive.arcadeDrive(-navxPID.calcPID(dist, currentDist, .05), 0);
    		if(!navxPID.isDone())
                targetTime=Timer.getFPGATimestamp();
    	}
    	
    	SmartDashboard.putBoolean("straightNav", false);
    }
    
    /**
     * 
     * @param dist Distance in inches
     */
    public void driveStraightEncoder(double dist){
    	encPID.resetPID();
    	leftEncoder.reset();
    	rightEncoder.reset();
        
    	SmartDashboard.putBoolean("straightEnc", true);
    	SmartDashboard.putNumber("eP", encP);
    	SmartDashboard.putNumber("eI", encI);
    	SmartDashboard.putNumber("eD", encD);
        
    	double startAngle = getAngle();
    	double maxP=0;
    	double LPower=0;
    	double RPower=0;
    	double targetTime=Timer.getFPGATimestamp();
    	while(Timer.getFPGATimestamp()-targetTime<.5){
    		maxP+=.005;
    		encPID.atTarget=false;
        	encP=SmartDashboard.getNumber("eP", 0);
        	encI=SmartDashboard.getNumber("eI", 0);
        	encD=SmartDashboard.getNumber("eD", 0);
        	encPID.changePIDGains(encP, encI, encD);

<<<<<<< HEAD
    		double currentDistL = getLeftEncDistance();
    		double currentDistR = getRightEncDistance();
=======
    		double currentDistL=getLeftEncDistance();
    		double currentDistR=getRightEncDistance();
>>>>>>> master


    		SmartDashboard.putNumber("current distance",currentDistL);
    		SmartDashboard.putNumber("current distance",currentDistR);

<<<<<<< HEAD
    		//robotDrive.tankDrive(-encPID.calcPID(dist, currentDistL, 2),-encPID.calcPID(dist, currentDistR, 2));    		
    		robotDrive.arcadeDrive(-encPID.calcPID(dist, (currentDistL+currentDistR)/2, 2), 0);//-(getAngle()-startAngle)/4

    		if(!encPID.isDone())
                targetTime=Timer.getFPGATimestamp();
=======
    		LPower=-encPID.calcPID(dist, currentDistL, 2);
    		RPower=-encPID.calcPID(dist, currentDistR, 2);
    		if(Math.abs(LPower)>maxP)
    			LPower=maxP*Math.signum(LPower);
    		if(Math.abs(RPower)>maxP)
    			RPower=maxP*Math.signum(RPower);
    		SmartDashboard.putNumber("encPID L out",LPower);
    		SmartDashboard.putNumber("encPID R out",RPower);


    		//robotDrive.arcadeDrive(power,rotatePID.calcPID(startAngle, getAngle(), 1));//)/4
    		robotDrive.tankDrive(LPower,RPower*.95);

    		
    		if(!encPID.isDone()){
    				targetTime=Timer.getFPGATimestamp();
    		
    		}
>>>>>>> master
    	}
    	
    	SmartDashboard.putBoolean("straightEnc", false);
    }
    
    /**
     * 
     * @param dist Distance in inches
     */
    public void driveUnevenEncoder(double dist){
    	encPID.resetPID();
    	leftEncoder.reset();
    	rightEncoder.reset();

    	SmartDashboard.putNumber("eP", encP);
    	SmartDashboard.putNumber("eI", encI);
    	SmartDashboard.putNumber("eD", encD);

    	double targetTime = Timer.getFPGATimestamp();
    	while(Timer.getFPGATimestamp()-targetTime<.5){
    		encPID.atTarget=false;
        	encP=SmartDashboard.getNumber("eP", 0);
        	encI=SmartDashboard.getNumber("eI", 0);
        	encD=SmartDashboard.getNumber("eD", 0);
        	encPID.changePIDGains(encP, encI, encD);

    		double currentDist=0;
    		SmartDashboard.putNumber("current dist",currentDist);
    		SmartDashboard.putNumber("nPID out",encPID.calcPID(dist, currentDist, .05));

    		robotDrive.arcadeDrive(-encPID.calcPID(dist, currentDist, .05), 0);
    		if(!encPID.isDone())
                targetTime=Timer.getFPGATimestamp();
    	}
    	
    	SmartDashboard.putBoolean("straight", false);
    }
    
    // NavX Functions
    public double getYaw(){
    	return navx.getYaw();
    }
    
    public double getAngle(){
    	return navx.getAngle();
    }
    
    public double getDisplacementX(){
    	return navx.getDisplacementX()*39.37007874;
    }
    public double getDisplacementY(){
    	return navx.getDisplacementY()*39.37007874;
    }
    public double getDisplacementZ(){
    	return navx.getDisplacementZ()*39.37007874;
    }
    
    // Return Encoder Distance in inches
    public double getLeftEncDistance(){
    	return (leftEncoder.getRaw()/pulsesPerRev)*(Math.PI*wheelDiameter);
    }
    public double getRightEncDistance(){
    	return (rightEncoder.getRaw()/pulsesPerRev)*(Math.PI*wheelDiameter);
    }
    
    public void stop(){
    	robotDrive.drive(0, 0);
    }
}