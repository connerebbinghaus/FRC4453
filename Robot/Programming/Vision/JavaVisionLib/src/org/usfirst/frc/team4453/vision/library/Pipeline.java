package org.usfirst.frc.team4453.vision.library;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class represents a sequence of Pipeline Steps that run in parallel.
 * @author Conner Ebbinghaus
 *
 */
public class Pipeline extends PipelineStep
{
	public Pipeline() {
		super(null);
	}
	
	public Pipeline(Pipeline p) {
		super(p);
	}
	
	/**
	 * The PipelineSteps.
	 */
	private LinkedList<PipelineStep> steps = new LinkedList<PipelineStep>();
	
	/**
	 * Adds a step to the end of the pipeline.
	 * @param in
	 */
	public void add(PipelineStep in)
	{
		steps.add(in);
	}
	
	/**
	 * Starts all steps.
	 */
	public void start()
	{
		for(PipelineStep p : steps)
		{
			p.start();
		}
	}
	
	/**
	 * Stops all running steps.
	 * @throws InterruptedException If the calling thread is interrupted while waiting for a step to stop.
	 */
	public void stop() throws InterruptedException
	{
		for(PipelineStep p : steps)
		{
			p.stop();
		}
	}
	
	/**
	 * Returns the step after the specified one, or null if it does not exist.
	 * @param in The step to find the next of.
	 * @return The next step, or null if the specified step is not in this pipeline or it is the last step.
	 */
	public PipelineStep getNextStep(PipelineStep in)
	{
		int i = steps.lastIndexOf(in);
		if(i != -1 && steps.size() != i+1)
		{
			if(steps.get(i+1) instanceof Pipeline)
			{
				return ((Pipeline)steps.get(i+1)).steps.get(0);
			}
			return steps.get(i+1);
		}
		else if(getPipeline() != null)
		{
			return getPipeline().getNextStep(this);
		}
		return null;
	}
	
	/**
	 * Returns true if the specified step is the first of the entire pipeline (not just this segment).
	 * @param in The step.
	 * @return True if the step is first.
	 */
	public boolean isFirst(PipelineStep in)
	{
		if(getPipeline() != null)
		{
			return false;
		}
		int i = steps.lastIndexOf(in);
		return i==0;
	}

	@Override
	protected boolean execute(Data in) {
		return true;
	}
	
	private ArrayBlockingQueue<Data> recycleStore;
	
	public Data generate()
	{
		return recycleStore.poll();
	}
	
	public void recycle(Data in)
	{
		recycleStore.offer(in);
	}
}
