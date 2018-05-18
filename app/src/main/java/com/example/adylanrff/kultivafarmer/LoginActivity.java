package com.example.adylanrff.kultivafarmer;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class LoginActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView subtitleText;
    private EditText phoneField;
    private EditText passwordField;
    private Button loginButton;
    private TextView noAccountInfo;
    private TextView signUpButton;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        assignView();
        setFont();
        session = new Session(this);
        if (session.getAuth() != null) {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://168.235.103.57:5000/user/login_status";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("status")) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                                else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    super.getHeaders();
                    HashMap<String, String> header = new HashMap<>();
                    header.put("auth", session.getAuth());

                    return header;
                }
            };

            queue.add(request);

        }

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {

        if (!validate()) {
            return;
        }

        String email = phoneField.getText().toString();
        String password = passwordField.getText().toString();

        // TODO: implement loading
        verify(email, password);

    }

    private void verify(String email, String password) {
        // TODO: implement authentication
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://168.235.103.57:5000/user/login_petani";

        final JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("phone", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonRequest.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String requestBody = jsonRequest.toString();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(LoginActivity.class.getSimpleName(), response.toString());
                        try {
                            if (response.get("session_id") != null) {
                                session.setAuth(response.getString("session_id"));
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Log.i(LoginActivity.class.getSimpleName(), response.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener(){


            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(LoginActivity.class.getSimpleName(), error.toString());
            }
        }) {
            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog
                            .wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };

        queue.add(request);
    }

    private boolean validate() {
        boolean valid = true;

        String email = phoneField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.isEmpty() || !Patterns.PHONE.matcher(email).matches()) {
            Log.i(LoginActivity.class.getSimpleName(), "email incorrect");
            phoneField.setError("enter a valid email address");
            valid = false;
        } else {
            phoneField.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            Log.i(LoginActivity.class.getSimpleName(), "password incorrect");
            passwordField.setError("password must be longer than 8 characters");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }

    private void assignView() {

        titleText = findViewById(R.id.title_tv);
        subtitleText = findViewById(R.id.subtitle_tv);
        phoneField = findViewById(R.id.phone_field);
        passwordField = findViewById(R.id.password_field);
        loginButton = findViewById(R.id.button_login);
        noAccountInfo = findViewById(R.id.no_account_info);
        signUpButton = findViewById(R.id.signup_button);

    }

    private void setFont() {
        AssetManager am = this.getApplicationContext().getAssets();

        Typeface titleFont = Typeface
                .createFromAsset(am, String.format(Locale.US, "fonts/%s", "TitilliumWeb-Bold.ttf"));

        Typeface bodyFont = Typeface
                .createFromAsset(am, String.format(Locale.US, "fonts/%s", "TitilliumWeb-ExtraLight.ttf"));

        subtitleText.setTypeface(titleFont);
        titleText.setTypeface(titleFont);
        phoneField.setTypeface(bodyFont);
        passwordField.setTypeface(bodyFont);
        loginButton.setTypeface(bodyFont);
        noAccountInfo.setTypeface(bodyFont);
        signUpButton.setTypeface(titleFont);

    }
}

