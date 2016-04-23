package com.example.arwk.wifiscanner;

import android.test.suitebuilder.annotation.SmallTest;

import com.example.arwk.wifiscanner.WifiDataNetwork;

import junit.framework.TestCase;
import org.junit.Test;
import org.testng.annotations.Test;

import java.lang.Exception;

@SmallTest
public class ExampleUnitTest {

    @Test
    public void testAddition_isCorrect() throws Exception {
        assert(1 == 3);
    }
    public void testKanalCzestotliwosc() throws Exception {
        assert(1 == WifiDataNetwork.convertFrequencyToChannel(2412));
    }
}