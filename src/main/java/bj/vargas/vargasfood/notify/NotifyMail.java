package bj.vargas.vargasfood.notify;

import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.model.Customer;

public class NotifyMail implements Notify {

	private boolean capsLock;
	private final String serverHostMail;

	public NotifyMail(final String serverHostMail) {
		this.serverHostMail = serverHostMail;
	}

	@Override
	public void notify(final Customer customer, String message) {
		if(capsLock) {
			message = message.toUpperCase();
		}
		System.out.printf("Notifying %s by mail %s using SMTP %s: %s\n",
				customer.getName(), customer.getMail(), this.serverHostMail, message);
	}

	public void setCapsLock(final boolean capsLock) {
		this.capsLock = capsLock;
	}
}