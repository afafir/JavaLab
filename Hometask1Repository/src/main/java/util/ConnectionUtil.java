package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final String PROPERTIES = "db.properties";

    public static Connection getConnection() {
        FileInputStream propFile = null;
        try {
            ClassLoader classLoader = ConnectionUtil.class.getClassLoader();
            propFile = new FileInputStream(classLoader.getResource(PROPERTIES).getFile());
            Properties prop = new Properties();
            prop.load(propFile);
            String dbUrl = prop.getProperty("url");
            String dbUser = prop.getProperty("user");
            String dbPassword = prop.getProperty("password");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}