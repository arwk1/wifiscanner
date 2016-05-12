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
    public void test_2_4_Ghz() throws Exception {
        //assertTrue(-1 == WifiDataNetwork.convertFrequencyToChannel(2211));//dolny zakres kanału
        assertTrue(1 == com.example.arwk.wifiscanner.WifiDataNetwork.convertFrequencyToChannel(2412));//inne wywoładnie funkcji
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

    }
    public void test_5_Ghz() throws Exception {
        assertTrue(36 == WifiDataNetwork.convertFrequencyToChannel(5180));
        assertTrue(40 == WifiDataNetwork.convertFrequencyToChannel(5200));
        assertTrue(44 == WifiDataNetwork.convertFrequencyToChannel(5220));
        assertTrue(48 == WifiDataNetwork.convertFrequencyToChannel(5240));
        assertTrue(52 == WifiDataNetwork.convertFrequencyToChannel(5260));
        assertTrue(56 == WifiDataNetwork.convertFrequencyToChannel(5280));
        assertTrue(60 == WifiDataNetwork.convertFrequencyToChannel(5300));
        assertTrue(64 == WifiDataNetwork.convertFrequencyToChannel(5320));
        assertTrue(100 == WifiDataNetwork.convertFrequencyToChannel(5500));
        assertTrue(102 == WifiDataNetwork.convertFrequencyToChannel(5510));
        assertTrue(104 == WifiDataNetwork.convertFrequencyToChannel(5520));
        assertTrue(106 == WifiDataNetwork.convertFrequencyToChannel(5530));
        assertTrue(108 == WifiDataNetwork.convertFrequencyToChannel(5540));
        assertTrue(110 == WifiDataNetwork.convertFrequencyToChannel(5550));
        assertTrue(112 == WifiDataNetwork.convertFrequencyToChannel(5560));
        assertTrue(114 == WifiDataNetwork.convertFrequencyToChannel(5570));
        assertTrue(116 == WifiDataNetwork.convertFrequencyToChannel(5580));
        assertTrue(118 == WifiDataNetwork.convertFrequencyToChannel(5590));
        assertTrue(120 == WifiDataNetwork.convertFrequencyToChannel(5600));
        assertTrue(122 == WifiDataNetwork.convertFrequencyToChannel(5610));
        assertTrue(124 == WifiDataNetwork.convertFrequencyToChannel(5620));
        assertTrue(126 == WifiDataNetwork.convertFrequencyToChannel(5630));
        assertTrue(128 == WifiDataNetwork.convertFrequencyToChannel(5640));
        assertTrue(132 == WifiDataNetwork.convertFrequencyToChannel(5660));
        assertTrue(134 == WifiDataNetwork.convertFrequencyToChannel(5670));
        assertTrue(136 == WifiDataNetwork.convertFrequencyToChannel(5680));
        assertTrue(138 == WifiDataNetwork.convertFrequencyToChannel(5690));
        assertTrue(140 == WifiDataNetwork.convertFrequencyToChannel(5700));
        assertTrue(142 == WifiDataNetwork.convertFrequencyToChannel(5710));
        assertTrue(144 == WifiDataNetwork.convertFrequencyToChannel(5720));
        assertTrue(149 == WifiDataNetwork.convertFrequencyToChannel(5745));
        assertTrue(151 == WifiDataNetwork.convertFrequencyToChannel(5755));
        assertTrue(153 == WifiDataNetwork.convertFrequencyToChannel(5765));
        assertTrue(157 == WifiDataNetwork.convertFrequencyToChannel(5785));
        assertTrue(161 == WifiDataNetwork.convertFrequencyToChannel(5805));//bylo 5805
    }
    public void test_2_4_Ghz_k1_do_k13() throws Exception{

        int [] czestotliwosci = new int[] {2412, 2417, 2422, 2427};
        int [] kanaly = new int[] { 1, 2, 3, 4};
        for (int i = 0; i < czestotliwosci.length; i++)
                assertTrue(kanaly[i] == WifiDataNetwork.convertFrequencyToChannel(czestotliwosci[i]));

    }
    public void test_5_Ghz_k36_do_k161() throws Exception{
        int [] czestotliwosci = new int[] {5805, 5785, 5765, 5755};
        int [] kanaly = new int[] {161, 157, 153, 151};
        for (int i = 0; i < czestotliwosci.length; i++)
                assertTrue(kanaly[i] == WifiDataNetwork.convertFrequencyToChannel(czestotliwosci[i]));
    }
    public void testowanie_wyjatku() throws Exception{
        try {
            WifiDataNetwork.convertFrequencyToChannel(5825);
            }
        catch(RuntimeException wyjatek){

            System.out.println(wyjatek.getMessage());
            }


    }


}