package bj.vargas.vargasfood.controller;

import bj.vargas.vargasfood.domain.model.Customer;
import bj.vargas.vargasfood.service.CustomerActivateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SingleController {

	private final CustomerActivateService activateService;

	public SingleController(final CustomerActivateService activateService) {
		this.activateService = activateService;
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {

		final Customer bruno = new Customer("Bruno", "bruno_jaques@hotmail.com", "999999999");

		activateService.active(bruno);

		return "Hello!";
	}
	
}
