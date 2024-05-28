import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuBD {
    private Scanner scanner = new Scanner(System.in);
    private static int autooId = 0;
    private ConectorDB conectorDB;

    public void menu(){
        int opcion = 0;
        do {
            try {
                this.conectorDB = new ConectorDB();

                int autoID = 0;
                System.out.printf("""
                        ¿Qué desea hacer?
                        1.Cargar Usuario
                        2.Obtener Usuario
                        3.Obtener Usuarios
                        """);
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.printf("Inserte el nombre del usuario");
                        String nombre = scanner.next();
                        System.out.printf("Ingrese la contraseña");
                        String contrasenia = scanner.next();
                        Usuario usuario = new Usuario(autoID, nombre, contrasenia);
                        autoID++;
                        this.conectorDB.insertarUsuario(usuario);
                        break;

                    case 2:
                        System.out.printf("Ingrese su nombre de usuario");
                        String username = scanner.next();
                        System.out.printf("Ingrese su contrasenia");
                        String password = scanner.next();

                        String queryUser = "SELECT * FROM usuarios WHERE nombre = '" + username + "' AND contraseña = '" + password + "'";
                        ResultSet log = this.conectorDB.obtenerConsulta(queryUser);
                        Usuario usuarioLog = new Usuario(log.getInt("id"), log.getString("username"), log.getString("password"));

                        break;

                    case 3:

                        String query = "SELECT * from usuario";
                        ResultSet resultado = this.conectorDB.obtenerConsulta(query);

                        while (resultado.next()) {
                            int idDB = resultado.getInt("id");
                            String nombreDB = resultado.getString("username");
                            String contraseniaDB = resultado.getString("password");
                            Usuario usuarioDB = new Usuario(idDB, nombreDB, contraseniaDB);
                            System.out.printf(usuarioDB.toString());

                        }
                        break;

                    default:
                        System.out.printf("Dato ingresado incorrecto");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }while(opcion == 0);
    }
}
