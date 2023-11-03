package pl.zajavka.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.zajavka.domain.*;

@Slf4j
@Service
@AllArgsConstructor
public class RandomDataService {
//ctrl shift u - zmiana wielkości liter

    //klasa odpowiedzialan za przygotowanie danych
    private final RandomDataPreparationService randomDataPreparationService;
    //5 repositorów odpowiedzialne za to, aby z każdą tabelą coś robić w bazie danych
    private final CustomerRepository customerRepository;
    private final OpinionRepository opinionRepository;
    private final ProducerRepository producerRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public void create() {
        Customer customer = customerRepository.create(randomDataPreparationService.createCustomer());
        Opinion opinion = opinionRepository.create(randomDataPreparationService.createOpinion());
        Producer producer = producerRepository.create(randomDataPreparationService.createProducer());
        Product product = productRepository.create(randomDataPreparationService.createProduct());
        Purchase purchase = purchaseRepository.create(randomDataPreparationService.createPurchase());

        log.debug("Random customer created: [{}]",customer);
        log.debug("Random opinion created: [{}]",opinion);
        log.debug("Random producer created: [{}]",producer);
        log.debug("Random product created: [{}]",product);
        log.debug("Random purchase created: [{}]",purchase);

    }
}
