package com.example.projectandroid.DB;

public class Phone_MessageDTO {
    String phone ;
    String message;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Phone_MessageDTO(String phone, String message) {
        this.phone = phone;
        this.message = message;
    }
}
