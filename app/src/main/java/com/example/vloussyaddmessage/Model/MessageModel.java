package com.example.vloussyaddmessage.Model;

import java.io.Serializable;
import java.util.Date;

public class MessageModel implements Serializable {

    public String messageDate;
    public String messagetime;
    public String destination;
    public String source;
    public String amount1;
    public String amount2;
    public String price;
    public String senderName;
    public String receiverName;
    public String senderMobileNumber;
    public String receiverMobileNumber;
    public String notes;

    public boolean messageisDrawn = false;
//    String undrawnMessage;
    public String drawnDate;



}
