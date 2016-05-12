package com.example.arwk.wifiscanner;

/**
 * Created by arwk on 2016-05-06.
 */
public class ConvertFreqToChan {
    private int frequency;

    public static int convertFrequencyToChannel(int freq) {
        if (freq >= 2412 && freq <= 2472) {
            return (freq - 2412) / 5 + 1;
        }
        if (freq == 2484) {
            return 14;
        }
        if (freq >= 5170 && freq <= 5825) {
            return (freq - 5170) / 5 + 34;
        } else {
            return -1;
        }
    }

}
