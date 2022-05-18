package bj.vargas.vargasfood.config;

import bj.vargas.vargasfood.notify.NotifyMail;
import bj.vargas.vargasfood.service.CustomerActivateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotifyConfig {

    @Bean
    public NotifyMail notifyMail() {
        NotifyMail notifyMail = new NotifyMail("smtp.vargas.com");
        notifyMail.setCapsLock(true);

        return notifyMail;
    }



}
