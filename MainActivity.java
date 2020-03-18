package com.example.ppe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    String server_url_insert="https://api.vchazallet/index.php";

    EditText pseudo = findViewById(R.id.pseudo);
    EditText password = findViewById(R.id.password);
    String valuePseudo = pseudo.getText().toString();
    String valuePassword = password.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.connect);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               /* TextView showP = findViewById(R.id.p1);
                showP.setText("Ui");
                if(valuePseudo.equals("test") && valuePassword.equals("123")){
                    Intent home = new Intent(MainActivity.this, Home.class);
                    startActivity(home);
                }else {
                    showP.setText("Erreur de connexion");
                }*/
                connect();

                // Code here executes on main thread after user presses button
            }
        });
    }

    private void connect(){
        String url=server_url_insert+ "?app_pseudo="+valuePseudo+"&app_password="+valuePassword;
        Log.e("URL",url);


        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView showP = findViewById(R.id.p1);
                showP.setText(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"err"+error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

}
