
import java.sql.*;
import java.util.List;

public class PruebaSQL {
    
    public static void main(String[] args) {
        PersonaJDBC pJDBC = new PersonaJDBC();
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
                }
            //INSERT
            Persona p2 = new Persona();
            p2.setUsuario("Johnny");
            p2.setPassword("asd123");
            pJDBC.insert(p2);
            
            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
        
        //SELECT
        List <Persona> personas;
        try {
            personas = pJDBC.select();
            for(Persona persona : personas){
            System.out.println("Persona: " + persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
