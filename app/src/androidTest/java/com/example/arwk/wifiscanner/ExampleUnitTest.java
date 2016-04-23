package com.example.arwk.wifiscanner;

import android.test.suitebuilder.annotation.SmallTest;

import com.example.arwk.wifiscanner.WifiDataNetwork;

import junit.framework.TestCase;
import org.junit.Test;
//import org.testing.annotations.Test;

import java.lang.Exception;

public class ExampleUnitTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testAddition_isCorrect() throws Exception {
        assertTrue(3 == 3);
    }
    public void testCzestotliwoscNaKanal() throws Exception {
        assertTrue(1 == WifiDataNetwork.convertFrequencyToChannel(2412));
        assertTrue(2 == WifiDataNetwork.convertFrequencyToChannel(2417));
        assertTrue(3 == WifiDataNetwork.convertFrequencyToChannel(2422));
        assertTrue(4 == WifiDataNetwork.convertFrequencyToChannel(2427));
        assertTrue(5 == WifiDataNetwork.convertFrequencyToChannel(2432));
        assertTrue(6 == WifiDataNetwork.convertFrequencyToChannel(2437));
        assertTrue(7 == WifiDataNetwork.convertFrequencyToChannel(2442));
        assertTrue(8 == WifiDataNetwork.convertFrequencyToChannel(2447));
        assertTrue(9 == WifiDataNetwork.convertFrequencyToChannel(2452));
        assertTrue(10 == WifiDataNetwork.convertFrequencyToChannel(2457));
        assertTrue(11 == WifiDataNetwork.convertFrequencyToChannel(2462));
        assertTrue(12 == WifiDataNetwork.convertFrequencyToChannel(2467));
        assertTrue(13 == WifiDataNetwork.convertFrequencyToChannel(2472));
        assertTrue(14 == WifiDataNetwork.convertFrequencyToChannel(2484));
        //rozpoczynają się testy dla 5GHz
        assertTrue(36 == WifiDataNetwork.convertFrequencyToChannel(5180));
        assertTrue(40 == WifiDataNetwork.convertFrequencyToChannel(5200));
        assertTrue(44 == WifiDataNetwork.convertFrequencyToChannel(5220));
        assertTrue(48 == WifiDataNetwork.convertFrequencyToChannel(5240));
        assertTrue(52 == WifiDataNetwork.convertFrequencyToChannel(5260));
        assertTrue(56 == WifiDataNetwork.convertFrequencyToChannel(5280));
        assertTrue(60 == WifiDataNetwork.convertFrequencyToChannel(5300));
        assertTrue(64 == WifiDataNetwork.convertFrequencyToChannel(5320));
        assertTrue(149 == WifiDataNetwork.convertFrequencyToChannel(5745));
        assertTrue(153 == WifiDataNetwork.convertFrequencyToChannel(5765));
        assertTrue(157 == WifiDataNetwork.convertFrequencyToChannel(5785));
        assertTrue(161 == WifiDataNetwork.convertFrequencyToChannel(5805));
    }

    public void testjakistam() throws Exception{

    }

}