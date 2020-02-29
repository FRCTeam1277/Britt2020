/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hatch extends SubsystemBase {
  private PWMVictorSPX m_motor = new PWMVictorSPX(8);
  private Encoder m_encoder = new Encoder(2,3);
  private PIDController m_controller  = new PIDController(.005, 0.001, 0);
  private double target = 0;
  /**
   * Creates a new Hatch.
   */
  public Hatch() {
    m_controller.setTolerance(2);
    SmartDashboard.putData("HatchPid", m_controller);
    addChild("Hatch Encoder 1",m_encoder);
    m_motor.set(0);
    m_encoder.reset();
  }

  // Manual setting for commands that needs.
  public void set_speed(double speed){
    m_motor.set(speed);
  }
  // Set the current target of the pid controller
  public void set_target (double target) {
    m_controller.setSetpoint(target);
    m_controller.reset();
  }

  // Calling this makes it go between up and down every button press.
  public void toggle() {
    if (target == 0) {
      target = 235;
    } else {
      target = 0;
    }
    m_controller.setSetpoint(target);
    m_controller.reset();
  }

  // Called from the command execute function to keep running.
  public void run_pid () {
      int location = m_encoder.get();
      SmartDashboard.putNumber("Hatch Encoder", location);
      double new_speed = m_controller.calculate(location);
      SmartDashboard.putNumber("Hatch Speed", new_speed);
      m_motor.set(new_speed);
  }

  // Called by isFinished to know when the target is found
  public boolean complete() {
    boolean isComplete = m_controller.atSetpoint();
    SmartDashboard.putBoolean("Complete", isComplete);
    return isComplete;
  }

  // Command it complete, trun off the motor
  public void stop() {
    m_motor.set(0);
    
  }

  public void reset_encoder(){
    m_encoder.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
