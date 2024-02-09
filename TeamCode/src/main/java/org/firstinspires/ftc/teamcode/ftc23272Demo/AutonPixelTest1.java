package org.firstinspires.ftc.teamcode.ftc23272Demo;

import android.hardware.Sensor;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auton Pixel Sensor Test1")
public class AutonPixelTest1 extends LinearOpMode {

    public Johnny5 robot;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        runtime.reset();
        telemetry.update();

        robot = new Johnny5();
        robot.init(hardwareMap, telemetry);

        //Code Above the waitForStart() is where you define variables or initialize any Vuforia
        waitForStart(); //Below this point is where you place the linear code for your autonomous.
        //Any code that goes in this space is only run once, and after it is finished the program ends.
        final double Y_INCH_TICKS = 40;
        final double X_INCH_TICKS = 40;

        while (true) {


            telemetry.addData("R", robot.colorSensor.red()).addData("G", robot.colorSensor.green()).addData("B", robot.colorSensor.blue()).addData("A", robot.colorSensor.alpha());
            telemetry.addData("Color Detected", robot.detectColor());
            telemetry.update();
            robot.detectColorHue();
        }
        //Step 1: make sure motor mode is run to position
        //Step 2: 500 Clicks
        //Step 3: set target position to the number of clicks
        //Step 4: set power on motors
        //Step 5: loop until all the motors are finished
        //Step 6: set mode to stop and reset encoder


      //  move(Direction.FORWARD, 43, .4);

    }
}


