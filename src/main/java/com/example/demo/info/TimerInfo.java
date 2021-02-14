package com.example.demo.info;

public class TimerInfo {

	private int totalFireCount;
	private int remainingFireCount;
	private boolean runForever;
	private long repeatInterval;
	private long initialOfset;
	private String callbackDate;

	public int getTotalFireCount() {
		return totalFireCount;
	}

	public void setTotalFireCount(int totalFireCount) {
		this.totalFireCount = totalFireCount;
	}

	public boolean isRunForever() {
		return runForever;
	}

	public void setRunForever(boolean runForever) {
		this.runForever = runForever;
	}

	public long getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public long getInitialOfset() {
		return initialOfset;
	}

	public void setInitialOfset(long initialOfset) {
		this.initialOfset = initialOfset;
	}

	public String getCallbackDate() {
		return callbackDate;
	}

	public void setCallbackDate(String callbackDate) {
		this.callbackDate = callbackDate;
	}

	public int getRemainingFireCount() {
		return remainingFireCount;
	}

	public void setRemainingFireCount(int remainingFireCount) {
		this.remainingFireCount = remainingFireCount;
	}

}
