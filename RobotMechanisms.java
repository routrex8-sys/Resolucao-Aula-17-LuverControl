package frc.robot;

public class RobotMechanisms {

    private double conveyorSpeed = 0.0;
    private double armPosition = 0.0;

    private boolean armReady = false;

    public void runConveyor(double speed) {
        conveyorSpeed = speed;
    }

    public void stopConveyor() {
        conveyorSpeed = 0.0;
    }

    public void moveArm(double position) {
        armPosition = position;

        if (armPosition >= 1.0) {
            armReady = true;
        }
    }

    public void stopArm() {
        armReady = false;
    }

    public double getConveyorSpeed() {
        return conveyorSpeed;
    }

    public double getArmPosition() {
        return armPosition;
    }

    public boolean isArmReady() {
        return armReady;
    }
}