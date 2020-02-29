/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
// import jdk.internal.jshell.tool.resources.l10n;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Hatch m_hatchsub = new Hatch();
  public final Wheel m_wheel = new Wheel();
  public Joystick joystick1 = new Joystick(0);
  // Fix Me 
  private final HatchReset m_autoCommand = new HatchReset(m_hatchsub);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton joystickButton1, button2;
    joystickButton1 = new JoystickButton(joystick1, 1);
    button2 = new JoystickButton(joystick1, 2);
    joystickButton1.whenPressed(new HatchGrab(m_hatchsub));
    button2.whenHeld(new HatchReset(m_hatchsub));
    
    // SmartDashboard Buttons
    // SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
    SmartDashboard.putData("HatchGrab", new HatchGrab(m_hatchsub));
    SmartDashboard.putData("HatchReset", new HatchReset(m_hatchsub));
    SmartDashboard.putData("ReadColor", new ReadWheel(m_wheel));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
