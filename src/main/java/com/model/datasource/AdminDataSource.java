package com.model.datasource;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class AdminDataSource {
	
	private static AdminDataSource instance;
    private DataSource dataSource;

    private AdminDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost/windows");
        ds.setUsername("admin");
        ds.setPassword("admin1");
        ds.setInitialSize(5);
        ds.setMaxTotal(15);
        dataSource = ds;
    }

    public static AdminDataSource getInstance() {
        if (instance == null) {
            instance = new AdminDataSource();
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
