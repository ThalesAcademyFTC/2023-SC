package org.firstinspires.ftc.teamcode.ftc23272demo;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static java.lang.Math.min;

import android.graphics.RenderNode;
import android.view.Display; 

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Teleop (Choose this one!!)")
public class  Tell extends OpMode {

    boolean dpadMode = false;
    double pp = 1;
    double maxSpeed = 0.8;
    double OutOpen = 0;
    boolean DpadUpPressed;

    boolean AIsPressed;
    boolean DpadLeftPressed;

    boolean DpadRightPressed;

    boolean DpadDownPressed;
    //double dd = 0.25;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    Servo clawServo;

    Servo servo2;
    Servo servo3;
    DcMotor motor5;

    Servo clawHingeServo;

    DcMotor motor6;

    //DcMotor motor7;

    Servo armServo;

    Servo servoTest;


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
        //motor7 = hardwareMap.get(DcMotor.class, "motor7");

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

        clawServo = hardwareMap.get(Servo.class, "clawServo");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        clawHingeServo = hardwareMap.get(Servo.class, "clawHingeServo");
        armServo = hardwareMap.get(Servo.class, "armServo");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servoTest = hardwareMap.get(Servo.class, "servoTest");


        // reset servos to 'center' position, continuous servos to 'off'
        clawServo.setPosition(0.65);
        servo2.setPosition(0.5);
        clawHingeServo.setPosition(0.5);
        armServo.setPosition(0.5);
        servo3.setPosition(0.5);
        servoTest.setPosition(0.5);

        servo3.setDirection(Servo.Direction.REVERSE);

