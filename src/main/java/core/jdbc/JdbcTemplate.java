package core.jdbc;

import org.springframework.test.context.jdbc.Sql;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stripes on 2017. 3. 2..
 */
public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter pss) throws DataAccessException {
        try (Connection conn = ConnectionManager.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement((sql))) {
            pss.setValues(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void update(String sql, Object... params) throws SQLException {
        update(sql, createPreparedStatementSetter(params));
    }

    public <T> T query(String sql, RowMapper<T> rm, PreparedStatementSetter pss) throws SQLException {
        List<T> list = list(sql, rm, pss);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public <T> T query(String sql, RowMapper<T> rm, Object... params) throws SQLException {
        return query(sql, rm, createPreparedStatementSetter(params));
    }

    public <T> List<T> list(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
        ResultSet rs = null;
        try (Connection conn=ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();

            List<T> list = new ArrayList<T>();
            while(rs.next()) {
                list.add(rm.mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
    }

    public <T> List<T> list(String sql, RowMapper<T> rm, Object... params) throws SQLException {
        return list(sql, rm, createPreparedStatementSetter(params));
    }

    private PreparedStatementSetter createPreparedStatementSetter(Object... params) {
        return new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    for (int i = 0; i < params.length; i++) {
                        pstmt.setObject(i + 1, params[i]);
                    }
                }
        };
    }
}
