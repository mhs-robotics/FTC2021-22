package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MeccanumTest extends LinearOpMode {
  @Override
  public void runOpMode() throws InterruptedException {
    //declare our motors
    //make sure your id's match your configuration
    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("FrontL");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("BackL");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("FrontR");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("BackR");

    //reverse left motors bc using NeveRests
    motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);

    waitForStart();

    if (isStopRequested()) return;

    while (opModeIsActive()) {
      double y = gamepad1.left_stick_x; // this is reversed
      double x = -gamepad1.left_stick_y * 1.1; //counteract bad strafing
      double rx = gamepad1.right_stick_x;

      //denom is largest motor power or 1
      if (Math.abs(gamepad1.left_stick_x) > 0.1 || Math.abs(gamepad1.left_stick_y) > 0.1) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

      motorFrontLeft.setPower(frontLeftPower);
      motorBackLeft.setPower(backLeftPower);
      motorFrontRight.setPower(frontRightPower);
      motorBackRight.setPower(backRightPower);
    }
  }
}
