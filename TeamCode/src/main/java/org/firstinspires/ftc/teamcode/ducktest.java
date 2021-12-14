package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Vision.DuckDetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class  ducktest extends OpMode {

    OpenCvCamera webcam; // webcam object
    DuckDetector detector = new DuckDetector(telemetry); // duck pos object 0 left 1 middle 2 right

    @Override
    public void init() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()); // creates the webcam object
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId); // gets the physical webcam from the hardwaremap
        webcam.setPipeline(detector);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void loop() {
        webcam.startStreaming(320,240); // creates the stream to the driver station

        int pos = detector.getDuckPosition(); // gets the pos of the duck
        int num = detector.getNumContoursFound();

        if(pos == 0){
            telemetry.addData("Duck on the left", pos);
        } else if (pos == 1){
         telemetry.addData("duck is in the middle", pos);
        }else{
            telemetry.addData("duck is on the right", pos);
        }

        //adds a bunch of data to benchmark pipeline and controller hub
        telemetry.addData("Frame Count", webcam.getFrameCount());
        telemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
        telemetry.addData("Total frame time ms", webcam.getTotalFrameTimeMs());
        telemetry.addData("Pipeline time ms", webcam.getPipelineTimeMs());
        telemetry.addData("Overhead time ms", webcam.getOverheadTimeMs());
        telemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
        telemetry.addData("there are this many 'ducks' found", num);
    }
}
