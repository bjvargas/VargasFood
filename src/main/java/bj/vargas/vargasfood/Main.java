package bj.vargas.vargasfood;

import bj.vargas.vargasfood.controller.KitchenController;
import bj.vargas.vargasfood.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(VargasFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenController kitchenController = applicationContext.getBean(KitchenController.class);

        Kitchen japonesa = new Kitchen();
        japonesa.setName("Japonesa");

        Kitchen brasileira = new Kitchen();
        japonesa.setName("Brasileira");

        kitchenController.create(japonesa);
        kitchenController.create(brasileira);

        List<Kitchen> list = kitchenController.list();
        list.stream().map(kitchen -> kitchen.getName()).forEach(System.out::println);
    }
}
