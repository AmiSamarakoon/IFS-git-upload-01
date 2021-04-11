package com.example.demo.payload;


import java.util.Date;

public class SortRequestTS {


    private Date selectedDate;
    private String deliveryMethod;
    private String ifsVersion;



    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getIfsVersion() {
        return ifsVersion;
    }

    public void setIfsVersion(String ifsVersion) {
        this.ifsVersion = ifsVersion;
    }
}
