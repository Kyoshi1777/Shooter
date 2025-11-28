package frc.robot.subsystems;

import java.lang.module.Configuration;
import java.security.Provider;
import java.util.function.BooleanSupplier;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.VoltageUnit;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MainWheel extends SubsystemBase {
    private final TalonFX wheelMotor1;
    private final TalonFX wheelMotor2;

    public MainWheel(final int wheelMotor1ID, final int wheelMotor2ID) {
        wheelMotor1 = new TalonFX(wheelMotor1ID);
        wheelMotor2 = new TalonFX(wheelMotor2ID);

        //ADD CONFIGURATION for motors
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    private Runnable stop() {
        return () -> {
            wheelMotor1.setVoltage(0);
            wheelMotor2.setVoltage(0);
        };
    }

    private Runnable setMotorVoltage(final double volts) {
        return () -> {
            wheelMotor1.setVoltage(volts);
            wheelMotor2.setVoltage(-volts);
        };
    }

    public Command setVoltage(final double volts) {
        return this.runOnce(setMotorVoltage(volts));
    }
    
    public Command setVoltageAndStop(final double volts) {
        return this.startEnd(setMotorVoltage(volts), stop());
        
        //return this.run(setMotorVoltage(volts)).finallyDo(stop());
    }

    public Command turnUntil(final double volts, final BooleanSupplier condition) {
        return setVoltageAndStop(volts).until(condition);
    }
}   
