package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

public abstract class mecanumMove extends OpMode{
    public static DcMotor motorFrontLeft;
    public static DcMotor motorBackLeft;
    public static DcMotor motorFrontRight;
    public static DcMotor motorBackRight;

    @Override
    public void init() {
        telemetry.addData("Status", "Mecanum Move Initiated");

        motorFrontLeft = hardwareMap.dcMotor.get("FrontL");
        motorBackLeft = hardwareMap.dcMotor.get("BackL");
        motorFrontRight = hardwareMap.dcMotor.get("FrontR");
        motorBackRight = hardwareMap.dcMotor.get("BackR");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public static void move(double x, double y, double rx){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        motorFrontLeft.setPower((y + x + rx) / denominator);
        motorBackLeft.setPower((y - x + rx) / denominator);
        motorFrontRight.setPower((y - x - rx) / denominator);
        motorBackRight.setPower((y + x - rx) / denominator);
    }
}
