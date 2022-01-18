package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "Autonomous Testing Miles")
public class AutonomousTestingMiles extends LinearOpMode{
    static DcMotor motorFrontLeft;
    static DcMotor motorBackLeft;
    static DcMotor motorFrontRight;
    static DcMotor motorBackRight;
    static Servo topMotor;

    @Override
    public void runOpMode() {
        motorFrontLeft = hardwareMap.get(DcMotor.class, "front_left_motor");
        motorBackLeft = hardwareMap.get(DcMotor.class, "back_left_motor");
        motorFrontRight = hardwareMap.get(DcMotor.class, "front_right_motor");
        motorBackRight = hardwareMap.get(DcMotor.class, "back_right_motor");

        topMotor = hardwareMap.get(Servo.class, "back_servo");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        for(int x = 0; x < 5; x ++){
            getBoxes();
        }
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

        sleep(10);
    }

    void getBoxes(){
        moveSeconds(1, 0, 0, 200);

        moveSeconds(0, -1, 0, 450);

        topMotor.setPosition(10);
        sleep(1000);
        topMotor.setPosition(-10);

        moveSeconds(0, 1, 0, 450);

        moveSeconds(-1, 0, 0, 200);

        moveSeconds(0, 1, 0, 900);

        moveSeconds(0, 0, 1, 840);

        moveSeconds(0, -1, 0, 500);

        topMotor.setPosition(10);
        sleep(1000);
        topMotor.setPosition(-10);

        moveSeconds(0, 1, 0, 500);

        moveSeconds(0, 0, -1, 840);

        moveSeconds(0, -1, 0, 900);
    }
}
