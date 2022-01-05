package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Autonomous Testing Miles")
public class AutonomousTestingMiles extends LinearOpMode{
    static DcMotor motorFrontLeft;
    static DcMotor motorBackLeft;
    static DcMotor motorFrontRight;
    static DcMotor motorBackRight;

    @Override
    public void runOpMode() {
        motorFrontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
        motorBackLeft = hardwareMap.get(DcMotor.class, "back_left_motor");
        motorFrontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        motorBackRight = hardwareMap.get(DcMotor.class, "back_right_motor");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        moveSeconds(1, 1, 0, 1000);

        moveSeconds(1, 0, 0, 1000);

        moveSeconds(0, -1, 0, 1800);

        moveSeconds(-1, 0, 0, 1000);

        moveSeconds(-1, 1, 0, 1000);
    }

    void moveSeconds(double x, double y, double r, int time){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);
        double frontLeftPower = (y + x + r) / denominator;
        double backLeftPower = (y - x + r) / denominator;
        double frontRightPower = (y - x - r) / denominator;
        double backRightPower = (y + x - r) / denominator;

        motorFrontLeft.setPower(frontLeftPower);
        motorBackLeft.setPower(backLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackRight.setPower(backRightPower);

        sleep(time);

        motorFrontLeft.setPower(0);
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
    }
}