        telemetry.addData("Right", gamepad2.right_trigger);
        telemetry.addData("Left", gamepad2.left_trigger);
        //telemetry.addData("ServoStartingPoint", "%.3f", dd);
        telemetry.addData("IntakeSpeed", "%.3f", pp);
        telemetry.update();
    }

    public void loop() {




        /*if (gamepad2.dpad_up != DpadUpPressed) {
            dd += .005;
        }
        DpadUpPressed = gamepad2.dpad_up;

        if (gamepad2.dpad_down != DpadDownPressed) {
            dd -= .005;
        }*/
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

        /*if (gamepad2.right_stick_y > .1) {
            clawServo.setPosition(0.65);
        }
        if (gamepad2.right_stick_y < .1) {
            clawServo.setPosition(0.3);
        }*/

        //Gamepad2 right trigger wiggles one servo
        if (gamepad2.right_trigger > .1) {
            servo2.setPosition(0.65);
        }
        if (gamepad2.right_trigger < .1) {
            servo2.setPosition(0.5);
        }
        //Wiggles other servo
        if (gamepad2.left_trigger > .1) {
            servo3.setPosition(.65);
        }
        if (gamepad2.left_trigger < .1) {
            servo3.setPosition(0.5);
        }
        //Testing for servo functionality
        if (gamepad2.dpad_up) {
            servoTest.setPosition(1);
        }
        if (gamepad2.dpad_down) {
            servoTest.setPosition(0);
        }

        
        //gamepad1 right trigger moves arm to set positions
        if (gamepad1.right_trigger > .1) {
            armServo.setPosition(0.7);
        }   
        if (gamepad1.right_trigger < .1) {
            armServo.setPosition(0.1);
        //gamepad1 left trigger moves the claw hinge to set positions
        }
        if (gamepad1.left_trigger > .1) {
            clawHingeServo.setPosition(0.65);
        }
        if (gamepad1.left_trigger < .1) {
            clawHingeServo.setPosition(0.5);
        }
        //gamepad1 left bumper opens the claw
        if (gamepad1.x) {
            clawServo.setPosition(0.65);
        }
        //gamepad1 right bumper closes the claw
        if (gamepad1.b) {
            clawServo.setPosition(0.3);
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
        //BEGIN CODE FOR SCUFFED PRECISION ARM MOVEMENT
        //SCUFFED UPWARD MOVEMENT
        /*if (gamepad2.right_stick_y > .1) {
            armServo.setPosition(0.6);
        }
        if (gamepad2.right_stick_y > .2) {
            armServo.setPosition(0.5);
        }
        if (gamepad2.right_stick_y > .3) {
            armServo.setPosition(0.4);
        }
        if (gamepad2.right_stick_y > .4) {
            armServo.setPosition(0.3);
        }
        if (gamepad2.right_stick_y > .5) {
            armServo.setPosition(0.2);
        }
        if (gamepad2.right_stick_y > .6) {
            armServo.setPosition(0.1);
        }
        //SCUFFED DOWNWARD MOVEMENT
        if (gamepad2.right_stick_y < .6) {
            armServo.setPosition(0.1);
        }
        if (gamepad2.right_stick_y < .5) {
            armServo.setPosition(0.2);
        }
        if (gamepad2.right_stick_y < .4) {
            armServo.setPosition(0.3);
        }
        if (gamepad2.right_stick_y < .3) {
            armServo.setPosition(0.4);
        }
        if (gamepad2.right_stick_y < .2) {
            armServo.setPosition(0.5);
        }
        if (gamepad2.right_stick_y < .1) {
            armServo.setPosition(0.6);
        }*/

        /*if (gamepad2.a && gamepad1.a) {
            motor7.setPower(-1);
        } else {
            motor7.setPower(0);
        }
        if (gamepad2.y && gamepad1.y) {
            servo4.setPosition(.85);
        }*/
        if (gamepad2.left_stick_button) {
            motor6.setPower(0);
        }
        if (gamepad1.b) {
            dpadMode = !dpadMode;
        }
        if (dpadMode) {
            double pa = scaleStickValue(gamepad1.right_stick_x);
            double p1 = + pa;
            double p2 = - pa;
            double p3 = - pa;
            double p4 = + pa;
            double max = Math.max (1.0, Math. abs (p1));
            max = Math. max (max, Math. abs (p2));
            max = Math. max (max, Math. abs (p3));
            max = Math. max (max, Math. abs (p4));
            p1 /= max;
            p2 /= max;
            p3 /= max;
            p4 /= max;
            motor1.setPower(p1);
            motor2.setPower(p2);
            motor3.setPower(p3);
            motor4.setPower(p4);

            if (gamepad1.dpad_down) {
                motor1.setPower(.75);
                motor2.setPower(.75);
                motor3.setPower(.75);
                motor4.setPower(.75);
            } else if (gamepad1.dpad_up) {
                motor1.setPower(-.75);
                motor2.setPower(-.75);
                motor3.setPower(-.75);
                motor4.setPower(-.75);
            } else if (gamepad1.dpad_right) {
                motor1.setPower(.75);
                motor2.setPower(-.75);
                motor3.setPower(.75);
                motor4.setPower(-.75);
            } else if (gamepad1.dpad_left) {
                motor1.setPower(-.75);
                motor2.setPower(.75);
                motor3.setPower(-.75);
                motor4.setPower(.75);
            } else {
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
            }

        } else { // do sane left stick mode
            double px = scaleStickValue(gamepad1.left_stick_x);
            double py = scaleStickValue(gamepad1.left_stick_y);
            double pa = scaleStickValue(gamepad1.right_stick_x);
            double p1 = px + py + pa;
            double p2 = -px + py - pa;
            double p3 = px + py - pa;
            double p4 = -px + py + pa;
            double max = Math.max (1.0, Math. abs (p1));
            max = Math. max (max, Math. abs (p2));
            max = Math. max (max, Math. abs (p3));
            max = Math. max (max, Math. abs (p4));
            p1 /= max;
            p2 /= max;
            p3 /= max;
            p4 /= max;
            motor1.setPower(p1);
            motor2.setPower(p2);
            motor3.setPower(p3);
            motor4.setPower(p4);
        }
    }

}

















