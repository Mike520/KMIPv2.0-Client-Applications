package com.example.kmip_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void checkLogin(View view) throws ExecutionException, InterruptedException {
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        Button login = (Button)findViewById(R.id.login);
        String message = username.getText()+": "+password.getText();

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

        User[] users = new HttpRequest().execute().get();

        for(User u : users){
            Log.i("USERNAME", u.getUserName());
            Log.i("PASSWORD", u.getPassword());
            if(password.getText().toString().equals(u.getPassword().toString()) && username.getText().toString().equals(u.getUserName().toString()))
            {
                //Intent intent = new Intent(MainActivity.this,CreateKeyActivity.class);
                //startActivity(intent);
                String message1 = "Valid user";
                Toast.makeText(getApplicationContext(),message1,Toast.LENGTH_SHORT).show();

            }
        }

    }

    private class HttpRequest extends AsyncTask<Void,Void,User[]>{

        @Override
        protected User[] doInBackground(Void... voids) {
            try {
                String apiUrl = "http://10.0.2.2:8080/users";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                User[] users = restTemplate.getForObject(apiUrl, User[].class);
                return users;
            }
            catch (Exception ex){
                Log.e("", ex.getMessage());
            }
            return null;

        }

        @Override
        protected void onPostExecute(User[] users) {
            super.onPostExecute(users);

            for(User u: users){
                Log.i("username", String.valueOf(u.getUserName()));
                Log.i("password", String.valueOf(u.getPassword()));
            }
        }
    }
}
