package pl.zajavka.infrastucture.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import pl.zajavka.domain.Producer;
import pl.zajavka.infrastucture.configuration.DatabaseConfiguration;


@Slf4j
@Repository
@AllArgsConstructor
public class ProducerRepository implements pl.zajavka.business.ProducerRepository {
    public static final String DELETE_ALL = "DELETE FROM PRODUCER WHERE 1=1";
    private final SimpleDriverDataSource simpleDriverDataSource;

    @Override
    public Producer create(Producer producer) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        jdbcInsert.withTableName(DatabaseConfiguration.PRODUCER_TABLE);
        jdbcInsert.usingGeneratedKeyColumns(DatabaseConfiguration.PURCHASE_TABLE_PKEY.toLowerCase());

        Number producerId = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(producer));
        return producer.withId((long) producerId.intValue());
    }

    @Override
    public void removeAll() {
        new JdbcTemplate(simpleDriverDataSource).update(DELETE_ALL);
    }
}
