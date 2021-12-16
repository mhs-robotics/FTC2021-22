package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@TeleOp
public class OpModeMecanum extends OpMode {
  public static DcMotor motorFrontLeft;

  @Override
  public void init() {
    telemetry.addData("Status", "Mecanum Move Initiated");

    motorFrontLeft = hardwareMap.dcMotor.get("FrontL");

    motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    telemetry.addData("Status", "Op Mode Control Initiated");
  }

  @Override
  public void loop(){
    double x = -gamepad1.left_stick_y * 1.1;
    double y = gamepad1.left_stick_x;
    double rx = gamepad1.right_stick_x;

    mecanumMove.move(x, y, rx);

    if(gamepad1.a){
      mecanumMove.moveRotations(1, 3, motorFrontLeft);
    }
  }
}
