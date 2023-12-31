package pl.zajavka.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.zajavka.business.*;
import pl.zajavka.domain.*;
import pl.zajavka.infrastucture.configuration.ApplicationConfiguration;

@SpringJUnitConfig(classes = ApplicationConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceTest {

    private ReloadDataService reloadDataService;
    private CustomerService customerService;
    private PurchaseService purchaseService;
    private OpinionService opinionService;
    private ProducerService producerService;
    private ProductService productService;

//    @Autowired
//    public CustomerServiceTest(CustomerService customerService, PurchaseService purchaseService, OpinionService opinionService) {
//        this.customerService = customerService;
//        this.purchaseService = purchaseService;
//        this.opinionService = opinionService;
//    }






    @BeforeEach
    public void SetUp() {
        Assertions.assertNotNull(reloadDataService);
        Assertions.assertNotNull(customerService);
        Assertions.assertNotNull(purchaseService);
        Assertions.assertNotNull(opinionService);
        Assertions.assertNotNull(producerService);
        Assertions.assertNotNull(productService);
        reloadDataService.loadRandomData();
    }

    @Test
    @DisplayName("Polecenie 4 cz.1")
    void thatCustomerIsRemovedCorrectly() {
        //given
        final Customer customer = customerService.create(StoreFixtures.someCustomer());
        final Producer producer = producerService.create(StoreFixtures.someProducer());
        final Product product1 = productService.create(StoreFixtures.someProduct1(producer));
        final Product product2 = productService.create(StoreFixtures.someProduct2(producer));
        final Purchase purchase1 = purchaseService.create(StoreFixtures.somePurchase(customer, product1).withQuantity(1));
        final Purchase purchase2 = purchaseService.create(StoreFixtures.somePurchase(customer, product2).withQuantity(3));
        final Opinion opinion = opinionService.create(StoreFixtures.someOpinion(customer, product1));
        Assertions.assertEquals(customer, customerService.find(customer.getEmail()));
    }

    @Test
    @DisplayName("Polecenie 4 cz.2")
    void thatPurchaseIsNotRemovedWhenCustomerRemovingFails() {

    }
}
