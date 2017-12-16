package org.usfirst.frc.team4453.vision;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team4453.vision.library.Pipeline;
import org.usfirst.frc.team4453.vision.library.pipelinesteps.opencv.VideoCaptureStep;

public class RPIVision2017 {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Pipeline pipelineGear = new Pipeline();
		Pipeline pipelineBoiler = new Pipeline();
		
		VideoCapture gearCam = new VideoCapture();
		gearCam.open("http://rpicameragear.local:1180/?action=stream&dummy=param.mjpg");
		pipelineGear.add(new VideoCaptureStep(pipelineGear, gearCam, "frameIn"));
		
		VideoCapture boilerCam = new VideoCapture();
		boilerCam.open("http://rpicameraboiler.local:1180/?action=stream&dummy=param.mjpg");
		pipelineBoiler.add(new VideoCaptureStep(pipelineBoiler, boilerCam, "frameIn"));
		
	}

}
