public class Main {
    public static void main(String[] args) {

        Usuario u = PersistenciaUsuario.carregar();

        new TelaPrincipal(u);
    }
}