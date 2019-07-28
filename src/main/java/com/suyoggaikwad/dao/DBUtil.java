package com.suyoggaikwad.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBUtil {

    private static DataSource dataSource;

    private static final String JNDI_LOOKUP_SERVICE = "java:comp/env/jdbc/myResource";

    static {
        try {
           dataSource = (DataSource) new InitialContext().lookup(JNDI_LOOKUP_SERVICE);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        System.out.println("Datasource Created");
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
