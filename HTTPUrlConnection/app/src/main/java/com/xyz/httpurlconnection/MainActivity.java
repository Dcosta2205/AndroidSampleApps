package com.xyz.httpurlconnection;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView mTvTitle;
    private Button mBtnGetRequest;
    private String API_URL = " ";
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(API_URL);
                /*
                Open the connection and connect to the server url
                 */
                urlConnection = (HttpURLConnection) url.openConnection();
                /*
                This method gives stream of data from the server
                 */
                InputStream inputStream = urlConnection.getInputStream();
                /*
                This method is used to read the stream of data received from the server.
                 */
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                /*
                inputStreamReader.read() will give one element at a time , so data will have the first element
                 */
                int data = inputStreamReader.read();
                /*
                StringBuffer class is used to to build the json response in the string format. StringBuffer
                class is mutable and does not create new objects every time as we append the data unlike strings
                 */
                StringBuffer stringBuffer = new StringBuffer();

                /*
                when data is -1, that means we have reached the end of response
                 */
                while (data != -1) {
                    /*
                    the data will be in byte format, cast it to char
                     */
                    char responseChar = (char) data;
                    stringBuffer.append(responseChar);
                    data = inputStreamReader.read();
                }
                Log.d("Lloyd", stringBuffer.toString());

                buildResponseModel(stringBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTvTitle = findViewById(R.id.tvTitle);
        mBtnGetRequest = findViewById(R.id.btnGetRequest);
        mBtnGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API_URL = "https://jsonplaceholder.typicode.com/posts/4";
                startBackGroundThread();
            }
        });

    }

    private void startBackGroundThread() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * This method is used to build the JSONObject from the string and to extract all the keys and build the POJO class.
     * Please note that this method is called from runnable so this will be running inside a background thread.
     */
    private void buildResponseModel(StringBuffer stringBuffer) {
        try {
            /*
            Build a JSON object from the received string
             */
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            String title = jsonObject.getString("title");
            String body = jsonObject.getString("body");
            int userId = jsonObject.getInt("userId");
            int id = jsonObject.getInt("id");
            final Response response = new Response(userId, id, title, body);
            /*
            After the response class  is built, We need to update the UI so we have to communicate with the UI thread
            and display the data in the main thread.
             */
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    /*
                    This method will be called in the main thread
                     */
                    mTvTitle.setText(response.getTitle());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}