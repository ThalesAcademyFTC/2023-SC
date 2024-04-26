package org.firstinspires.ftc.teamcode;

import static java.lang.Math.max;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TayteLETOT44 extends OpMode {
    DcMotor number1;
    DcMotor number2;
    DcMotor number3;
    DcMotor number4;

    public void init() {

        number1 = hardwareMap.get (DcMotor.class,"motor1");
        number2 = hardwareMap.get (DcMotor.class,"motor2");
        number3 = hardwareMap.get (DcMotor.class,"motor3");
        number4 = hardwareMap.get (DcMotor.class,"motor4");
    }



    public void loop() {
        double moveforward = gamepad1.left_stick_y;
        double moveright = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;
    //number1.setPower();
double rawPower1 = moveforward + moveright + rotate;
double rawPower2 = moveforward - moveright - rotate;
double rawPower3 = moveforward + moveright - rotate;
double rawPower4 = moveforward - moveright + rotate;
    double max;
    max = Math.max(Math.abs(rawPower1), 1.0);
    max = Math.max(max, Math.abs(rawPower2));
    max = Math.max(max, Math.abs(rawPower3));
    max = Math.max(max, Math.abs(rawPower4));

    number1.setPower(rawPower1/max);
    number2.setPower(rawPower2/max);
    number3.setPower(rawPower3/max);
    number4.setPower(rawPower4/max);

    }
}
