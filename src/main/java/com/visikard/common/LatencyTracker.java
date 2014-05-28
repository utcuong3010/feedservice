package com.visikard.common;

import java.util.concurrent.atomic.AtomicLong;

public class LatencyTracker {

	public LatencyTracker() {
		latencyPeakTime = 0L;
		latencyPeak = 0L;
		rpsPeakTime = 0L;
		rpsPeak = 0L;
		lastLatency = 0L;
		lastOpCount = 0L;
		lastSec = 0L;
		opsPerSec = 0L;
		lastSec = System.currentTimeMillis();
	}

	public void clear() {
		latencyPeakTime = 0L;
		latencyPeak = 0L;
		rpsPeak = 0L;
		rpsPeakTime = 0L;
		lastLatency = 0L;
		lastOpCount = 0L;
		lastSec = 0L;
		opsPerSec = 0L;
		curSec.set(-1L);
		opCount.set(0L);
		totalLatency.set(0L);
	}

	public void addNano(long nanos) {
		addMicro(nanos / 1000L);
	}

	public void addMicro(long micros) {
		opCount.incrementAndGet();
		totalLatency.addAndGet(micros);
		if (micros > latencyPeak) {
			latencyPeak = micros;
			latencyPeakTime = System.currentTimeMillis();
		}
	}

	public long getOpCount() {
		return opCount.get();
	}

	public long getTotalLatencyMicros() {
		return totalLatency.get();
	}

	public double getAvgLatency() {
		long ops = opCount.get();
		long n = totalLatency.get();
		if (ops == 0L)
			return 0.0D;
		else
			return (double) n / (double) ops;
	}

	public double getRecentLatencyMicros() {
		long ops;
		long n;
		long sec;
		double ret;
		ops = opCount.get();
		n = totalLatency.get();
		sec = System.currentTimeMillis() / 1000L;
		ret = 0.0D;
		opsPerSec = (ops - lastOpCount) / (sec - lastSec);
		if (opsPerSec > rpsPeak) {
			rpsPeak = opsPerSec;
			rpsPeakTime = System.currentTimeMillis();
		}
		if (ops - lastOpCount != 0L)
			ret = ((double) n - (double) lastLatency)
					/ (double) (ops - lastOpCount);
		lastLatency = n;
		lastOpCount = ops;
		lastSec = sec;

		lastLatency = n;
		lastOpCount = ops;
		lastSec = sec;
		return ret;
	}

	public long getLatencyPeak() {
		return latencyPeak;
	}

	public long getLatencyPeakTime() {
		return latencyPeakTime;
	}

	public long getRPSPeak() {
		return rpsPeak;
	}

	public long getRPSPeakTime() {
		return rpsPeakTime;
	}

	private void autoCalculate() {
		long sec = System.currentTimeMillis() / 1000L;
		long cur = curSec.get();
		if (cur == -1L)
			curSec.set(sec);
		else if (cur != sec) {
			getRecentLatencyMicros();
			curSec.getAndSet(sec);
		}
	}

	public long getTotalOpsPerSec() {
		return opsPerSec;
	}

	private long latencyPeakTime;
	private long latencyPeak;
	private long rpsPeakTime;
	private long rpsPeak;
	private final AtomicLong curSec = new AtomicLong(-1L);
	private final AtomicLong opCount = new AtomicLong(0L);
	private final AtomicLong totalLatency = new AtomicLong(0L);
	private long lastLatency;
	private long lastOpCount;
	private long lastSec;
	private long opsPerSec;
}
