package org.firstinspires.ftc.teamcode.ftc23272Demo;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Johnny5 {
    public boolean dpadMode;
    enum ftcColor { RED, BLUE, WHITE, UNKNOWN };
    enum Direction  {FORWARD, BACKWARD, LEFT, RIGHT}

    double ticksPerInches = 58.17537;
    static DcMotor motor1;
    static DcMotor motor2;
    static DcMotor motor3;
    static DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;

    DcMotor motor7;

    Servo servo1;

    Servo servo2;

    Servo servo3;
    RevColorSensorV3 colorSensor;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    public void init(HardwareMap hmap, Telemetry telem) {
        hardwareMap = hmap;
        telemetry = telem;
        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motor5 = hardwareMap.get(DcMotor.class, "motor5");
        motor6 = hardwareMap.get(DcMotor.class, "motor6");
        motor7 = hardwareMap.get(DcMotor.class, "motor7");

        motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        motor3.setDirection(DcMotorSimple.Direction.REVERSE);
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");

        colorSensor = hardwareMap.get(RevColorSensorV3.class, "colorSensor");

        // reset servos to 'center' position, continuous servos to 'off'
        servo1.setPosition(0.5);
        servo2.setPosition(0.5);
        servo3.setPosition(0.5);
        dpadMode = false;
    }

    public Color convertToColorType(int red, int green, int blue, int alpha) {
        Color rgb = new Color();
        int max = 10000;
        rgb.pack(red * 255 / max,
                green * 255 / max,
                 blue * 255 / max,
                alpha * 255 / max);
        return rgb;
    }
    public ftcColor detectColorHue() {
        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();
        //int alpha = colorSensor.alpha() / 16000;
        //Color rgb = convertToColorType(red, green, blue, alpha);
        float hsvValues[] = new float[3];
        final float hsv[] = hsvValues;
        Color.RGBToHSV(red, green, blue, hsv);
        // hsv[0] is Hue, a value between 0 and 360
        // hsv[1] is saturation, a value between 0 and 100
        // hsv[2] is brightness, a value between 0 and 100
        ftcColor detectedColor = ftcColor.UNKNOWN;
        // detect color from hsv
    telemetry.addData("H", hsv[0]);
    telemetry.addData("S", hsv[1]);
    telemetry.addData("V", hsv[2]);
    telemetry.update();
        if (hsv[0] >= 330 && hsv[0] <= 30) {
            return  ftcColor.RED;
        }
        if (hsv[0] <= 240 && hsv[0] >= 180) {
            return ftcColor.BLUE;
        }
        if (hsv[0] == 0 && hsv[1] < 10 && hsv[2] > 90) {
            return ftcColor.WHITE;
        }

        return ftcColor.UNKNOWN;
    }

    public ftcColor detectColor() {
        double red = colorSensor.red();
        double blue = colorSensor.blue();
        double green = colorSensor.green();
        int alpha = colorSensor.alpha();
        double max;
        max = Math.max(red, blue);
        max = Math.max(max, green);
        red /= max;
        green /= max;
        blue /= max;
        telemetry.addData("R", red); telemetry.addData("G", green); telemetry.addData("B", blue);
        if(red == 1 && blue < .7 && green < .9) {
            return ftcColor.RED;
        }
        if (blue == 1 && green < .7 && red < .7) {
            return ftcColor.BLUE;
        }
        if (blue >= 0.9 && red <= 0.45 && green >= .8 && alpha >= 2000) {
            return ftcColor.WHITE;
        }
        return ftcColor.UNKNOWN;
    }
    public void move( Direction direction, double inches, double speed) {
        int ticks = (int) (ticksPerInches * inches);
        double speed1=0.0, speed2=0.0, speed3=0.0, speed4=0.0;
        switch(direction) {
            case FORWARD:
                speed1 = speed; speed2 = speed; speed3 = speed; speed4 = speed;
                motor1.setTargetPosition(ticks);
                motor2.setTargetPosition(ticks);
                motor3.setTargetPosition(ticks);
                motor4.setTargetPosition(ticks);
                break;
            case BACKWARD:
                speed1 = -speed; speed2 = -speed; speed3 = -speed; speed4 = -speed;
                motor1.setTargetPosition(-ticks);
                motor2.setTargetPosition(-ticks);
                motor3.setTargetPosition(-ticks);
                motor4.setTargetPosition(-ticks);
                break;
            case LEFT:
                speed1 = speed; speed2 = -speed; speed3 = speed; speed4 = -speed;
                motor1.setTargetPosition(ticks);
                motor2.setTargetPosition(-ticks);
                motor3.setTargetPosition(ticks);
                motor4.setTargetPosition(-ticks);
                break;
            case RIGHT:
                speed1 = -speed; speed2 = speed; speed3 = -speed; speed4 = speed;
                motor1.setTargetPosition(-ticks);
                motor2.setTargetPosition(ticks);
                motor3.setTargetPosition(-ticks);
                motor4.setTargetPosition(ticks);

        }

        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor2.setPower(speed2);
        motor3.setPower(speed3);
        motor1.setPower(speed1);
        motor4.setPower(speed4);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (motor2.isBusy() || motor3.isBusy() || motor1.isBusy() || motor4.isBusy()) {
            telemetry.addData("Running until", "%7d");
            telemetry.addData("Currently at", "at %7d :%7d :%7d :%7d", motor2.getCurrentPosition(), motor3.getCurrentPosition(), motor1.getCurrentPosition(), motor4.getCurrentPosition());
            telemetry.update();
        }
    }
    public ftcColor moveUntil( Direction direction,ftcColor color1, ftcColor color2, double inches, double speed) {
        int ticks = (int) (ticksPerInches * inches);
        double speed1 = 0.0, speed2 = 0.0, speed3 = 0.0, speed4 = 0.0;
        switch (direction) {
            case FORWARD:
                speed1 = speed;
                speed2 = speed;
                speed3 = speed;
                speed4 = speed;
                motor1.setTargetPosition(ticks);
                motor2.setTargetPosition(ticks);
                motor3.setTargetPosition(ticks);
                motor4.setTargetPosition(ticks);
                break;
            case BACKWARD:
                speed1 = -speed;
                speed2 = -speed;
                speed3 = -speed;
                speed4 = -speed;
                motor1.setTargetPosition(-ticks);
                motor2.setTargetPosition(-ticks);
                motor3.setTargetPosition(-ticks);
                motor4.setTargetPosition(-ticks);
                break;
            case LEFT:
                speed1 = speed;
                speed2 = -speed;
                speed3 = speed;
                speed4 = -speed;
                motor1.setTargetPosition(ticks);
                motor2.setTargetPosition(-ticks);
                motor3.setTargetPosition(ticks);
                motor4.setTargetPosition(-ticks);
                break;
            case RIGHT:
                speed1 = -speed;
                speed2 = speed;
                speed3 = -speed;
                speed4 = speed;
                motor1.setTargetPosition(-ticks);
                motor2.setTargetPosition(ticks);
                motor3.setTargetPosition(-ticks);
                motor4.setTargetPosition(ticks);

        }

        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor2.setPower(speed2);
        motor3.setPower(speed3);
        motor1.setPower(speed1);
        motor4.setPower(speed4);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor4.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ftcColor detectedColor = ftcColor.UNKNOWN;
        // while motors are busy and color not detected
        while ((motor2.isBusy() || motor3.isBusy() || motor1.isBusy() || motor4.isBusy())
                && (detectedColor != color1 && detectedColor != color2)) {
            detectedColor = detectColor();
            telemetry.addData("Running until", "%7d");
            telemetry.addData("Currently at", "at %7d :%7d :%7d :%7d", motor2.getCurrentPosition(), motor3.getCurrentPosition(), motor1.getCurrentPosition(), motor4.getCurrentPosition());
            telemetry.addData("Color Detected", detectColorHue());
            telemetry.update();
        }
        return detectedColor;
    }
    public void dropPixel1() {
      openGate1();
      move(Direction.FORWARD, 4, .4);
      linearOpMode.sleep(500);
      closeGate1();
    }
    public void dropPixel2() {
        openGate2();
        move(Direction.FORWARD, 4, .4);
        linearOpMode.sleep(500);
        closeGate2();
    }
    public void openGate1() {
        servo2.setPosition(.75);

    }
    public void closeGate1() {
        servo2.setPosition(.5);
    }
    public void openGate2() {
        servo3.setPosition(.75);

    }
    public void closeGate2() {
        servo3.setPosition(.5);
    }
    public void power() {

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

    }



}

