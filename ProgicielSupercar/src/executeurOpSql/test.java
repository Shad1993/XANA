package executeurOpSql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import employes.Voiture;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

import junit.framework.Assert;

class test {


    @Test
    public void verifierVoiture()
    {
        Voiture voiture=new Voiture();
        //cree la connexion avec la base de donneé
    	String myUrl = "jdbc:mysql://localhost/moloto";
        try(java.sql.Connection connection= DriverManager.getConnection(myUrl, "root", ""))
        {
            try(java.sql.Statement stCheck=connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                // Les valeurs a tester:
                String marque="Mercedes";
                String modele="AMG GT";
              
                // vefiffier si la page voiture contient les valeur declaré si dessus
                try(ResultSet rs=stCheck.executeQuery("SELECT * FROM voitures"))
                {
                    assertTrue(rs.next());
                    assertEquals(marque, rs.getString("Marque"));
                    assertEquals(modele, rs.getString("Modele"));
                }
            }
        }
        catch (SQLException e)
        {
        	// si les valeurs a tster ne sont pas presente alors on aura une erreur
            fail(e.toString());
        }
    }
}