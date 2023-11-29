package org.firstinspires.ftc.teamcode.ftc23272Demo;


import static java.lang.Math.min;

import android.graphics.RenderNode;
import android.view.Display;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Teleop (Choose this one!!)")
public class Tell extends OpMode {

    double pp = .85;
    double maxSpeed = 0.8;
    double OutOpen = 0;
    boolean DpadUpPressed;

    boolean AIsPressed;
    boolean DpadLeftPressed;

    boolean DpadRightPressed;

    boolean DpadDownPressed;
    double dd = 0.27;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    Servo servo1;

    Servo servo2;
    DcMotor motor5;

    Servo servo3;

    DcMotor motor6;

    DcMotor motor7;

    Servo servo4;

    //reduces the sensitivity of the robot
    public double scaleStickValue(double value) {
        double squared = value*value;
        if (value < 0.0) {
            squared = -squared;
        }
        return squared*maxSpeed;
    }

    public void init() {


        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motor5 = hardwareMap.get(DcMotor.class, "motor5");
        motor6 = hardwareMap.get(DcMotor.class, "motor6");
        motor7 = hardwareMap.get(DcMotor.class, "motor7");

        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        //motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        //motor3.setDirection(DcMotorSimple.Direction.REVERSE);
        motor4.setDirection(DcMotorSimple.Direction.REVERSE);

        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor5.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor6.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servo4 = hardwareMap.get(Servo.class, "servo4");

        // reset servos to 'center' position, continuous servos to 'off'
        servo1.setPosition(dd);
        servo2.setPosition(0.5);
        servo3.setPosition(0.5);
        servo4.setPosition(.5);

        servo3.setDirection(Servo.Direction.REVERSE);
    }

    public void loop() {




        if (gamepad2.dpad_up != DpadUpPressed) {
            dd += .005;
        }
        DpadUpPressed = gamepad2.dpad_up;

        if (gamepad2.dpad_down != DpadDownPressed) {
            dd -= .005;
        }
        DpadDownPressed = gamepad2.dpad_down;

        if (gamepad2.right_bumper) {
            motor5.setPower(pp);
        } else {
            motor5.setPower(0);
        }
        if (gamepad2.left_bumper) {
            motor5.setPower(-pp);
        } else {
            motor5.setPower(0);
        }

        if (gamepad2.right_stick_y > 0) {
            servo1.setPosition(dd);
        }
        if (gamepad2.right_stick_y < 0) {
            servo1.setPosition(.5);
        }
        if (gamepad2.right_trigger > .1) {
            servo2.setPosition(.65);
        }
        if (gamepad2.right_trigger < .1) {
            servo2.setPosition(0.5);
        }
        if (gamepad2.left_trigger > .1) {
            servo3.setPosition(.65);
        }
        if (gamepad2.left_trigger < .1) {
            servo3.setPosition(0.5);
        }
        if (gamepad2.dpad_left != DpadLeftPressed) {
            pp -= .005;
        }
        DpadLeftPressed = gamepad2.dpad_left;

        if (gamepad2.dpad_right != DpadRightPressed) {
            pp += .005;
        }
        DpadRightPressed = gamepad2.dpad_right;

        if (gamepad2.left_stick_y > .01) {
            motor6.setPower(-.75);
        } else if  (gamepad2.left_stick_y < -.01) {
            motor6.setPower(.75);
        } else {
            motor6.setPower(0);
        }



        if (gamepad2.a) {
            motor7.setPower(-1);
        } else {
            motor7.setPower(0);
        }
        if (gamepad2.y && gamepad1.y) {
            servo4.setPosition(.85);
        }
        if (gamepad2.left_stick_button) {
            motor6.setPower(0);
        }
        if (gamepad1.touchpad) {
            Johnny5.dpadMode(1);
        }
        if (gamepad1.touchpad) {

        }
    }

}

















