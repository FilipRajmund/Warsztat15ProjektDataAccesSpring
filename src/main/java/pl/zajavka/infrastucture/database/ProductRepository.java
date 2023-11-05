package pl.zajavka.infrastucture.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import pl.zajavka.domain.Product;
import pl.zajavka.infrastucture.configuration.DatabaseConfiguration;


@Slf4j
@Repository
@AllArgsConstructor
public class ProductRepository implements pl.zajavka.business.ProductRepository {
    private final SimpleDriverDataSource simpleDriverDataSource;
    @Override
    public Product create(Product product) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        jdbcInsert.withTableName(DatabaseConfiguration.PRODUCT_TABLE);
        jdbcInsert.usingGeneratedKeyColumns(DatabaseConfiguration.PURCHASE_TABLE_PKEY.toLowerCase());

        Number productId = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(product));
        return product.withId((long) productId.intValue());
    }
}
