package org.firstinspires.ftc.teamcode.ftc23272Demo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp()
public class DemoController extends OpMode {

    DcMotor backLeft;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor armMotor;
    DcMotor spinnyWheel;
    Servo clawServo;
    public void init() {
        backLeft = hardwareMap.get(DcMotor.class, "motor3");
        backRight = hardwareMap.get(DcMotor.class, "motor4");
        frontLeft = hardwareMap.get(DcMotor.class, "motor1");
        frontRight = hardwareMap.get(DcMotor.class, "motor2");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
    }

    void drive(float x, float y, float turn) {
        double angle = Math.atan2(y,x); //Finds direction joystick is pointing
        double magnitude = Math.sqrt(Math.pow(x,2)+Math.pow(y,2)); //Pythagorean Theorem
        //front right and back left motors
        double turnFactor = 1-Math.abs(turn);
        frontRight.setPower(Math.sin(angle - 0.25 * Math.PI) * turnFactor* magnitude - turn);
        backLeft.setPower(Math.sin(angle - 0.25 * Math.PI) * turnFactor * magnitude + turn);

        //front left and back right motors
        frontLeft.setPower(Math.sin(angle + 0.25 * Math.PI) * turnFactor * magnitude + turn);
        backRight.setPower(Math.sin(angle + 0.25 * Math.PI) * turnFactor * magnitude - turn);
    }
    public void loop() {
        float x = gamepad1.left_stick_x;
        float y = gamepad1.left_stick_y;
        float turn = gamepad1.right_stick_x;
        drive(x, y, turn);
    }
}
