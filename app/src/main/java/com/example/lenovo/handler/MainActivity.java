package com.example.lenovo.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create Inner Thread Class
        Thread background = new Thread(new Runnable() {


            public void run() {
                for (int i = 0; i < 10; i++) {
                    threadMsg(Integer.toString(i));
                }
            }
        });

        background.start();
    }

    void threadMsg(String msg) {

        if (!msg.equals(null) && !msg.equals("")) {
            Message msgObj = handler.obtainMessage();
            Bundle b = new Bundle();
            b.putString("message", msg);
            msgObj.setData(b);
            handler.sendMessage(msgObj);
        }
    }

    // Define the Handler that receives messages from the thread and update the progress
    final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            String aResponse = msg.getData().getString("message");

            if ((null != aResponse)) {

                // ALERT MESSAGE
                Toast.makeText(
                        getBaseContext(),
                        "Server Response: " + aResponse,
                        Toast.LENGTH_SHORT).show();
                Log.d("MYLOG", "MainActivity: "+"handleMessage: "+"aResponse: "+aResponse);
            } else {

                // ALERT MESSAGE
                Toast.makeText(
                        getBaseContext(),
                        "Not Got Response From Server.",
                        Toast.LENGTH_SHORT).show();
            }

        }
    };


}
