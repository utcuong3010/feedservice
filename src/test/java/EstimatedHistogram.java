

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;

public class EstimatedHistogram {

    /**
     * This series starts at 1 and grows by 1.2 each time (rounding down and
     * removing duplicates). It goes from 1 to around 30M, which will give us
     * timing resolution from microseconds to 30 seconds, with less precision as
     * the numbers get larger.
     */
    //tinh theo microsecs
    private static final long[] bucketOffsets = {1, 10, 100, 200, 300, 400, 500, 600, 700, 800, 900, //microsecs
        1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, //msecs
        10000, 20000, 50000, 100000, 200000, 300000, 400000, 500000, //msecs
        1000000, 2000000, 5000000, 10000000};					//secs
    private static final int numBuckets = bucketOffsets.length + 1;
    final AtomicLongArray buckets;

    public EstimatedHistogram() {
        buckets = new AtomicLongArray(numBuckets);
    }

    public void add(long n) {
        int index = Arrays.binarySearch(bucketOffsets, n);
        if (index < 0) {
            // inexact match, find closest bucket
            index = -index - 1;
        } else {
            // exact match, so we want the next highest one
            index += 1;
        }
        buckets.incrementAndGet(index);
    }

    public long[] getBucketOffset() {
        return this.bucketOffsets;
    }

    public void clear() {
        for (int i = 0; i < numBuckets; i++) {
            buckets.set(i, 0L);
        }
    }

    public long[] get(Boolean reset) {
        long[] rv = new long[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            rv[i] = buckets.get(i);
        }

        if (reset) {
            for (int i = 0; i < numBuckets; i++) {
                buckets.set(i, 0L);
            }
        }
        return rv;
    }
}
