package com.example.fintech2020_abank.model;

public class User {
    private static String session_key;
    private static int running_code;
    private static String my_imei;
    private static User user = new User();

    // 싱글톤 패턴으로 구현
    private User()
    {
        running_code = 1;
    }

    public static User getInstance(){
        return user;
    }

    public void set_info(String session_key, String imei)
    {
        this.session_key = session_key;
        this.my_imei = imei;
        System.out.println(session_key+" "+imei);
    }

    public String get_session_key()
    {
        return session_key;
    }

    public int get_running_code()
    {
        return running_code;
    }

    public String get_imei()
    {
        return my_imei;
    }

}
