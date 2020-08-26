package com.example.fintech2020_abank.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.fintech2020_abank.R;
import com.example.fintech2020_abank.model.User;
import com.example.fintech2020_abank.network.SSL_Connection;
import com.example.fintech2020_abank.network.SendRequest;

import java.util.HashMap;

public class Register_fingerprint extends Activity {

    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
        }
        else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            // send(String url, int method, final HashMap<String, String> hashMap, Context context)
            SendRequest sendRequest = new SendRequest();
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("session_key", User.getInstance().get_session_key());
            sendRequest.send("https://"+ SSL_Connection.getSsl_connection().get_url()+"/logout",
                    1, hashMap, Register_fingerprint.this);
        }
    }

    public void onFinish() {
        finish();
        toast.cancel();
    }

    public Register_fingerprint()
    {;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast = Toast.makeText(Register_fingerprint.this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);;
        register_function();
    }

    public void register_function()
    {
        Call_HIDO call_hido = new Call_HIDO();
        if(call_hido.exist_check(Register_fingerprint.this)) {
            Intent intent = new Intent();
            intent.setClassName("com.example.fintech_hido","com.example.fintech_hido.function.Fingerprint_function");
            intent.putExtra("mode", "register");
            intent.putExtra("session_key", User.getInstance().get_session_key());
            intent.putExtra("imei",User.getInstance().get_imei());
            intent.putExtra("running",User.getInstance().get_running_code());
            startActivityForResult(intent,1000);
        }
        else{
            Alert.alert_function(Register_fingerprint.this, "exist");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.hasExtra("result")){
            if(data.getExtras().getString("result").toString().equals("true")) {
                Alert.alert_function(Register_fingerprint.this, "register");
            }
            else {
                Alert.alert_function(Register_fingerprint.this, "main");
            }
        }
        else {
            Alert.alert_function(Register_fingerprint.this, "main");
        }
    }

}
