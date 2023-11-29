package org.firstinspires.ftc.teamcode.ftc23272Demo;


import android.graphics.RenderNode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Scrimmage1")
public class Tell extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    Servo servo1;

    Servo servo2;
    DcMotor motor5;

    public void init() {


        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motor5 = hardwareMap.get(DcMotor.class, "motor5");

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);

        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");

        // reset servos to 'center' position, continuous servos to 'off'
        servo1.setPosition(0.5);
        servo1.setPosition(0.5);
    }

    public void loop() {

        double Cl = 0.1;
        double CCL = -0.1;
        double px = gamepad1.left_stick_x;
        double py = -gamepad1.left_stick_y;
        double pa = gamepad1.left_trigger - gamepad1.right_trigger;

        double p1 = -px + py + pa;
        double p2 = px + py - pa;
        double p3 = -px + py - pa;
        double p4 = px + py + pa;
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
        motor5.setPower(gamepad2.right_trigger);
        motor5.setPower(-gamepad2.left_trigger);
    /*
            if (gamepad2.dpad_up) {
                Cl += .1;
                if (Cl >= 1.0) {
                    Cl = 0.1;
                }
            }
            if (gamepad2.dpad_up) {
                CCL += -.1;
                if (CCL >= -1.0) {
                    CCL = -0.1;
                }
                if (gamepad2.dpad_down) {
                    Cl -= .1;
                    if (Cl <= .1) {
                        Cl = 0.1;
                    }
                }
                if (gamepad2.dpad_down) {
                    Cl -= -.1;
                    if (Cl <= -.1) {
                        Cl = -0.1;
                    }
                }
    */
        double dup = 0.4
                ;

        if (gamepad2.right_stick_y < -.5) {
            servo1.setPosition(dup);
        }
        if (gamepad2.right_stick_y > -.5) {
            servo1.setPosition(0.5);

            if (gamepad2.left_stick_y < -.5) {
                servo2.setPosition(dup);
            }
            if (gamepad2.left_stick_y > -.5) {
                servo2.setPosition(0.5);
            }
        }
    }
}

















