package com.example.arwk.wifiscanner;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;

import android.support.v7.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private WifiData mWifiData;
    private static int flag = 0;
    //private static final String PREF_IS_FIRST_RUN="pierwszeUruchomienie";
    private SharedPreferences prefs;
    private static final int RESULT_PERMS_INITIAL=1339; // nie mam pojęcia co oznacz cyfra
    private static final int RESULT_PERMS_SCAN=1340;
    private static final String PREF_IS_FIRST_RUN="firstRun";
    private static final String[] PERMS_SCAN_WIFI={
            //ACCESS_NETWORK_STATE,
            //ACCESS_WIFI_STATE
            ACCESS_FINE_LOCATION
    };
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //ponizsze musiałem wyłączyć ponieważ emulator nie ma wifi a moje telefony mają api starsze niż 23 :)
        if (wifi.isWifiEnabled()) {
        }
        else{
            showSimplePopUp();
        }
            mWifiData = null;
            if (isFirstRun() && useRuntimePermissions()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(PERMS_SCAN_WIFI, RESULT_PERMS_SCAN);
                }
            }
            // set receiver
            MainActivityReceiver mReceiver = new MainActivityReceiver();
            LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(Constants.APP_NAME));

            // launch WiFi service
            Intent intent = new Intent(this, WifiService.class);
            startService(intent);

            // recover retained object
            mWifiData = (WifiData) getLastNonConfigurationInstance();

            // set layout
            setContentView(R.layout.activity_main);
            plotData();
            //poniżej reakcja na przycisk zamknięcia
            Button button = (Button) findViewById(R.id.exit);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    finish();

                    //finish();
                    //System.exit(0);
                    //finish();
                    //System.exit(0);
                }
            });
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_WIFI_STATE}, RESULT_PERMS_INITIAL);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //@Override
    //public Object onRetainNonConfigurat       ionInstance() {
    //   return mWifiData;
    //}

    public void plotData() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.scanningResultBlock);

        linearLayout.removeAllViews();

        if (mWifiData == null) {
            Log.d(TAG, "Plotting data: no networks");
            TextView noDataView = new TextView(this);
            noDataView.setText(getResources().getString(R.string.wifi_no_data));
            noDataView.setGravity(Gravity.CENTER_HORIZONTAL);
            noDataView.setPadding(0, 50, 0, 0);
            noDataView.setTextSize(18);
            linearLayout.addView(noDataView);
        } else {
            Log.d(TAG, "Plotting data");

            TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT);

            TableLayout tableLayout = new TableLayout(this);
            tableLayout.setLayoutParams(tableParams);
            tableLayout.setStretchAllColumns(true);

            // row header
            TableRow tableRowHeader = new TableRow(this);
            tableRowHeader.setLayoutParams(rowParams);

            TextView ssidText = new TextView(this);
            ssidText.setText(getResources().getString(R.string.ssid_text));
            ssidText.setTypeface(null, Typeface.BOLD);

            TextView chText = new TextView(this);
            chText.setText(getResources().getString(R.string.ch_text));
            chText.setTypeface(null, Typeface.BOLD);

            TextView rxText = new TextView(this);
            rxText.setText(getResources().getString(R.string.rx_text));
            rxText.setTypeface(null, Typeface.BOLD);

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                TextView bssidText = new TextView(this);
                bssidText.setText(getResources().getString(R.string.bssid_text));
                bssidText.setTypeface(null, Typeface.BOLD);

                tableRowHeader.addView(ssidText);
                tableRowHeader.addView(bssidText);
                tableRowHeader.addView(chText);
                tableRowHeader.addView(rxText);
            } else {
                tableRowHeader.addView(ssidText);
                tableRowHeader.addView(chText);
                tableRowHeader.addView(rxText);
            }

            tableLayout.addView(tableRowHeader);

            // rows data
            for (WifiDataNetwork net : mWifiData.getNetworks()) {
                TextView ssidVal = new TextView(this);
                ssidVal.setText(net.getSsid());

                TextView chVal = new TextView(this);
                chVal.setText(String.valueOf(WifiDataNetwork.convertFrequencyToChannel(net.getFrequency())));

                TextView rxVal = new TextView(this);
                rxVal.setText(String.valueOf(net.getLevel()));

                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(rowParams);

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    TextView bssidVal = new TextView(this);
                    bssidVal.setText(net.getBssid());

                    rxVal.setText(String.valueOf(net.getLevel()) + " dBm");

                    tableRow.addView(ssidVal);
                    tableRow.addView(bssidVal);
                    tableRow.addView(chVal);
                    tableRow.addView(rxVal);
                } else {
                    tableRow.addView(ssidVal);
                    tableRow.addView(chVal);
                    tableRow.addView(rxVal);
                }

                tableLayout.addView(tableRow);
            }

            linearLayout.addView(tableLayout);
        }
    }

    public class MainActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            WifiData data = (WifiData) intent.getParcelableExtra(Constants.WIFI_DATA);

            if (data != null) {
                mWifiData = data;
                plotData();
            }
        }

    }
    private void showSimplePopUp() {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Wifi jest wyłączone");
        helpBuilder.setMessage("Aby używać aplikacji musisz włączyć Wifi");
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }
    private boolean isFirstRun(){
        boolean result=prefs.getBoolean(PREF_IS_FIRST_RUN, true); //zmienione z true na false - testy
        if (result) {
            prefs.edit().putBoolean(PREF_IS_FIRST_RUN, false).apply();
        }
        return(result);
    }

    private boolean useRuntimePermissions() {
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        //TODO
    }
}