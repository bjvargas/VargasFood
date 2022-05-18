package bj.vargas.vargasfood.listener;

import bj.vargas.vargasfood.config.NotifierType;
import bj.vargas.vargasfood.config.UrgencyLevel;
import bj.vargas.vargasfood.event.CustomerActivateEvent;
import bj.vargas.vargasfood.interfaces.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotifierListener {

    @NotifierType(UrgencyLevel.DEFAULT)
    @Autowired
    private Notify notify;

    @EventListener
    public void customerActivatedListener(CustomerActivateEvent event) {
        notify.notify(event.getCustomer(), "Your register is active!");
    }

}
