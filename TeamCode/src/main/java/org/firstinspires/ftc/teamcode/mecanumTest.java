package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@TeleOp
public class mecanumTest extends OpMode {
  public static DcMotor motorFrontLeft;
  public static DcMotor motorBackLeft;
  public static DcMotor motorFrontRight;
  public static DcMotor motorBackRight;

  @Override
  public void init(){
    telemetry.addData("Status", "Code Init'd");

    motorFrontLeft = hardwareMap.dcMotor.get("FrontL");
    motorBackLeft = hardwareMap.dcMotor.get("BackL");
    motorFrontRight = hardwareMap.dcMotor.get("FrontR");
    motorBackRight = hardwareMap.dcMotor.get("BackR");

    motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
  }

  @Override
  public void start(){
    telemetry.addData("Status", "Main loop started");
  }

  @Override
  public void loop(){
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
