package org.usfirst.frc.team4453.vision.library.pipelinesteps.opencv;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;
import org.usfirst.frc.team4453.vision.library.Data;
import org.usfirst.frc.team4453.vision.library.Pipeline;
import org.usfirst.frc.team4453.vision.library.PipelineStep;

public class CascadeClassifierStep extends PipelineStep {
	private CascadeClassifier classifier;
	private String inName, outName;
	public CascadeClassifierStep(Pipeline p, CascadeClassifier c, String in, String out) {
		super(p);
		classifier = c;
		inName = in;
		outName = out;
	}

	@Override
	protected boolean execute(Data in) {
		Mat frame = (Mat) in.get(inName);
		MatOfRect out = new MatOfRect(); 
		classifier.detectMultiScale(frame, out);
		in.put(outName, out);
		return true;
	}

}
