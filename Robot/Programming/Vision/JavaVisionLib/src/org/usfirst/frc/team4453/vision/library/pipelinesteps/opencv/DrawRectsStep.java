package org.usfirst.frc.team4453.vision.library.pipelinesteps.opencv;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4453.vision.library.Data;
import org.usfirst.frc.team4453.vision.library.Pipeline;
import org.usfirst.frc.team4453.vision.library.PipelineStep;

public class DrawRectsStep extends PipelineStep {
	private String imgName, rectsName;
	private Scalar drawColor;
	public DrawRectsStep(Pipeline p, Scalar color, String img, String rects) {
		super(p);
		drawColor = color;
		imgName = img;
		rectsName = rects;
	}

	@Override
	protected boolean execute(Data in) {
		Mat frame = (Mat) in.get(imgName);
		MatOfRect rects = (MatOfRect) in.get(rectsName);
		for(Rect r : rects.toList())
		{
			Imgproc.rectangle(frame, r.tl(), r.br(), drawColor);
		}
		in.put(imgName, frame);
		return true;
	}

}
