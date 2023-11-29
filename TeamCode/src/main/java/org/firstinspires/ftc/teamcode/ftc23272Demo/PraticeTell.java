package org.firstinspires.ftc.teamcode.ftc23272Demo;


import static java.lang.Math.min;

import android.graphics.RenderNode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Practice Teleop)")
public class PraticeTell extends OpMode {

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

   // DcMotor motor7;



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
       // motor7 = hardwareMap.get(DcMotor.class, "motor7");

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

        // reset servos to 'center' position, continuous servos to 'off'
        servo1.setPosition(dd);
        servo2.setPosition(0.5);
        servo3.setPosition(0.5);

        servo3.setDirection(Servo.Direction.REVERSE);
    }

    public void loop() {

        double Cl = 0.1;
        double CCL = -0.1;
        double pa = scaleStickValue(gamepad1.right_stick_x);
        double p1 = + pa;
        double p2 = - pa;
        double p3 = - pa;
        double p4 = + pa;
        double max = Math.max(1.0, Math.abs(p1));
        max = Math.max(max, Math.abs(p2));
        max = Math.max(max, Math.abs(p3));
        max = Math.max(max, Math.abs(p4));
        p1 /= max;
        p2 /= max;
        p3 /= max;
        p4 /= max;
        motor1.setPower(p1);
        motor2.setPower(p2);
        motor3.setPower(p3);
        motor4.setPower(p4);
        telemetry.addData("Right", gamepad2.right_trigger);
        telemetry.addData("Left", gamepad2.left_trigger);
        telemetry.addData("motor1", p1);
        telemetry.addData("motor2", p2);
        telemetry.addData("motor3", p3);
        telemetry.addData("motor4", p4);
        telemetry.addData("ServoStartingPoint", "%.3f", dd);
        telemetry.addData("IntakeSpeed", "%.3f", pp);


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

        if (gamepad1.dpad_up) {
            motor6.setPower(.75);
        } else if (gamepad1.dpad_down) {
            motor6.setPower(-.75);
        } else {
            motor6.setPower(0);
        }

     //   if (gamepad2.a) {
       //     motor7.setPower(-1);
        //} else {
          //  motor7.setPower(0);
        //}
        if (gamepad1.dpad_up) {
            motor1.setPower(1);
            motor2.setPower(1);
            motor3.setPower(1);
            motor4.setPower(1);
        } else if (gamepad1.dpad_down) {
            motor1.setPower(-1);
            motor2.setPower(-1);
            motor3.setPower(-1);
            motor4.setPower(-1);
        } else if (gamepad1.dpad_left) {
            motor1.setPower(1);
            motor2.setPower(-1);
            motor3.setPower(1);
            motor4.setPower(-1);
        } else if (gamepad1.dpad_right) {
            motor1.setPower(-1);
            motor2.setPower(1);
            motor3.setPower(-1);
            motor4.setPower(1);
        }


    }

}


















