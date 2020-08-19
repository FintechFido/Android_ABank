package com.example.fintech2020_abank.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fintech2020_abank.R;
import com.example.fintech2020_abank.network.SSL_Connection;
import com.example.fintech2020_abank.network.SendRequest;

import java.util.HashMap;

public class Check_depositor extends Activity {

    private String depositor_bank;
    private String price;
    private String account;
    private String name;
    private ProgressBar progressBar;
    private Button btn;

    // 뒤로가기 하면 처리되도록 해야 한다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_depositor);

        btn = (Button)findViewById(R.id.btn_transfer_auth);

        depositor_bank = getIntent().getExtras().getString("depositor_bank").toString();
        price = getIntent().getExtras().getString("price").toString();
        account = getIntent().getExtras().getString("account").toString();
        name = getIntent().getExtras().getString("name").toString();
        progressBar = (ProgressBar)findViewById(R.id.check_depositor_progressBar);

        confirm();
        // 사용자 확인

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer_auth();
            }
        });
    }

    public void confirm()
    {
        // 서버에 확인 요청하기. 없으면 알람뜨고 뒤로가게 만들어야 함
        // send(String url, int method, final HashMap<String, String> hashMap, Context context)
        SendRequest sendRequest = new SendRequest();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", name);
        hashMap.put("bank_code", "1"); // 강제로 넣어주었음
        hashMap.put("account_code", account);
        System.out.println("CHECK : "+hashMap);
        sendRequest.send("https://"+ SSL_Connection.getSsl_connection().get_url()+"/user/valid",
                0, hashMap, Check_depositor.this);
    }

    public void onResult(boolean result)
    {
        progressBar.setVisibility(View.GONE);

        if(result) {
            TextView textView = (TextView)findViewById(R.id.tv_confirm);
            textView.setText(name+" 님에게 송금하겠습니다");
            btn.setClickable(true);
        }
        else {
            Intent intent = new Intent();
            setResult(3000,intent);
            finish();
        }
    }

    public void transfer_auth() {
        // 송금 및 인증
        // 챌린지 넘버 HIDO에서 받아서 진행해야 한다..
        /*
        Call_HIDO call_hido = new Call_HIDO();
            if(call_hido.exist_check(Transfer.this)) {
                Intent intent = new Intent();
                intent.setClassName("com.example.fintech_hido","com.example.fintech_hido.function.Fingerprint_function");
                intent.putExtra("mode", "auth");
                intent.putExtra("session_key", User.getInstance().get_session_key());
                intent.putExtra("imei",User.getInstance().get_imei());
                intent.putExtra("running",User.getInstance().get_running_code());
                startActivityForResult(intent,1000);
            }
            else{
                Alert.alert_function(Transfer.this, "exist");
            }
         */

        /*
        Intent intent = new Intent();
        setResult(2000,intent);
        finish();

         */
    }
}