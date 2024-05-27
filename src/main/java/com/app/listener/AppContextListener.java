package com.app.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered JDBC driver: " + driver);
            } catch (SQLException e) {
                System.out.println("Error unregistering JDBC driver: " + driver + ": " + e.getMessage());
            }
        }

        // Cleanup MySQL abandoned connection cleanup thread
        try {
            Class<?> cls = Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread");
            cls.getMethod("checkedShutdown").invoke(null);
            System.out.println("Successfully invoked MySQL AbandonedConnectionCleanupThread shutdown");
        } catch (Exception e) {
            System.out.println("Error invoking MySQL AbandonedConnectionCleanupThread shutdown: " + e.getMessage());
        }
    }
}
