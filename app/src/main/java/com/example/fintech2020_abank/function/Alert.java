package com.example.fintech2020_abank.function;

import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fintech2020_abank.MainActivity;

public class Alert extends AppCompatActivity {

    static AlertDialog.Builder builder;
    public static void alert_function(Context context, String mode) {
        builder = new AlertDialog.Builder(context);

        if(mode.equals("login")){
            builder.setTitle("알림").setMessage("ID 또는 비밀번호를 확인해주세요");
            setneutralButton("normal",context);
        }
        else if(mode.equals("exist")){
            builder.setTitle("알림").setMessage("HIDO 앱이 없습니다");
            setneutralButton("normal",context);
        }
        else if(mode.equals("logout")){
            builder.setTitle("알림").setMessage("서버의 응답이 없습니다");
            setneutralButton("finish",context);
        }
        else if(mode.equals("loading")){
            builder.setTitle("알림").setMessage("서버의 응답이 없습니다");
            setneutralButton("exit",context);
        }
        else if(mode.equals("register")){
            builder.setTitle("알림").setMessage("지문이 등록되었습니다");
            setneutralButton("main",context);
        }
        else if(mode.equals("transfer")){
            builder.setTitle("알림").setMessage("송금이 완료되었습니다");
            setneutralButton("normal",context);
            // 송금 완료 페이지던 상대 확인 페이지던 이동해야할 것 같음
        }
        else if(mode.equals("fail")){
            builder.setTitle("알림").setMessage("요청이 실패되었습니다");
            setneutralButton("normal",context);
        }
        else if(mode.equals("main")){
            builder.setTitle("알림").setMessage("요청이 실패되었습니다");
            setneutralButton("main",context);
        }
        else if(mode.equals("valid")){
            builder.setTitle("알림").setMessage("입금자 정보를 다시 확인해주세요");
            setneutralButton("return",context);
        }
    }

    private static void setneutralButton(String mode, Context para_context)
    {
        final Context context = para_context;
        if(mode.equals("exit")){
            builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.runFinalization();
                    System.exit(0);
                }
            });
        }
        else if(mode.equals("normal")){
            builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        else if(mode.equals("logout")){
            builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity mainActivity = (MainActivity)context;
                    mainActivity.onFinish();
                }
            });
        }
        else if(mode.equals("main")){
            builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
        else if(mode.equals("return")){
            builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Check_depositor check_depositor = (Check_depositor)context;
                    check_depositor.onResult(false);
                }
            });
        }
        alert_show();
    }

    private static void alert_show(){
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}