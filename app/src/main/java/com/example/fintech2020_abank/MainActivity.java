package com.example.fintech2020_abank;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.fintech2020_abank.function.Register_fingerprint;
import com.example.fintech2020_abank.function.Transfer;
import com.example.fintech2020_abank.model.User;
import com.example.fintech2020_abank.network.SSL_Connection;
import com.example.fintech2020_abank.network.SendRequest;

import java.util.HashMap;

public class MainActivity extends Activity {

    private Button btn_register;
    private Button btn_transfer;
    private long backKeyPressedTime;
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
            System.out.println("CHECK : "+hashMap);
            sendRequest.send("https://"+ SSL_Connection.getSsl_connection().get_url()+"/logout",
                    1, hashMap, MainActivity.this);
        }
    }

    public void onFinish() {
        finish();
        toast.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toast =  Toast.makeText(MainActivity.this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_transfer= (Button)findViewById(R.id.btn_transfer);

        //송금하기
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Transfer.class);
                startActivity(intent);
            }
        });

        //지문등록하기
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register_fingerprint.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
