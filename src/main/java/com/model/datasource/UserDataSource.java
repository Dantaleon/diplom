package com.model.datasource;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class UserDataSource {
	
	private static UserDataSource instance;
    private DataSource dataSource;

    private UserDataSource() {
    	BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost/windows");
        ds.setUsername("user");
        ds.setPassword("admin1");
        ds.setInitialSize(5);
        ds.setMaxTotal(15);
        dataSource = ds;
    }

    public static UserDataSource getInstance() {
        if (instance == null) {
            instance = new UserDataSource();
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
