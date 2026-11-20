package frc.robot;

public class GamePiece {

    private boolean exists;
    private boolean inCenter;
    private boolean inArm;

    public GamePiece() {
        exists = true;
        inCenter = true;
        inArm = false;
    }

    public void collect() {
        exists = true;
        inCenter = true;
        inArm = false;
    }

    public void transferToArm() {
        if (exists && inCenter) {
            inCenter = false;
            inArm = true;
        }
    }

    public void release() {
        exists = false;
        inCenter = false;
        inArm = false;
    }

    public boolean isInCenter() {
        return inCenter;
    }

    public boolean isInArm() {
        return inArm;
    }

    public boolean exists() {
        return exists;
    }
}