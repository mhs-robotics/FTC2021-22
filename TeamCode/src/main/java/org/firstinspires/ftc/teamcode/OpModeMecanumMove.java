package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@TeleOp(name = "OpMode Movement")
public class OpModeMecanumMove extends OpMode {
  static DcMotor motorFrontLeft;
  static DcMotor motorBackLeft;
  static DcMotor motorFrontRight;
  static DcMotor motorBackRight;
  double speed = .7;

  @Override
  public void init() {
    motorFrontLeft = hardwareMap.get(DcMotor.class, "FrontL");
    motorBackLeft = hardwareMap.get(DcMotor.class, "BackL");
    motorFrontRight = hardwareMap.get(DcMotor.class, "FrontR");
    motorBackRight = hardwareMap.get(DcMotor.class, "BackR");

    motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    telemetry.addData("Status", "Mecanum Move Initiated");
    telemetry.addData("Status", "Op Mode Control Initiated");
  }

  @Override
  public void loop(){
    double y = -gamepad1.left_stick_y;
    double x = gamepad1.left_stick_x;
    double r = gamepad1.right_stick_x;

    if(Math.abs(x) < .2){x = 0;}
    if(Math.abs(y) < .2){y = 0;}

    telemetry.addData("X", x);
    telemetry.addData("Y", y);
    telemetry.update();

    move(x, y, r, speed);
  }

  static void move(double x, double y, double r, double speed){
    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);
    double frontLeftPower = (y + x + r) * speed / denominator;
    double backLeftPower = (y - x + r) * speed / denominator;
    double frontRightPower = (y - x - r) * speed / denominator;
    double backRightPower = (y + x - r) * speed / denominator;

    motorFrontLeft.setPower(frontLeftPower);
    motorBackLeft.setPower(backLeftPower);
    motorFrontRight.setPower(frontRightPower);
    motorBackRight.setPower(backRightPower);
  }
}
