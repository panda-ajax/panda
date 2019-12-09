package com.xiaochen.goodsmanager.util;
import com.xiaochen.goodsmanager.common.DbPropertiesloads;
import java.sql.*;
public class DBUtil {
    private static Connection connection = null;
    private static PreparedStatement ps = null;
    private static ResultSet result = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;

    /**
     * 建立连接的方法
     *
     * @return Connection 连接对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DbPropertiesloads.getprokey("driver"));
        url = DbPropertiesloads.getprokey("url");
        user = DbPropertiesloads.getprokey("user");
        password = DbPropertiesloads.getprokey("password");
        connection= DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * 获取预编译语句对象的方法
     *
     * @param sql 预编译的SQL命令
     * @return PreparedStatement对象
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        return getConnection().prepareStatement(sql);
    }

    /**
     * 执行增删改的方法
     *
     * @param ps 执行增删改语句的对象
     * @return 返回影响的行数
     * @throws SQLException
     */
    public int execUpdate(PreparedStatement ps) throws SQLException {
        return ps.executeUpdate();
    }

    /**
     * 执行查询的方法
     *
     * @param prepStat 执行查询语句的对象
     * @return 返回相应的结果集
     * @throws SQLException
     */
    public ResultSet execQuery(PreparedStatement prepStat) throws SQLException {
        return prepStat.executeQuery();
    }


    /**
     * 关闭结果集对象
     */
    public void closeSet() throws SQLException {
        if (result != null) {
            result.close();
        }
    }

    /**
     * 关闭预编译对象
     */
    public void closePrepStat() throws SQLException {
        if (ps != null) {
            ps.close();
        }
    }

    /**
     * 关闭连接对象
     */
    public void closeConn() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 关闭全部对象
     */
    public void closeAll() throws SQLException {
        closeSet();
        closePrepStat();
        closeConn();
    }


}
