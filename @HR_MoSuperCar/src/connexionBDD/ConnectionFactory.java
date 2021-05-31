package connexionBDD;



import java.sql.DriverManager;


/*
 * Lionel Perrine
 * 
 * 
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Cette classe est utilisée pour connecter l'application à la base de données MySQL en utilisant L'API JDBC Driver 
 * @author Lionel
 *
 */
public class ConnectionFactory {
	private static String driverClassName;
	private static String connectionUrl;
	private static String dbUser;
	private static String dbPassword;
	
	/**
	 * Méthode qui récupère les paramètres : tels que mot de passe,login,driver etc pour la connexion à la base de données
	 */
	private final static Properties dbProperties = new Properties();
	static{
		try(InputStream input = new FileInputStream("dbconfig.properties")){
			dbProperties.load(input);
			
			driverClassName = dbProperties.getProperty("driver-class-name");
			connectionUrl = dbProperties.getProperty("connection-url");
			dbUser = dbProperties.getProperty("user");
			dbPassword = dbProperties.getProperty("password");
		}catch(IOException ioex){
			ioex.printStackTrace();
		}
	}

	/**
	 * Cette méthode retourne le résultat de la connexion( réussie ou non)
	 * @return retourne le résultat de la connexion( réussie ou non)
	 * @throws SQLException gère les erreurs sql
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
		return conn;
	}
}
