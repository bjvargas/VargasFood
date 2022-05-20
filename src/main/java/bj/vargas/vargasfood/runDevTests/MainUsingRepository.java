package bj.vargas.vargasfood.runDevTests;

import bj.vargas.vargasfood.VargasFoodApplication;
import bj.vargas.vargasfood.domain.model.Kitchen;
import bj.vargas.vargasfood.domain.model.Restaurant;
import bj.vargas.vargasfood.domain.repository.KitchenRepository;
import bj.vargas.vargasfood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class MainUsingRepository {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(VargasFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        testKitchen(applicationContext);
        testRestaurant(applicationContext);



    }

    public static void testKitchen(ApplicationContext applicationContext){

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

    public static void testRestaurant(ApplicationContext applicationContext){

        System.out.println("Instance of restaurant component: ");
        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        /**'
         * Creating
         */
        System.out.println("Creating restaurants: ");

        Restaurant sammFood = new Restaurant();
        sammFood.setName("Sam Food");
        sammFood.setShippingFee(BigDecimal.TEN);

        Restaurant barVargas = new Restaurant();
        barVargas.setName("Bar Vargas");
        barVargas.setShippingFee(BigDecimal.TEN);

        restaurantRepository.save(sammFood);
        restaurantRepository.save(barVargas);


        /**
         * Printing list
         */
        System.out.println("Listing restaurants: ");
        List<Restaurant> list = restaurantRepository.list();
        list.stream().map(restaurant -> restaurant.getName()).forEach(System.out::println);

        /**
         * get One item by id
         */
        System.out.println("Show first restaurant: ");

        System.out.println(restaurantRepository.getRestaurant(1L).getName());

        /**
         * Updating a restaurant
         */
        System.out.println("Show restaurant updated: ");
        Restaurant updateThaiGourm = new Restaurant();
        updateThaiGourm.setId(1L);
        updateThaiGourm.setName("Thai not Gourmet any more");
        updateThaiGourm.setShippingFee(BigDecimal.ONE);

        restaurantRepository.save(updateThaiGourm);

        System.out.println(restaurantRepository.getRestaurant(1L).getName());

        /**
         * Removing a restaurant
         */
        System.out.println("Removing a restaurant:");
        Restaurant deletedRestaurant = new Restaurant();
        deletedRestaurant.setId(1L);
        restaurantRepository.remove(deletedRestaurant);

        List<Restaurant> list2 = restaurantRepository.list();
        list2.stream().map(restaurant -> restaurant.getName()).forEach(System.out::println);

    }

}
