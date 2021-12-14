package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@TeleOp
public class MeccanumTest extends LinearOpMode {
  @Override
  public void runOpMode() throws InterruptedException {
    //declare our motors (make sure the id's match the configuration)
    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("FrontL");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("BackL");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("FrontR");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("BackR");

    //reverse left motors
    motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);

    waitForStart();

    while (opModeIsActive()) {
      double y = gamepad1.left_stick_x;
      double x = -gamepad1.left_stick_y * 1.1;
      double rx = gamepad1.right_stick_x;

      double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

      motorFrontLeft.setPower((y + x + rx) / denominator);
      motorBackLeft.setPower((y - x + rx) / denominator);
      motorFrontRight.setPower((y - x - rx) / denominator);
      motorBackRight.setPower((y + x - rx) / denominator);
    }
  }
}
