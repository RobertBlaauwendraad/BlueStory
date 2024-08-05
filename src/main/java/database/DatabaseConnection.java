package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import server.ServerProperties;

import static java.lang.System.getenv;

/**
 * @author Manu -
 */
public class DatabaseConnection {

	private static final DatabaseConnection dbc = new DatabaseConnection();

	public static DatabaseConnection getInstance() {
		return dbc;
	}

	public static Connection getConnection() {
		return getInstance().getInternalConnection();
	}

	private Connection getInternalConnection() {
		int denies = 0;
		while (true) { // There is no way it can pass with a null out of here?
			try {
				String url = ServerProperties.getProperty("database.url", "jdbc:mysql://" + getenv("DB_HOST") + ":" + getenv("DB_PORT") + "/" + getenv("DB_DATABASE") + "?autoReconnect=true");
				String user = ServerProperties.getProperty("database.user", getenv("DB_USER"));
				String password = ServerProperties.getProperty("database.password", getenv("DB_PASSWORD"));

				return DriverManager.getConnection(url, user, password);
			} catch (SQLException sqle) {
				denies++;

				if (denies == 3) {
					System.err.println(sqle);
					break;
				}
			}
		}
		return null;
	}

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // touch the mysql driver
		} catch (ClassNotFoundException e) {
			System.out.println("[SEVERE] SQL Driver Not Found. Consider death by clams.");
			System.err.println(e);
		}

	}
}
