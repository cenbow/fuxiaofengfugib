package com.cqliving.codegenerator.ui.jdbc;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-1-30
 * Time: 上午11:24
 * To change this template use File | Settings | File Templates.
 */
public class JDBConnect {

    private Connection connection;
    private Statement statement;

    public JDBConnect(String url, String driverName,
                       String user, String passwd) {
        try {
            Class.forName(driverName);
            System.out.println("Opening db connection");

            connection = DriverManager.getConnection(url, user, passwd);
            statement = connection.createStatement();
        }
        catch (ClassNotFoundException ex) {
            System.err.println("Cannot find the database driver classes.");
            System.err.println(ex);
        }
        catch (SQLException ex) {
            System.err.println("Cannot connect to this database.");
            System.err.println(ex);
        }
    }

    public Vector executeQuery(String query) {
        if (connection == null || statement == null) {
            System.err.println("There is no database to execute the query.");
            return null;
        }
        try {
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            // Get all rows.
            Vector rows = new Vector();
            while (resultSet.next()) {
                Map<String, Object> newRow = new LinkedHashMap<String, Object>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    newRow.put(metaData.getColumnLabel(i).toLowerCase(), resultSet.getObject(i));
                }
                rows.addElement(newRow);
            }
            return rows;
            //  close(); Need to copy the metaData, bug in jdbc:odbc driver.
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println(ex);
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
