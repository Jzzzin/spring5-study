package ch4.config;

import ch4.Food;
import ch4.FoodProviderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class ProfileJavaConfigExample {

    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(
                KindergartenConfig.class,
                HighschoolConfig.class
        );

        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);

        List<Food> lunchSet = foodProviderService.provideLunchSet();
        for (Food food : lunchSet) {
            System.out.println("Food: " + food.getName());
        }
        ctx.close();
    }
}
