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
        motorFrontLeft = hardwareMap.get(DcMotor.class, "FrontL");
        motorBackLeft = hardwareMap.get(DcMotor.class, "BackL");
        motorFrontRight = hardwareMap.get(DcMotor.class, "FrontR");
        motorBackRight = hardwareMap.get(DcMotor.class, "BackR");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Mecanum Move Initiated");
    }

    public static void move(double x, double y, double rx){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        motorFrontLeft.setPower((y + x + rx) / denominator);
        motorBackLeft.setPower((y - x + rx) / denominator);
        motorFrontRight.setPower((y - x - rx) / denominator);
        motorBackRight.setPower((y + x - rx) / denominator);
    }
}
