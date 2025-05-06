package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
public class BD {

    
    public static void main(String[] args) {
        
        boolean done = false;
        
        try {
            String url = "jdbc:mysql://localhost:3307/pruebas";
            String user = "root";
            String password = "";
            
            Connection miConexion = DriverManager.getConnection(url, user, password);
            
            Statement miStatement = miConexion.createStatement();
            
            ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM productos");
            
            while (miResultSet.next()) {                
                System.out.println(miResultSet.getInt("id") + "\t" + 
                        miResultSet.getString("Nombre") + "\t" + 
                        miResultSet.getString("Descripcion") + "\t" + 
                        miResultSet.getFloat("Precio"));
            }
            
            try {
                miStatement.executeUpdate("INSERT INTO Productos "
                    + "(id,Nombre,Descripcion,Precio) "
                    + "VALUES (4,'Tornillos','Tornillos Finos',6)");
                
            } catch (Exception e) {
                System.out.println("El objeto ya existe");
            }
            //Actualiza el dato
            miStatement.executeUpdate("UPDATE Productos "
                        + "SET Precio = Precio * 10 WHERE Nombre = 'Tornillos'");
            //Borra el dato
            int filasAfectadas = miStatement.executeUpdate("DELETE FROM Productos WHERE Nombre = 'Tornillos'");
            
            System.out.println("Nº de registros eliminados: " + filasAfectadas);
            
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
