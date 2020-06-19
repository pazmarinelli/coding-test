
import java.sql.*;
import java.util.*;

public class PersonaJDBC {
    
    private Connection conexion;
    private static final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password) VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario=?, password=? WHERE id_usuario=?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario=?";
    
    public PersonaJDBC(){
        
    }
    
    public PersonaJDBC(Connection conexion){
        this.conexion = conexion;
    }
    
    public List<Persona> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList();

        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");

                persona = new Persona();
                persona.setId_usuario(id_usuario);
                persona.setUsuario(usuario);
                persona.setPassword(password);

                personas.add(persona);

            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if(conexion!=null){
                Conexion.close(conn);
            }
        }
        return personas;
    }

    public int insert(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, persona.getUsuario());
            stmt.setString(2, persona.getPassword());

            System.out.println("Ejecutando Query: " + SQL_INSERT);
            rows = stmt.executeUpdate();//Regresa un entero con el número de registros afectados
            System.out.println("Registros afectados: " + rows);

        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            System.out.println("Ejecutando query " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, persona.getUsuario());
            stmt.setString(2, persona.getPassword());
            stmt.setInt(3, persona.getId_usuario());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado: " + rows);

        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conexion != null ? this.conexion : Conexion.getConnection();
            System.out.println("Ejecutando Query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, persona.getId_usuario());
            rows = stmt.executeUpdate();
            System.out.println("Cantidad de registros modificados: " + rows);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}
