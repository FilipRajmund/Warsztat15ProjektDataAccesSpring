package pl.zajavka.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class StoreFixtures {


    public static Customer someCustomer() {
        return Customer.builder()
                //definiuje baza danych nie potrzebne te dane .id()
                .userName("filp")
                .email("filip@gmail.com")
                .name("filip")
                .surname("surname")
                .dateOfBirth(LocalDate.of(1991, 10, 2))
                .telephoneNumber("+488330105")
                .build();
    }

    public static Producer someProducer() {
        return Producer.builder()
                //definiuje baza danych nie potrzebne te dane .id()
                .producerName("Fifi")
                .address("someAddress")
                .build();
    }

    public static Product someProduct1(Producer producer) {
        return Product.builder()
                //definiuje baza danych nie potrzebne te dane .id()
                .productCode("123456")
                .productName("fiflak")
                .productPrice(BigDecimal.valueOf(162.2))
                .adultsOnly(false)
                .description("someDescription")
                .producer(producer)
                .build();
    }

    public static Product someProduct2(Producer producer) {
        return Product.builder()
                //definiuje baza danych nie potrzebne te dane .id()
                .productCode("586242")
                .productName("fafik")
                .productPrice(BigDecimal.valueOf(162.2))
                .adultsOnly(false)
                .description("someDescription")
                .producer(producer)
                .build();
    }

    public static Purchase somePurchase(Customer customer, Product product) {
        return Purchase.builder()
                //definiuje baza danych nie potrzebne te dane .id()
                .customer(customer)
                .product(product)
                .quantity(2)
                .dateTime(OffsetDateTime.of(2020, 1, 1, 10, 9, 10, 1, ZoneOffset.ofHours(4)))
                .build();
    }

    public static Opinion someOpinion(Customer customer, Product product) {
        return Opinion.builder()
                //definiuje baza danych nie potrzebne te dane .id()
                .customer(customer)
                .product(product)
                .stars((byte) 4)
                .comment("My comment")
                .dateTime(OffsetDateTime.of(2020, 1, 1, 12, 9, 10, 1, ZoneOffset.ofHours(4)))
                .build();
    }
}
