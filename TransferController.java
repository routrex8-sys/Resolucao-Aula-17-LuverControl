package frc.robot;

public class TransferController {

    public enum TransferState {
        IDLE,
        POSITIONING_ARM,
        TRANSFERRING,
        FINISHED
    }

    private TransferState state = TransferState.IDLE;

    private final RobotMechanisms mechanisms;
    private final GamePiece gamePiece;

    private double timer = 0.0;

    public TransferController(
            RobotMechanisms mechanisms,
            GamePiece gamePiece) {

        this.mechanisms = mechanisms;
        this.gamePiece = gamePiece;
    }

    public void startTransfer() {

        if (state == TransferState.IDLE
                && gamePiece.isInCenter()) {

            state = TransferState.POSITIONING_ARM;
            timer = 0.0;
        }
    }

    public void update(double deltaTime) {

        switch (state) {

            case IDLE:

                mechanisms.stopConveyor();
                break;

            case POSITIONING_ARM:

                mechanisms.moveArm(1.0);

                timer += deltaTime;

                if (timer >= 1.0) {

                    state = TransferState.TRANSFERRING;
                    timer = 0.0;
                }

                break;

            case TRANSFERRING:

                mechanisms.runConveyor(0.6);

                timer += deltaTime;

                if (timer >= 2.0) {

                    mechanisms.stopConveyor();

                    gamePiece.transferToArm();

                    state = TransferState.FINISHED;
                }

                break;

            case FINISHED:

                mechanisms.stopConveyor();

                break;
        }
    }

    public void reset() {

        state = TransferState.IDLE;
        timer = 0.0;

        mechanisms.stopConveyor();
    }

    public TransferState getState() {
        return state;
    }

    public boolean isFinished() {
        return state == TransferState.FINISHED;
    }
}