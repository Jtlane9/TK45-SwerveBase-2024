package frc.robot;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
//import com.revrobotics.CANSparkMax.IdleMode;      // 2023 - Deprecated
import com.revrobotics.CANSparkBase.IdleMode;           // ^ 2024 - Replacement

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSFalconSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants 
{
    public static final double stickDeadband = 0.1;

    /* Intake Constants */
    public static final int IntakeLeftID = 40;
    public static final int IntakeRightID = 41; //f

    /* Arm Constants */   
    public static final int ArmID = 60;
    public static final int ArmEncoderID = 61;

    public static final double ARM_P = .02;  // TODO - REMOVE
    public static final double ARM_I = 0;    // TODO - REMOVE
    public static final double ARM_D = 0;    // TODO - REMOVE
    
    public static final double ARM_REVERSE_LIMIT = 45;      // TK 45 - CHANGE VALUE TO ACTUAL REVERSE LIMIT // TODO - REMOVE
    public static final double ARM_FORWARD_LIMIT = -45;     // TK 45 - CHANGE VALUE TO ACTUAL FORWARD LIMIT  // TODO - REMOVE

    /* Arm Presets */
    public static final double ARM_LOW_FRONT_SCORE = 0;   // TK 45 - Change Value to actual front low preset    // TODO - REMOVE
    public static final double ARM_MID_FRONT_SCORE = 60;    // TK 45 - Change Value to actual front low preset  // TODO - REMOVE
    public static final double ARM_TOP = 95;    // TODO - REMOVE

    public static double gyroOffset = 0;

    public static final class Swerve 
    {
        public static final int pigeonID = 50;
        public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW- (DO NOT USE, ENABLES ROBOT-CENTRIC)

        public static final COTSFalconSwerveConstants chosenModule =  
            COTSFalconSwerveConstants.SDSMK4i(COTSFalconSwerveConstants.driveGearRatios.SDSMK4i_L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(22);   // TODO - UPDATE
        public static final double wheelBase = Units.inchesToMeters(22);    // TODO - UPDATE
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
        public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics
        (
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0), 
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0)
        );

        /* Swerve Voltage Compensation */
        public static final double voltageComp = 12.0;

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
        public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final boolean canCoderInvert = chosenModule.canCoderInvert;

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 25;
        public static final int anglePeakCurrentLimit = 40;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 35;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive NEO to ramp in open loop and closed loop driving.
        * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;
        public static final double angleKF = chosenModule.angleKF;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.05; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values 
        * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
        public static final double driveKS = (0.16861 / 12); //TODO: This must be tuned to specific robot
        public static final double driveKV = (2.6686 / 12);
        public static final double driveKA = (0.34757 / 12);

        /* Drive Motor Conversion Factors */
        public static final double driveConversionPositionFactor =
        wheelCircumference / driveGearRatio;
        public static final double driveConversionVelocityFactor = driveConversionPositionFactor / 60.0;
        public static final double angleConversionFactor = 360.0 / angleGearRatio;

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /* Swerve Profiling Values */
        /** Meters per Second */    // Controls the translational speed and acceleration of the robot (left joystick)
        public static final double maxSpeed = 4.1;
        public static final double maxAccel = 4.1;

        /** Radians per Second */ // Controls the rotational speed of the robot (right joystick)
        public static final double maxAngularVelocity = 10;
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /* Neutral Modes */
        public static final IdleMode angleNeutralMode = IdleMode.kCoast;
        public static final IdleMode driveNeutralMode = IdleMode.kBrake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 
        { 
            public static final int driveMotorID = 13;
            public static final int angleMotorID = 23;
            public static final int canCoderID = 33;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(-78.22); // TK 45 - Zeroed in Phoenix Tuner
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 
        { 
            public static final int driveMotorID = 10;
            public static final int angleMotorID = 20;
            public static final int canCoderID = 30;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(26.71+180); // TK 45 - Zeroed in Phoenix Tuner
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 
        { 
            public static final int driveMotorID = 12;
            public static final int angleMotorID = 22;
            public static final int canCoderID = 32;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(111.53); // TK 45 - Zeroed in Phoenix Tuner
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 
        { 
            public static final int driveMotorID = 11;
            public static final int angleMotorID = 21;
            public static final int canCoderID = 31;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(7.03); // TK 45 - Zeroed in Phoenix Tuner
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        public static final HolonomicPathFollowerConfig pathFollowerConfig = new HolonomicPathFollowerConfig
        (
            new PIDConstants(5.0, 0, 0), // Translation constants 
            new PIDConstants(5.0, 0, 0), // Rotation constants 
            maxSpeed, 
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0).getNorm(), // Drive base radius (distance from center to furthest module) 
            new ReplanningConfig()
        );
    }

    public static final class AutoConstants //TODO: The below constants are used in the example auto, and must be tuned to specific robot
    {
        public static final double slowVel = 1.5;
        public static final double slowAccel = 1.5;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }
}
