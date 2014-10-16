package ymc.ch.bearingexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class BearingDisplay extends Activity implements BearingToNorthProvider.ChangeEventListener {

    private TextView mTxtBearing;

    private BearingToNorthProvider mBearingProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bearing_display);
        mTxtBearing = (TextView) findViewById(R.id.txtBearing);
        mBearingProvider = new BearingToNorthProvider(this);
        mBearingProvider.setChangeEventListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mBearingProvider.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mBearingProvider.stop();
    }

    @Override
    public void onBearingChanged(double bearing)
    {
        mTxtBearing.setText(String.format("Current Bearing: %f", bearing));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bearing_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
