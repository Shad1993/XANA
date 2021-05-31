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
 * Cette classe est utilis�e pour connecter l'application � la base de donn�es MySQL en utilisant L'API JDBC Driver 
 * @author Lionel
 *
 */
public class ConnectionFactory {
	private static String driverClassName;
	private static String connectionUrl;
	private static String dbUser;
	private static String dbPassword;
	
	/**
	 * M�thode qui r�cup�re les param�tres : tels que mot de passe,login,driver etc pour la connexion � la base de donn�es
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
	 * Cette m�thode retourne le r�sultat de la connexion( r�ussie ou non)
	 * @return retourne le r�sultat de la connexion( r�ussie ou non)
	 * @throws SQLException g�re les erreurs sql
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
		return conn;
	}
}
