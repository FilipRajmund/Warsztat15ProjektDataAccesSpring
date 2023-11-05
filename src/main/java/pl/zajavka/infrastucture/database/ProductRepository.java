package pl.zajavka.infrastucture.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import pl.zajavka.domain.Product;
import pl.zajavka.infrastucture.configuration.DatabaseConfiguration;

import java.util.Map;


@Slf4j
@Repository
@AllArgsConstructor
public class ProductRepository implements pl.zajavka.business.ProductRepository {
    public static final String DELETE_ALL = "DELETE FROM PRODUCT WHERE 1=1";
    private final SimpleDriverDataSource simpleDriverDataSource;
    private final DatabaseMapper databaseMapper;
    @Override
    public Product create(Product product) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        jdbcInsert.withTableName(DatabaseConfiguration.PRODUCT_TABLE);
        jdbcInsert.usingGeneratedKeyColumns(DatabaseConfiguration.PURCHASE_TABLE_PKEY.toLowerCase());

        Map<String, ?> params = databaseMapper.mapProduct(product);

        Number productId = jdbcInsert.executeAndReturnKey(params);
        return product.withId((long) productId.intValue());
    }

    @Override
    public void removeAll() {
        new JdbcTemplate(simpleDriverDataSource).update(DELETE_ALL);
    }
}
