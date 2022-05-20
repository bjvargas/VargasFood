package bj.vargas.vargasfood;

import bj.vargas.vargasfood.controller.KitchenController;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class MainUsingRepository {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(VargasFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        System.out.println("Instance of kitchen component: ");
        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        /**
         * Creating
         */
        System.out.println("Creating kitchens: ");

        Kitchen japonesa = new Kitchen();
        japonesa.setName("Japonesa");

        Kitchen brasileira = new Kitchen();
        brasileira.setName("Brasileira");

        kitchenRepository.save(japonesa);
        kitchenRepository.save(brasileira);


        /**
         * Printing list
         */
        System.out.println("Listing kitchens: ");
        List<Kitchen> list = kitchenRepository.list();
        list.stream().map(kitchen -> kitchen.getName()).forEach(System.out::println);

        /**
         * get One item by id
         */
        System.out.println("Show first kitchen: ");

        System.out.println(kitchenRepository.getKitchen(1L).getName());

        /**
         * Updating a kitchen
         */
        System.out.println("Show kitchen updated: ");
        Kitchen updateTailandesa = new Kitchen();
        updateTailandesa.setId(1L);
        updateTailandesa.setName("Tailandesa Updated");

        kitchenRepository.save(updateTailandesa);

        System.out.println(kitchenRepository.getKitchen(1L).getName());

        /**
         * Removing a kitchen
         */
        System.out.println("Removing a kitchen:");
        Kitchen deletedKitchen = new Kitchen();
        deletedKitchen.setId(1L);
        kitchenRepository.remove(deletedKitchen);

        List<Kitchen> list2 = kitchenRepository.list();
        list2.stream().map(kitchen -> kitchen.getName()).forEach(System.out::println);

    }
}
