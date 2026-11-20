package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    private GamePiece gamePiece;
    private RobotMechanisms mechanisms;
    private TransferController transferController;

    private double lastTime;

    @Override
    public void robotInit() {

        gamePiece = new GamePiece();

        mechanisms = new RobotMechanisms();

        transferController = new TransferController(
                mechanisms,
                gamePiece
        );

        lastTime = Timer.getFPGATimestamp();

        SmartDashboard.putString("Transfer State", "IDLE");
    }

    @Override
    public void robotPeriodic() {

        double currentTime = Timer.getFPGATimestamp();

        double deltaTime = currentTime - lastTime;

        lastTime = currentTime;

        transferController.update(deltaTime);

        updateDashboard();
    }

    @Override
    public void teleopInit() {

        transferController.reset();

        lastTime = Timer.getFPGATimestamp();
    }

    @Override
    public void teleopPeriodic() {

        // Inicia automaticamente a transferência
        if (gamePiece.isInCenter()
                && transferController.getState()
                == TransferController.TransferState.IDLE) {

            transferController.startTransfer();
        }
    }

    private void updateDashboard() {

        SmartDashboard.putBoolean(
                "Game Piece Exists",
                gamePiece.exists()
        );

        SmartDashboard.putBoolean(
                "Game Piece Center",
                gamePiece.isInCenter()
        );

        SmartDashboard.putBoolean(
                "Game Piece Arm",
                gamePiece.isInArm()
        );

        SmartDashboard.putNumber(
                "Conveyor Speed",
                mechanisms.getConveyorSpeed()
        );

        SmartDashboard.putNumber(
                "Arm Position",
                mechanisms.getArmPosition()
        );

        SmartDashboard.putString(
                "Transfer State",
                transferController.getState().toString()
        );

        SmartDashboard.putBoolean(
                "Transfer Finished",
                transferController.isFinished()
        );
    }

    @Override
    public void disabledInit() {

        mechanisms.stopConveyor();
    }
}