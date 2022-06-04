package bj.vargas.vargasfood;

import bj.vargas.vargasfood.infraestructure.repository.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class VargasFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(VargasFoodApplication.class, args);
    }

}
