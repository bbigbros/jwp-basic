package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stripes on 2017. 3. 2..
 */
public interface RowMapper {
    Object mapRow(ResultSet rs) throws SQLException;
}
