package bj.vargas.vargasfood.config;

import bj.vargas.vargasfood.notify.NotifyMail;

public class NotifyConfig {

    public NotifyMail notifyMail() {
        NotifyMail notifyMail = new NotifyMail("smtp.vargas.com");
        notifyMail.setCapsLock(true);

        return notifyMail;
    }



}
