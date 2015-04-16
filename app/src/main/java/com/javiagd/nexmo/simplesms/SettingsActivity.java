package com.javiagd.nexmo.simplesms;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsException;
import com.javiagd.nexmo.simplesms.messaging.MessagingClient;
import com.javiagd.nexmo.simplesms.messaging.NexmoMessagingClient;
import com.javiagd.nexmo.simplesms.utils.ConfigManager;


public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void savePreferences(View v) {
        EditText apiKeyEdt = (EditText) findViewById(R.id.api_key_edit);
        EditText apiSecretEdt = (EditText) findViewById(R.id.api_secret_edit);

        String apiKey = apiKeyEdt.getText().toString();
        String apiSecret = apiSecretEdt.getText().toString();

        NexmoMessagingClient client = NexmoMessagingClient.getInstance(null, null);

        client.setApiKey(apiKey);
        client.setApiSecret(apiSecret);
        Toast.makeText(getApplicationContext(), "Your preferences have been saved.",
                Toast.LENGTH_LONG).show();
    }

    public void restoreDefault(View v) {
        // Restore the default config values defined in assets/config.properties
        ConfigManager config;
        NexmoMessagingClient messagingClient = null;
        try {
            config = ConfigManager.getInstance(this);
            messagingClient = NexmoMessagingClient.getInstance(null, null);
            messagingClient.setApiKey(config.getProperty("default_api_key"));
            messagingClient.setApiSecret(config.getProperty("default_api_secret"));
        } catch (SimpleSmsException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
            Log.e("SettingsActivity", e.getMessage(), e);
        }
        Toast.makeText(getApplicationContext(), "All settings restored by default.",
                Toast.LENGTH_LONG).show();
    }
}
