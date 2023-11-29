package org.firstinspires.ftc.teamcode.ftc23272Demo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name="Auton Left Blue Bottom Corner")
public class BottomLeftBlue extends LinearOpMode {

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


        //Step 1: make sure motor mode is run to position
        //Step 2: 500 Clicks
        //Step 3: set target position to the number of clicks
        //Step 4: set power on motors
        //Step 5: loop until all the motors are finished
        //Step 6: set mode to stop and reset encoder

        final double Y_INCH_TICKS = 40;

        final double X_INCH_TICKS = 40;

        // move(Direction.FORWARD, 54, .4);
        //  sleep(500);
        // move(Direction.RIGHT, 83, .4);
        //  sleep(500);
        //  move(Direction.BACKWARD, 54, .4);
        //  sleep(500);
        //  move(Direction.RIGHT, 25.5, .4);

        telemetry.addData("R", robot.colorSensor.red()).addData("G", robot.colorSensor.green()).addData("B", robot.colorSensor.blue()).addData("A", robot.colorSensor.alpha());
        telemetry.addData("Color Detected", robot.detectColor());
        telemetry.update();
        Johnny5.ftcColor detected = robot.moveUntil(Johnny5.Direction.FORWARD, Johnny5.ftcColor.WHITE, Johnny5.ftcColor.RED, 45, .2);
        sleep(500);
        if (Johnny5.ftcColor.WHITE == detected) {
            robot.move(Johnny5.Direction.FORWARD, 5, .4);
            sleep(200);
            robot.dropPixel1();
            sleep(200);
            robot.move(Johnny5.Direction.LEFT, 88, .4);
            sleep(200);
            robot.move(Johnny5.Direction.BACKWARD, 10, .4);
            robot.dropPixel2();
        } else if (detected == Johnny5.ftcColor.RED) {
            sleep(200);
            robot.move(Johnny5.Direction.FORWARD, 5, .4);
            sleep(200);
            robot.move(Johnny5.Direction.RIGHT, 17, .4);
            sleep(200);
            robot.move(Johnny5.Direction.BACKWARD, 5, .4);
            sleep(200);
            Johnny5.ftcColor detected1 = robot.moveUntil(Johnny5.Direction.LEFT, Johnny5.ftcColor.WHITE, Johnny5.ftcColor.WHITE, 27, .2);
            sleep(200);
            if (Johnny5.ftcColor.WHITE == detected1) {
                robot.dropPixel1();
                sleep(100);
                robot.move(Johnny5.Direction.FORWARD, 2, .4);
                sleep(200);
                robot.move(Johnny5.Direction.LEFT, 105, .4);
                sleep(200);
                robot.dropPixel2();
            } else {
                robot.move(Johnny5.Direction.FORWARD, 2, .4);
                sleep(200);
                robot.move(Johnny5.Direction.LEFT, 22, .4);
                sleep(200);
                robot.dropPixel1();
                sleep(200);
                robot.move(Johnny5.Direction.BACKWARD,2, .4);
                sleep(200);
                robot.move(Johnny5.Direction.LEFT, 83, .5);
                sleep(200);
                robot.dropPixel2();
            }
        }




    }

}