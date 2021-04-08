package com.example.demo.payload;

import java.util.Date;

public class SortRequestTrainingSessions {

    private Date startDate;
    private String deliveryMethod;
    private String ifsVersion;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
