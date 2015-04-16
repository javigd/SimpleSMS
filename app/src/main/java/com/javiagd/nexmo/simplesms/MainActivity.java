package com.javiagd.nexmo.simplesms;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsException;
import com.javiagd.nexmo.simplesms.messaging.MessagingClient;
import com.javiagd.nexmo.simplesms.messaging.NexmoMessagingClient;
import com.javiagd.nexmo.simplesms.models.Message;
import com.javiagd.nexmo.simplesms.models.Response;
import com.javiagd.nexmo.simplesms.utils.ConfigManager;


public class MainActivity extends ActionBarActivity {

    private MessagingClient messagingClient;
    private ConfigManager config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // Get the config values defined in assets/config.properties
            config = ConfigManager.getInstance(this);
            // Get an instance of your favourite Message Client
            messagingClient = NexmoMessagingClient.getInstance(
                    config.getProperty("default_api_key"),
                    config.getProperty("default_api_secret"));
        } catch (SimpleSmsException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
            Log.e("MainActivity", e.getMessage(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return false;
    }

    public void sendMessage(View v) {
        // Disable button in order to avoid re-sending the message
        Button sendBtn = (Button) findViewById(R.id.send_button);
        sendBtn.setActivated(false);

        // Send the message Asynchronously
        new MessageTask().execute();
    }

    // AsyncTask responsible for the Asynchronous Messaging
    private class MessageTask extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... params) {

            Response operationResults = null;

            final EditText sender = (EditText) findViewById(R.id.from_edit_text);
            final EditText receptor = (EditText) findViewById(R.id.to_edit_text);
            final EditText message = (EditText) findViewById(R.id.message_body);

            // Send the message
            try {
                Message textMessage = new Message(sender.getText().toString(),
                        receptor.getText().toString(),
                        message.getText().toString());
                operationResults = messagingClient.sendMessage(textMessage);
            } catch (SimpleSmsException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.e("MainActivity", e.getMessage(), e);
            }
            return operationResults;
        }

        @Override
        protected void onPostExecute(Response response) {
            // Show operation results
            TextView responseText = (TextView) findViewById(R.id.response_dyn_text);
            responseText.setText(response.toString());
            // Re-enable button
            Button sendBtn = (Button) findViewById(R.id.send_button);
            sendBtn.setActivated(false);
        }
    }
}
