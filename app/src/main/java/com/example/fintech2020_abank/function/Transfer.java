package com.example.fintech2020_abank.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.fintech2020_abank.R;
import com.example.fintech2020_abank.model.User;

public class Transfer extends Activity {

    private Spinner spinner;
    private EditText account;
    private EditText price;
    private EditText name;
    private Intent now_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);

        spinner = (Spinner)findViewById(R.id.spinner_bank);
        account = (EditText)findViewById(R.id.et_account);
        price = (EditText)findViewById(R.id.et_price);
        name = (EditText)findViewById(R.id.et_name);
        Button btn = (Button)findViewById(R.id.btn_check);
        now_intent = getIntent();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transfer.this, Check_depositor.class);
                intent.putExtra("depositor_bank", spinner.getSelectedItem().toString());
                intent.putExtra("account", ""+account.getText());
                intent.putExtra("price",""+price.getText());
                intent.putExtra("name", ""+name.getText());
                startActivityForResult(intent, 3000); // 3000번은 입금자 확인
            }
        });
    }


    private void transfer_function()
    {
        Intent intent = new Intent();
        intent.setClassName("com.example.fintech_hido","com.example.fintech_hido.function.Fingerprint_function");
        intent.putExtra("mode","auth");
        intent.putExtra("session_key", User.getInstance().get_session_key());
        startActivityForResult(intent,2000);
        // 키가 존재하는지 일단 확인, 키가 존재하면 그 다음에 인증을 해야 함
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 3000) {
            // 입금자 정보 확인 실패한 경우 리턴됨
            ;
        }
        else {
            ;
            // 2000. 송금 위해 인증까지 한 뒤 결과

        }
    }

}
