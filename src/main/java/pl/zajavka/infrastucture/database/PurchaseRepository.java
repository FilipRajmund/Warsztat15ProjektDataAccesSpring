package pl.zajavka.infrastucture.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import pl.zajavka.domain.Purchase;
import pl.zajavka.infrastucture.configuration.DatabaseConfiguration;

@Slf4j
@Repository
@AllArgsConstructor
public class PurchaseRepository implements pl.zajavka.business.PurchaseRepository {

    private final SimpleDriverDataSource simpleDriverDataSource;
    @Override
    public Purchase create(Purchase purchase) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        jdbcInsert.withTableName(DatabaseConfiguration.PURCHASE_TABLE);
        jdbcInsert.usingGeneratedKeyColumns(DatabaseConfiguration.PURCHASE_TABLE_PKEY.toLowerCase());

        Number purchaseID = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(purchase));
        return purchase.withId((long) purchaseID.intValue());
    }
}
