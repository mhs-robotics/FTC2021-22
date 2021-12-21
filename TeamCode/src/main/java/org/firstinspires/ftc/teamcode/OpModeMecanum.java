package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@TeleOp
public class OpModeMecanum extends OpMode {
  public static DcMotor motorFrontLeft;
  public static DcMotor motorBackLeft;
  public static DcMotor motorFrontRight;
  public static DcMotor motorBackRight;
  public double speed = .7;

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
    double rx = gamepad1.right_stick_x;

    if(Math.abs(x) < .3){x = 0;}
    if(Math.abs(y) < .3){y = 0;}

    telemetry.addData("X", x);
    telemetry.addData("Y", y);
    telemetry.update();

    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    double frontLeftPower = (y + x + rx) * speed / denominator;
    double backLeftPower = (y - x + rx) * speed / denominator;
    double frontRightPower = (y - x - rx) * speed / denominator;
    double backRightPower = (y + x - rx) * speed / denominator;

    motorFrontLeft.setPower(frontLeftPower);
    motorBackLeft.setPower(backLeftPower);
    motorFrontRight.setPower(frontRightPower);
    motorBackRight.setPower(backRightPower);
  }
}
