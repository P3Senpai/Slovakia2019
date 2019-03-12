/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
//@Disabled
public class OpMode_linear extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Robot robot = new Robot();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.init(hardwareMap);

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while(true) {

            // region Petr

            //comented out to avoid competing methods of setting the motot powers
            /*double leftPower = Range.clip(gamepad1.left_stick_y, -1.0, 1.0);
            double rightPower = Range.clip(gamepad1.right_stick_y, -1.0, 1.0);
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);*/





            //endregion

            //region Michael


            // sets the power of motors using x and y values from one stick to simplify driving
            double y = - gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;

            // addition an subtraction slows down one motor based on the x value to make the turn
            double leftPower    = Range.clip(y - x, -1.0, 1.0) ;
            double rightPower   = Range.clip(y + x, -1.0, 1.0) ;

            // sets the power of the drive motors
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);

            //s sets the power of the lift motors based on the second joy stick
            robot.rightLift.setPower(-gamepad1.right_stick_y);
            robot.leftLift.setPower(-gamepad1.right_stick_y);


            /*
            if (gamepad1.dpad_up) {


                //checks if the block is tilted so that the left side is further away and then accelerates that side.
                // The change in values is temporary
                // the added 3 is meant to have this only run at a certain margin of error
                if (robot.distanceSensor1.getDistance(DistanceUnit.CM) > robot.distanceSensor2.getDistance(DistanceUnit.CM) + 3) {
                    double speed = 0.5;
                    robot.leftIn.setPower(speed + 0.1);
                    robot.rightIn.setPower(speed - 0.1);
                    robot.leftBelt.setPower(speed);
                    robot.rightBelt.setPower(speed);
                }
                //does the same as above but checks the right
                else if (robot.distanceSensor2.getDistance(DistanceUnit.CM) > robot.distanceSensor2.getDistance(DistanceUnit.CM) + 3) {
                    double speed = 0.5;
                    robot.rightIn.setPower(speed + 0.1);
                    robot.leftIn.setPower(speed - 0.1);
                    robot.leftBelt.setPower(speed);
                    robot.rightBelt.setPower(speed);
                } else {
                    double speed = 0.5;
                    robot.rightIn.setPower(speed);
                    robot.leftIn.setPower(speed);
                    robot.leftBelt.setPower(speed);
                    robot.rightBelt.setPower(speed);
                }
            }
            else if (gamepad1.dpad_up)
            {
                robot.leftIn.setPower(-0.5);
                robot.rightIn.setPower(-0.5);
                robot.rightBelt.setPower(-0.5);
                robot.leftBelt.setPower(-0.5);
            }
            else
            {
                robot.leftIn.setPower(0);
                robot.rightIn.setPower(0);
                robot.rightBelt.setPower(0);
                robot.leftBelt.setPower(0);

            }




            */

            //endregion


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Driving", "left: (%.2f) right (%.2f)", leftPower, rightPower);
            telemetry.addData("range", String.format("%.01f m", robot.distanceSensor1.getDistance(DistanceUnit.CM)));
            telemetry.addData("range", String.format("%.01f m", robot.distanceSensor2.getDistance(DistanceUnit.CM)));
            telemetry.update();
            if(!opModeIsActive()){
                break;
            }
        }
    }
}
