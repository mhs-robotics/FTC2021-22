package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Autonomous Testing Miles")
public class AutonomousTestingMiles extends LinearOpMode{
    private  ElapsedTime elapsedTime = new ElapsedTime();
    static DcMotor motorFrontLeft;
    static DcMotor motorBackLeft;
    static DcMotor motorFrontRight;
    static DcMotor motorBackRight;

    static double WHEEL_DIAMETER = 3;
    static double WHEEL_CIRCUMFERENCE = (WHEEL_DIAMETER * Math.PI);
    static double REV_PER_INCH = 1/WHEEL_CIRCUMFERENCE;

    @Override
    public void runOpMode() {
        motorFrontLeft = hardwareMap.get(DcMotor.class, "FrontL");
        motorBackLeft = hardwareMap.get(DcMotor.class, "BackL");
        motorFrontRight = hardwareMap.get(DcMotor.class, "FrontR");
        motorBackRight = hardwareMap.get(DcMotor.class, "BackR");

        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        runToPosition(motorFrontLeft);

    }

    static void runToPosition(DcMotor motor){
        motor.setTargetPosition((int) (motor.getCurrentPosition() + (3 * REV_PER_INCH)));
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(1);
        while (motor.isBusy()){; }
        motor.setPower(0);
    }
}
