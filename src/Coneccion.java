public enum Coneccion {

    URL("jdbc:mysql://localhost:3306/login_usuario"),
    USUARIO("root"),
    CONTRASENA("genshin587");

    private final String description;

    private Coneccion(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
