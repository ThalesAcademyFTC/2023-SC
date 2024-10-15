package org.firstinspires.ftc.teamcode.ftc23272Demo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name="Auton Red Right Top Corner")
public class TopRightRed extends LinearOpMode {

    public Johnny5 robot;
    private ElapsedTime runtime = new ElapsedTime();
    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    private Finder finder;

    private Spark.Team team = Spark.Team.RED;

    private Finder.SPIKE_MARK propLocation;


    @Override
    public void runOpMode() {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);

        finder = new Finder(this, robot.webcamName, team);
        runtime.reset();

        finder.init();

        telemetry.addData("Status", "Initialized");
        runtime.reset();
        telemetry.update();

        robot = new Johnny5();
        robot.init(hardwareMap, telemetry);

        while ( opModeIsActive() && propLocation == null ) {

            finder.scanWithTelemetry();

            propLocation = finder.getPropLocation();
            //This updates the array of current detections inside tagger

            telemetry.addData("Pixel Spike Location: ", propLocation);

            telemetry.update();

            // Share the CPU.

            sleep(20);

        }

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


        telemetry.addData("R", robot.colorSensor.red()).addData("G", robot.colorSensor.green()).addData("B", robot.colorSensor.blue()).addData("A", robot.colorSensor.alpha());
        telemetry.addData("Color Detected", robot.detectColor());
        telemetry.update();
         Johnny5.ftcColor detected = robot.moveUntil(Johnny5.Direction.FORWARD, Johnny5.ftcColor.RED, Johnny5.ftcColor.WHITE, 45, .15);
                sleep(200);
         if (Johnny5.ftcColor.WHITE == detected) {
             //code for 1st spike mark
             robot.move(Johnny5.Direction.FORWARD, 1.25, .4);
             sleep(200);
             robot.dropPixel1();
             sleep(200);
             robot.move(Johnny5.Direction.BACKWARD, 4, .4);
             sleep(200);
             robot.move(Johnny5.Direction.RIGHT, 38, .4);
             sleep(200);
             robot.dropPixel2();
             sleep(200);
             robot.move(Johnny5.Direction.BACKWARD, 44.5, .4);
             sleep(200);
             robot.move(Johnny5.Direction.RIGHT, 10, .4);
         } else if (Johnny5.ftcColor.RED == detected){
             robot.move(Johnny5.Direction.RIGHT, 15, .4);
             sleep(200);
             robot.move(Johnny5.Direction.BACKWARD, 17, .4);
             sleep(200);
             Johnny5.ftcColor detected2 = robot.moveUntil(Johnny5.Direction.LEFT, Johnny5.ftcColor.WHITE, Johnny5.ftcColor.RED, 75, .25);
             sleep(200);
             if (detected2 == Johnny5.ftcColor.WHITE) {
                 //code for 2nd spike mark
                 robot.move(Johnny5.Direction.RIGHT, 2, .4);
                 sleep(200);
                 robot.dropPixel1();
                 sleep(200);
                 robot.move(Johnny5.Direction.RIGHT, 28, .4);
                 sleep(200);
                 robot.dropPixel2();
                 sleep(200);
                 robot.move(Johnny5.Direction.BACKWARD, 44.5, .4);
                 sleep(200);
                 robot.move(Johnny5.Direction.RIGHT, 22.5, .4);
             } else if (detected2 == Johnny5.ftcColor.RED) {
                 //code for 3rd spike mark
                 robot.move(Johnny5.Direction.LEFT, 22, .5);
                 sleep(200);
                 robot.dropPixel1();
                 sleep(200);
                 robot.move(Johnny5.Direction.BACKWARD,2,.5);
                 sleep(200);
                 robot.move(Johnny5.Direction.RIGHT, 50, .5);
                 sleep(200);
                 robot.dropPixel2();
                 sleep(200);
                 robot.move(Johnny5.Direction.BACKWARD, 2, .5);
                 sleep(200);
                 robot.move(Johnny5.Direction.BACKWARD, 22, .4);
                 sleep(200);
             } else if (detected == Johnny5.ftcColor.UNKNOWN){
             robot.move(Johnny5.Direction.FORWARD, 4, .4);
             sleep(200);
             robot.move(Johnny5.Direction.LEFT, 38, .4);
             sleep(200);
             robot.move(Johnny5.Direction.BACKWARD, 10, .4);


         }


    }}}