package nirmal.developer.markme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cdflynn.android.library.checkview.CheckView;

public class Main2Activity extends AppCompatActivity {
    String n, r;
    private IntentIntegrator qrScan;
    MediaPlayer mp;

    SharedPreferences sharedPreferences;
CheckView m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        m=findViewById(R.id.check);
        sharedPreferences = getSharedPreferences("My", Context.MODE_PRIVATE);
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();
        {
            Toast.makeText(Main2Activity.this, sharedPreferences.getString("nameKey", " "), Toast.LENGTH_SHORT).show();
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result not found", Toast.LENGTH_LONG).show();
            }
            if (result.getContents().equals("inet")) {
                String currentTime = new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());

               // Toast.makeText(Main2Activity.this, currentTime, Toast.LENGTH_SHORT).show();
                if (Integer.parseInt(currentTime) < 45) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://appealable-merchant.000webhostapp.com/MarkMe.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    m.check();
mp=MediaPlayer.create(Main2Activity.this,R.raw.apple);
mp.start();


                                    Toast.makeText(Main2Activity.this, response, Toast.LENGTH_LONG).show();


                                }
                            },
                            new Response.ErrorListener() {

                                public void onErrorResponse(VolleyError error) {
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("name",sharedPreferences.getString("nameKey", "ji"));
                            params.put("regno", sharedPreferences.getString("regKey", null));
                            return params;
                        }

                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(Main2Activity.this);
                    requestQueue.add(stringRequest);
                }
                else
                    {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://appealable-merchant.000webhostapp.com/MarkMeMsg.php",
                            new com.android.volley.Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //String time = "45";
                                    Toast.makeText(Main2Activity.this,response,Toast.LENGTH_LONG).show();
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject json_obj = jsonArray.getJSONObject(i);
                                           // time = json_obj.getString("Time");


                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    //Toast.makeText(Main2Activity.this, time, Toast.LENGTH_LONG).show();


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            params.put("name", sharedPreferences.getString("nameKey", "ji"));
                            params.put("regno", sharedPreferences.getString("regKey", null));


                            return params;
                        }
                    };


                    RequestQueue requestQueue = Volley.newRequestQueue(Main2Activity.this);
                    requestQueue.add(stringRequest);


                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }


    }

}





