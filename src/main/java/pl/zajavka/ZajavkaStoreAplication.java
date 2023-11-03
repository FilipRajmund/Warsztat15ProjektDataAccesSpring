package pl.zajavka;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.zajavka.business.RandomDataService;
import pl.zajavka.infrastucture.configuration.ApplicationConfiguration;

public class ZajavkaStoreAplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        RandomDataService randomDataService = applicationContext.getBean(RandomDataService.class);
        randomDataService.create();
    }
}
