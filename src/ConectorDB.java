import java.sql.*;

public class ConectorDB {

    private Connection conexion;
    private Statement declaracion;

    public ConectorDB() throws SQLException {
        this.conexion = DriverManager.getConnection(Coneccion.URL.toString(),Coneccion.USUARIO.toString(), Coneccion.CONTRASENA.toString());
        this.declaracion = conexion.createStatement();
    }

    public ResultSet obtenerConsulta(String consulta) throws SQLException {
        ResultSet resultado = declaracion.executeQuery(consulta);

        return resultado;
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String query ="INSERT INTO usuario (id, username, password) VALUES ('" + usuario.getId() + "', " + usuario.getNombre() + ", '" + usuario.getContrasenia() + "')";
        this.declaracion.executeUpdate(query);
    }

    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
}
