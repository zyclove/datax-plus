package com.datax.plus.core.simpleDbAccess;

import com.datax.plus.constant.DbTypes;
import com.datax.plus.model.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbAccessUtil {

    public static final int SHOW_DBS = 1;
    public static final int SHOW_TABLES = 2;
    public static final int DESC_TABLE = 3;

    private Connection connection;

    public DbAccessUtil(DataSource dataSource) throws SQLException {
        qryRun = new QueryRunner();
        if (dataSource.getDataSourceType().getDataSourceTypeId() == DbTypes.MYSQL) {
            this.connection = MysqlDbAccess.getConnection(dataSource.getDbHostUrl(), dataSource.getDbUsername(), dataSource.getDbPassword());
        }
    }

    private QueryRunner qryRun = null;

    public List<Map<String, Object>> simpleQuery(int queryType, String value) {
        StringBuffer sql0 = new StringBuffer();

        if (queryType == SHOW_DBS) {
            sql0.append(" SHOW DATABASES ");
        } else if (queryType == SHOW_TABLES) {
            sql0.append(" SHOW TABLES ");
        } else if (queryType == DESC_TABLE) {
            sql0.append(" DESC ").append(value);
        }

        List resultList = new ArrayList();

        try {
            resultList = (List<Map<String, Object>>) qryRun.query(this.connection, sql0.toString(), new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultList;
    }

}
