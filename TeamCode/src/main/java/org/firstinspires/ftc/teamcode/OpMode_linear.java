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
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


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
    private int loopCount = 0; // this var is created in order to count the number of loops in a second

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.init(hardwareMap);

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP
        while(true) {
            loopCount++;

            // region Petr

            //comented out to avoif competing methods of setting the motot powers
            /*double leftPower = Range.clip(gamepad1.left_stick_y, -1.0, 1.0);
            double rightPower = Range.clip(gamepad1.right_stick_y, -1.0, 1.0);
            robot.leftDrive.setPower(leftPower);
            robot.rightDrive.setPower(rightPower);*/

            if (gamepad1.dpad_up){
                double speed = 0.5;
                robot.leftIn.setPower(speed);
                robot.rightIn.setPower(speed);
                robot.leftBelt.setPower(speed);
                robot.rightBelt.setPower(speed);
            }else if(gamepad1.dpad_down){
                double speed = -0.5;
                robot.leftIn.setPower(speed);
                robot.rightIn.setPower(speed);
                robot.leftBelt.setPower(speed);
                robot.rightBelt.setPower(speed);
            }else{
                double speed = 0.0;
                robot.leftIn.setPower(speed);
                robot.rightIn.setPower(speed);
                robot.leftBelt.setPower(speed);
                robot.rightBelt.setPower(speed);
            }

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


            //endregion


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Driving", "left: (%.2f) right (%.2f)", leftPower, rightPower);
            telemetry.addData("Loop count", loopCount);
            telemetry.update();
            if(!opModeIsActive()){
                break;
            }
        }
    }
}
