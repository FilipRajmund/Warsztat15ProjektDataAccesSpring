package pl.zajavka.infrastucture.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import pl.zajavka.domain.Opinion;
import pl.zajavka.infrastucture.configuration.DatabaseConfiguration;

import java.util.Map;

@Slf4j
@Repository
@AllArgsConstructor
public class OpinionRepository implements pl.zajavka.business.OpinionRepository {

    public static final String DELETE_ALL = "DELETE FROM OPINION WHERE 1=1";
    private final SimpleDriverDataSource simpleDriverDataSource;
    private final DatabaseMapper databaseMapper;

    @Override
    public Opinion create(Opinion opinion) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        jdbcInsert.withTableName(DatabaseConfiguration.OPINION_TABLE);
        jdbcInsert.usingGeneratedKeyColumns(DatabaseConfiguration.PURCHASE_TABLE_PKEY.toLowerCase());

        Map<String, ?> params = databaseMapper.mapOpinion(opinion);
        Number opinionId = jdbcInsert.executeAndReturnKey(params);
        return opinion.withId((long) opinionId.intValue());
    }

    @Override
    public void removeAll() {
        new JdbcTemplate(simpleDriverDataSource).update(DELETE_ALL);
    }
}
