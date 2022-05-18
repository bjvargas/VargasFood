package bj.vargas.vargasfood.notify;

import bj.vargas.vargasfood.interfaces.Notify;
import bj.vargas.vargasfood.service.CustomerActivateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerActvServiceConfig {

    @Bean
    public CustomerActivateService activateService(Notify notify) {
        return new CustomerActivateService(notify);
    }
}
