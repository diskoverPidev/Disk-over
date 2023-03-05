/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author abidi
 */

public class TwilioSMS {

    public static final String ACCOUNT_SID = "ACf08d1bcd88962f492de0135480183899";
    public static final String AUTH_TOKEN = "a04b150c8d285f94957c1c7119a83605";
    public static final String TWILIO_PHONE_NUMBER = "+12706790747";

    public static void envoyerSMS(String message, String numero) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN ,TWILIO_PHONE_NUMBER);
        Message msg = Message.creator(new PhoneNumber(numero), new PhoneNumber("+12706790747"), message).create();
        System.out.println(msg.getSid());
    }
}
