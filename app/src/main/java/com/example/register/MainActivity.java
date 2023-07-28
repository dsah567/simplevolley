package com.example.register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email = (EditText) findViewById(R.id.em);
        EditText password= (EditText) findViewById(R.id.pass);
        EditText userid= (EditText) findViewById(R.id.usid);
        EditText name= (EditText) findViewById(R.id.na);
        EditText mobile= (EditText) findViewById(R.id.mo);
        EditText age= (EditText) findViewById(R.id.age);
        EditText gender= (EditText) findViewById(R.id.gen);
        EditText height= (EditText) findViewById(R.id.he);
        EditText weight= (EditText) findViewById(R.id.we);
        EditText dietitaionId= (EditText) findViewById(R.id.did);
        EditText dietitaionUsedId= (EditText) findViewById(R.id.duid);
        AppCompatButton btn1=(AppCompatButton) findViewById(R.id.btn1);
        TextView tv1=(TextView) findViewById(R.id.tv1);

        String url="http://192.168.29.187/register_client.php";


        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Email,Password,Userid,Name,Mobile,Gender,duid;
                Integer Age,Weight,Height,did;

                Email=email.getText().toString();
                Password=password.getText().toString();
                Userid=userid.getText().toString();
                Name=name.getText().toString();
                Mobile=mobile.getText().toString();
                Age=Integer.parseInt(age.getText().toString());
                Gender=gender.getText().toString();
                Height=Integer.parseInt(height.getText().toString());
                Weight=Integer.parseInt(weight.getText().toString());
                did=Integer.parseInt(dietitaionId.getText().toString());
                duid=dietitaionUsedId.getText().toString();

                RequestQueue re = Volley.newRequestQueue(MainActivity.this);
                StringRequest sr=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                tv1.setText(response);
                                re.stop();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                re.stop();
                            }
                        })
                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("email", Email);
                        params.put("password",Password);
                        params.put("userID",Userid);
                        params.put("name", Name);
                        params.put("mobile",Mobile);
                        params.put("age", String.valueOf(Age));
                        params.put("gender",Gender);
                        params.put("height", String.valueOf(Height));
                        params.put("weight", String.valueOf(Weight));
                        params.put("dietitian_id", String.valueOf(did));
                        params.put("dietitianuserID",duid);
                        return params;
                    }
                };
                re.add(sr);
            }
        });
    }
}