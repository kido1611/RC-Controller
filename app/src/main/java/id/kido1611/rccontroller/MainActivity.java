package id.kido1611.rccontroller;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import id.kido1611.arduinoconnect.ArduinoConnect;
import id.kido1611.arduinoconnect.ArduinoConnectCallback;

public class MainActivity extends AppCompatActivity
        implements ArduinoConnectCallback{

    private ArduinoConnect mArduinoConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArduinoConnect = new ArduinoConnect(this, getSupportFragmentManager());
        mArduinoConnect.setCallback(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mArduinoConnect.showDialog();
            }
        });
    }

    public void sendString(String s){
        if(mArduinoConnect!=null)mArduinoConnect.sendMessage(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mArduinoConnect!=null)
            mArduinoConnect.disconnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onArduinoConnected() {

    }

    @Override
    public void onArduinoConnectFailed() {

    }

    @Override
    public void onSerialTextReceived(String s) {

    }

    @Override
    public void onBluetoothDeviceNotFound() {

    }

    @Override
    public void onBluetoothFailedEnabled() {

    }
}
