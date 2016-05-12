package com.example.arwk.wifiscanner;

/**
 * Created by arwk on 2016-04-05.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import com.example.arwk.wifiscanner.CustomException;

import java.io.IOException;

public class WifiDataNetwork implements Comparable<WifiDataNetwork>, Parcelable {
    private String bssid;
    private String ssid;
    private String capabilities;
    private int frequency;
    private int level;
    private long timestamp;
    static int blad;



    public WifiDataNetwork(ScanResult result) {
        bssid = result.BSSID;
        ssid = result.SSID;
        capabilities = result.capabilities;
        frequency = result.frequency;
        level = result.level;
        timestamp = System.currentTimeMillis();

    }

    public WifiDataNetwork(Parcel in) {
        bssid = in.readString();
        ssid = in.readString();
        capabilities = in.readString();
        frequency = in.readInt();
        level = in.readInt();
        timestamp = in.readLong();
    }

    public static final Parcelable.Creator<WifiDataNetwork> CREATOR = new Parcelable.Creator<WifiDataNetwork>() {
        public WifiDataNetwork createFromParcel(Parcel in) {
            return new WifiDataNetwork(in);
        }

        public WifiDataNetwork[] newArray(int size) {
            return new WifiDataNetwork[size];
        }
    };


    public static float convertFrequencyToChannel(int freq){
        if (freq >= 2412 && freq <= 2472) {
            float return1 = (freq - 2412f) / 5f + 1f;
            return return1;
        }
        if (freq == 2484) {
            return 14;
        }
        if (freq >= 5170 && freq <= 5825) {
            float return1 = (freq - 5170f) / 5f + 34f;
            return return1;
        }
        else
            try {
                //throw new RuntimeException("blad");
                throw new CustomException("blad");
            }
            catch(CustomException e){
                blad = freq;
            }
        return -1;

    }






    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(WifiDataNetwork another) {
        return another.level - this.level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bssid);
        dest.writeString(ssid);
        dest.writeString(capabilities);
        dest.writeInt(frequency);
        dest.writeInt(level);
        dest.writeLong(timestamp);
    }

    @Override
    public String toString() {
        return ssid + " addr:" + bssid + " lev:" + level + "dBm freq:" + frequency + "MHz cap:" + capabilities;
    }

}
