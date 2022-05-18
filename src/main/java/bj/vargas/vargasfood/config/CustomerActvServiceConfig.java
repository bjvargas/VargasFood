package bj.vargas.vargasfood.config;

import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.service.CustomerActivateService;

public class CustomerActvServiceConfig {

    public CustomerActivateService activateService(Notify notify) {
        return new CustomerActivateService(notify);
    }
}
