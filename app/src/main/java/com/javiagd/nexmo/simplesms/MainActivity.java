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

import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsError;
import com.javiagd.nexmo.simplesms.exceptions.SimpleSmsException;
import com.javiagd.nexmo.simplesms.messaging.MessagingClient;
import com.javiagd.nexmo.simplesms.messaging.NexmoMessagingClient;
import com.javiagd.nexmo.simplesms.models.DeliveryReceipt;
import com.javiagd.nexmo.simplesms.models.Message;
import com.javiagd.nexmo.simplesms.models.Response;
import com.javiagd.nexmo.simplesms.utils.ConfigManager;


public class MainActivity extends ActionBarActivity {

    private MessagingClient messagingClient;
    private ConfigManager config;

    // Predefined Maximum delivery receipt requests
    private final int MAX_DELIVERY_RECEIPT_REQUESTS = 5;
    // Predefined time between requests in ms
    private final int RECEIPT_REQUEST_FREQUENCY = 2000;

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

    /**
     * AsyncTask responsible for the Asynchronous Messaging
     */
    private class MessageTask extends AsyncTask<Void, Response, DeliveryReceipt> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Sending Message...",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected DeliveryReceipt doInBackground(Void... params) {

            int reqCounter = 0;
            Response operationResults = null;
            DeliveryReceipt deliveryReceipt = null;

            final EditText sender = (EditText) findViewById(R.id.from_edit_text);
            final EditText receptor = (EditText) findViewById(R.id.to_edit_text);
            final EditText message = (EditText) findViewById(R.id.message_body);

            try {
                // Send the message and retrieve response
                Message textMessage = new Message(sender.getText().toString(),
                        receptor.getText().toString(),
                        message.getText().toString());
                operationResults = messagingClient.sendMessage(textMessage);
                // Show response feedback to user
                publishProgress(operationResults);
                // Get delivery receipt
                while (deliveryReceipt == null && reqCounter < MAX_DELIVERY_RECEIPT_REQUESTS) {
                    deliveryReceipt = messagingClient.getDeliveryReceipt(operationResults.getMessageId());
                    reqCounter++;
                }
                if(reqCounter == MAX_DELIVERY_RECEIPT_REQUESTS)
                    throw new SimpleSmsException(SimpleSmsError.CALLBACK_COM);
            } catch (SimpleSmsException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.e("MainActivity", e.getMessage(), e);
            }

            return deliveryReceipt;
        }

        @Override
        protected void onProgressUpdate(Response... response) {
            //Update the user name and image
            TextView responseText = (TextView) findViewById(R.id.response_dyn_text);
            responseText.setText(response.toString());
        }

        @Override
        protected void onPostExecute(DeliveryReceipt receipt) {
            // Show operation results
            TextView responseText = (TextView) findViewById(R.id.response_dyn_text);
            responseText.setText(responseText.getText().toString() + "\n\n" + receipt.toString());
            // Re-enable button
            Button sendBtn = (Button) findViewById(R.id.send_button);
            sendBtn.setActivated(false);
        }
    }

}
