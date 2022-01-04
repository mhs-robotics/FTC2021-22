package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

        move(1, 1, 0);

        sleep(1000);

        move(1, 0, 0);

        sleep(1000);

        move(0, -1, 0);

        sleep(1800);

        move(-1, 0, 0);

        sleep(1000);

        move(-1, 1, 0);

        sleep(1000);

        move(0, 0, 0);
    }

    static void move(double x, double y, double r){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);
        double frontLeftPower = (y + x + r) / denominator;
        double backLeftPower = (y - x + r) / denominator;
        double frontRightPower = (y - x - r) / denominator;
        double backRightPower = (y + x - r) / denominator;

        motorFrontLeft.setPower(frontLeftPower);
        motorBackLeft.setPower(backLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackRight.setPower(backRightPower);
    }
}
