package com.selfgrowth.model.util;

import com.selfgrowth.model.keyResult.KeyResult;

public class OkrUtils {
    public static int[][] chunkArray(int[] array, int chunkSize) {
        int numOfChunks = (int) Math.ceil((double) array.length / chunkSize);
        int[][] output = new int[numOfChunks][];

        for (int i = 0; i < numOfChunks; i++) {
            int start = i * chunkSize;
            int length = Math.min(array.length - start, chunkSize);

            int[] temp = new int[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = temp;
        }
        return output;
    }

    public static KeyResult[][] splitArray(int[] array, int chunk) {
        int row = Math.min(chunk, array.length);
        int min = array.length / row;
        int max = min + 1;
        DebugLog.logMessage("MIN: " + min + "MAX: " + max);
        KeyResult[][] output = new KeyResult[row][];

        int start = 0;
        for (int i = 0; i < row; i++) {
            if (Math.ceil((double) (array.length - start) / min) != (row - i)) {
                start += max;
                KeyResult[] temp = new KeyResult[max];
                System.arraycopy(array, start - max,temp, 0, max);
                output[i] = temp;

                DebugLog.logMessage("I = " + i + " START: " + start + " max: " + max + " T:  " +  (row - i) + " K: " + (array.length - start));
            } else {
                KeyResult[] temp = new KeyResult[min];
                System.arraycopy(array, start, temp, 0, min);
                output[i] = temp;
                start += min;
                DebugLog.logMessage("I = " + i + " START: " + start + " min: " + min);
            }
        }
        return output;
    }
}
