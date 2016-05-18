package com.example.arwk.wifiscanner;

/**
 * Created by arwk on 2016-04-05.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Manifest;

import android.app.Activity;
import android.content.Context;
//import android.content.SharedPreferences;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

public class WifiData implements Parcelable {
    Context context;
    boolean DwaCztery;
    boolean Piec;
    //Context apllicationContext = MainActivity.getContextOfApplication();
    private List<WifiDataNetwork> mNetworks;
    public WifiData() {
        mNetworks = new ArrayList<WifiDataNetwork>();
    }
    public WifiData(Parcel in) {
        in.readTypedList(mNetworks, WifiDataNetwork.CREATOR);
    }
    public static final Parcelable.Creator<WifiData> CREATOR = new Parcelable.Creator<WifiData>() {
        public WifiData createFromParcel(Parcel in) {
            return new WifiData(in);
        }
        public WifiData[] newArray(int size) {
            return new WifiData[size];
        }

    };
    /**
     * Stores the last WiFi scan performed by {@link
     //* WifiManager.getScanResults()} creating a {@link WifiDataNetwork()} object
     * for each network detected.
     *
     * @param results
     *            list of networks detected
     */
    //dla każdej znalezionej sieci dodaje nowy wiersz w liście
    public void addNetworks(List<ScanResult> results) {

        mNetworks.clear();

        for (ScanResult result : results) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.contextOfApplication);
            Integer check1val = sharedPreferences.getInt("CheckBox_Val1", -1);
            Integer check2Val = sharedPreferences.getInt("CheckBox_Val2", -1);
            if(check1val == 1){
                if(result.frequency < 2500){
                mNetworks.add(new WifiDataNetwork(result));}}

            //if(check2Val == 1){
            //    if(result.frequency > 2500){
                //mNetworks.add(new WifiDataNetwork(result));}//}

        }
                //mNetworks.add(new WifiDataNetwork(result));


            Collections.sort(mNetworks);
        }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mNetworks);
    }

    /**
     * @return Returns a string containing a concise, human-readable description
     *         of this object.
     */
    @Override
    //funkcja zwracająca Empty data przy braku sieci lub ilość dostępnych sieci
    public String toString() {
        if (mNetworks == null || mNetworks.size() == 0)
            return "Empty data";
        else
            return mNetworks.size() + " networks data";
    }

    /**
     * @return Returns the list of scanned networks
     */
    //zwraca listę dostępnych sieci
    public List<WifiDataNetwork> getNetworks() {
        return mNetworks;
    }
}