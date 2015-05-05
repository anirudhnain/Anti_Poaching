package com.example.anirudh_pc.anti_poaching;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.widget.EditText;
import android.widget.Toast;

public class ap_net extends AsyncTask
{
    public static final int NETWORK_STATE_REGISTER=1;

    LoginActivity LA= new LoginActivity();
    @Override
    protected Object doInBackground(Object[] params) {

        System.out.println("Do in BackGround");
        getJson((String) params[0],(Integer) params[1]);
        return null;
    }

    private void getJson(String url,int state)
    {
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        boolean valid=false;

        switch(state)
        {
            case ap_net.NETWORK_STATE_REGISTER:
               // postParameters.add(new BasicNameValuePair("username",LA.get_usrname()));
                //postParameters.add(new BasicNameValuePair("password",LA.get_pss()));
                valid=true;
                break;
            default:
                System.out.println("///////////Warning unknown state! //////////");
        }

        if(valid)
        {
            System.out.println("Valid!");
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");/*
            try{
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
//                request.setEntity(entity);
                HttpResponse response = httpClient.execute(request);

                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line = "";
                String lineSeparator = System.getProperty("line.separator");
                while((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line+lineSeparator);
                }
                bufferedReader.close();
            }catch (Exception e){
                System.out.println("Error while networking!!" + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("result:"+stringBuffer);*/
            postParameters.add(new BasicNameValuePair("username", LA.get_usrname()));
            postParameters.add(new BasicNameValuePair("password", LA.get_pss()));

            String response = null;
            try {
//                System.out.println("Doing client post");
  //              response = CustomHttpClient.executeHttpPost(url, postParameters);
                String res=response.toString();
                res= res.replaceAll("\\s+","");
                if(res.equals("1"))
                    System.out.println("Correct Username or Password");
                else
                    System.out.println("Sorry!! Incorrect Username or Password");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else
        {
            System.out.println("Valid was not true! Will do nothing");
        }
    }
}
