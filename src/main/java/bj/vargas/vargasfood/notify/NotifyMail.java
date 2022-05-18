package bj.vargas.vargasfood.notify;

import bj.vargas.vargasfood.config.NotifierType;
import bj.vargas.vargasfood.config.UrgencyLevel;
import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@NotifierType(UrgencyLevel.HIGH)
@Component
public class NotifyMail implements Notify {


	@Override
	public void notify(final Customer customer, String message) {

		System.out.printf("Notifying %s by mail %s: %s\n",
				customer.getName(), customer.getMail(), message);
	}

}