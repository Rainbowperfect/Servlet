package org.perosn.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Statement;

import javax.resource.cci.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author RainbowPerferct/zero
 * @version v1.0
 * @create 2018/9/20/21:30
 */

public class C3P0Utils {
    private static ComboPooledDataSource dataSource;
    static{
        dataSource = new ComboPooledDataSource();
    }
    /**
     * 从连接池中获取连接
     * @return
     * @throws SQLException
     */
    public static java.sql.Connection getConnection()
            throws SQLException
    {
        return dataSource.getConnection();
    }
    /**
     * 向外提供连接池对象
     * @return
     */
    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }
    /**
     * 释放资源
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void Release(ResultSet resultSet, Statement statement, Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                //connection.close();//需要考虑他到底是新创建的还是原来在池子中的吗？
                connection.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
