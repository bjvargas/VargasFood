package bj.vargas.vargasfood.listener;

import bj.vargas.vargasfood.event.CustomerActivateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IssuanceInvoiceListener {


    @EventListener
    public void sendInvoice(CustomerActivateEvent event) {
        System.out.println("Sending invoice to customer: " + event.getCustomer().getName());

    }


}
