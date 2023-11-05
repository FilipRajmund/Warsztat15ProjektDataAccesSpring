package pl.zajavka.infrastucture.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import pl.zajavka.domain.Opinion;
import pl.zajavka.infrastucture.configuration.DatabaseConfiguration;

@Slf4j
@Repository
@AllArgsConstructor
public class OpinionRepository implements pl.zajavka.business.OpinionRepository {
    private final SimpleDriverDataSource simpleDriverDataSource;
    @Override
    public Opinion create(Opinion opinion) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        jdbcInsert.withTableName(DatabaseConfiguration.OPINION_TABLE);
        jdbcInsert.usingGeneratedKeyColumns(DatabaseConfiguration.PURCHASE_TABLE_PKEY.toLowerCase());

        Number opinionId = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(opinion));
        return opinion.withId((long) opinionId.intValue());
    }
}
