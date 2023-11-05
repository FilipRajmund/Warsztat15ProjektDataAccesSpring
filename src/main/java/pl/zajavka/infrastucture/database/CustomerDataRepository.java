package pl.zajavka.infrastucture.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import pl.zajavka.business.CustomerRepository;
import pl.zajavka.domain.Customer;
import pl.zajavka.infrastucture.configuration.DatabaseConfiguration;

@Slf4j
@Repository
@AllArgsConstructor
public class CustomerDataRepository implements CustomerRepository {
    public static final String DELETE_ALL = "DELETE FROM CUSTOMER WHERE 1=1";
    private final SimpleDriverDataSource simpleDriverDataSource;

    @Override
    public Customer create(Customer customer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        jdbcInsert.withTableName(DatabaseConfiguration.CUSTOMER_TABLE);
        jdbcInsert.usingGeneratedKeyColumns(DatabaseConfiguration.PURCHASE_TABLE_PKEY.toLowerCase());

        Number customerId = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(customer));
        return customer.withId((long) customerId.intValue());
    }

    @Override
    public void removeAll() {
        new JdbcTemplate(simpleDriverDataSource).update(DELETE_ALL);

    }
}
