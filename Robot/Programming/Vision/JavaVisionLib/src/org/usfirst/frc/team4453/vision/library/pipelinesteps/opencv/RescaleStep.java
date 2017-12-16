package org.usfirst.frc.team4453.vision.library.pipelinesteps.opencv;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4453.vision.library.Data;
import org.usfirst.frc.team4453.vision.library.Pipeline;
import org.usfirst.frc.team4453.vision.library.PipelineStep;

public class RescaleStep extends PipelineStep {
	private Size newsize;
	private String inName, outName;
	public RescaleStep(Pipeline p, Size size, String in, String out) {
		super(p);
		newsize = size;
		inName = in;
		outName = out;
	}

	@Override
	protected boolean execute(Data in) {
		Mat frame = (Mat) in.get(inName);
		Mat out = new Mat();
		Imgproc.resize(frame, out, newsize);
		in.put(outName, out);
		return true;
	}

}