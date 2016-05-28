package com.knapsack;

public class SwitchingTime implements Comparable<SwitchingTime> {

	private int time;
	private int relativePower;
	private int absolutePower;
	private int runningTime;

	public SwitchingTime() {
		super();
	}

	public SwitchingTime(int time, int relativePower, int absolutePower) {
		super();
		this.time = time;
		this.relativePower = relativePower;
		this.absolutePower = absolutePower;
	}

	public SwitchingTime(int time, int relativePower, int absolutePower,
			int runningTime) {
		super();
		this.time = time;
		this.relativePower = relativePower;
		this.absolutePower = absolutePower;
		this.runningTime = runningTime;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getRelativePower() {
		return relativePower;
	}

	public void setRelativePower(int relativePower) {
		this.relativePower = relativePower;
	}

	public int getAbsolutePower() {
		return absolutePower;
	}

	public void setAbsolutePower(int absolutePower) {
		this.absolutePower = absolutePower;
	}

	@Override
	public int compareTo(SwitchingTime o) {
		Integer time = this.getTime();
		Integer st = o.getTime();
		return time.compareTo(st);
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

}
