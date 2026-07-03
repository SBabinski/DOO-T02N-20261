public class GerenciadorUsuarios {

    private static Usuario usuario = new Usuario(
            "Matheus",
            "matheus@teste.com"
    );

    public static Usuario getUsuario() {
        return usuario;
    }
}