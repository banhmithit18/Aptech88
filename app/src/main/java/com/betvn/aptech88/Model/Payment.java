package com.betvn.aptech88.Model;

import java.util.Date;

public class Payment {
    public String paymentType;
    public String amount;
    public String paymentDate;
    public String status;

    public Payment(String paymentType, String amount, String paymentDate, String status) {
        this.paymentType = paymentType;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getStatus() {
        return status;
    }
}
