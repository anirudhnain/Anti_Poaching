package com.example.anirudh_pc.anti_poaching;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class LoginActivity extends ActionBarActivity {

    private EditText username,password;
    private Button login_button;
    private String usr_name,usr_pss;
    private String resp;
    private String errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        final ActionBar bar = getSupportActionBar();
        bar.hide();

        setContentView(R.layout.activity_login);

        username    = (EditText) findViewById(R.id.username);
        password    = (EditText) findViewById(R.id.password);
        login_button= (Button) findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("LoginButtonClicked");

                usr_name =   username.getText()+"";
                usr_pss =   password.getText()+"";

                System.out.println("Username : "+usr_name+" Password: "+ usr_pss);

                //Cheking if something entered
                if(usr_name.length()==0 || usr_pss.length()==0){
                    //Checking for username field
                    if(usr_name.length()==0) {
                        Toast.makeText(getApplicationContext(), "Username field is empty !!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //Checking for password field
                    if(usr_pss.length()==0) {
                        Toast.makeText(getApplicationContext(), "Password field is empty !!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else{
                    //Do networking
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                            postParameters.add(new BasicNameValuePair("username",usr_name));
                            postParameters.add(new BasicNameValuePair("password",usr_pss));

                            String response = null;
                            try {
                                response = SimpleHttpClient.executeHttpPost("http://192.168.137.1:8080/MyServletProject/LoginVerification", postParameters);
                                String res = response.toString();
                                if(res.equals("true")){
                                    Intent home_redirect= new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(home_redirect);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Wrong Credentials !!!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                errorMsg = e.getMessage();
                            }
                        }

                    }).start();
                    try {
                        /** wait a second to get response from server */
                        Thread.sleep(1000);
                        /** Inside the new thread we cannot update the main thread
                         So updating the main thread outside the new thread */

                        System.out.println(resp);

                        if (null != errorMsg && !errorMsg.isEmpty()) {
                            System.out.println(errorMsg);
                        }
                    } catch (Exception e) {
                       e.getMessage();
                    }
                }}

        });
    }

    public String get_usrname(){
        return usr_name;
    }
    protected String get_pss(){
        return usr_pss;
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
    }*/
}
