public class Cliente {
    String nome;
    String cpf;
    String cnh;

    Cliente(String n, String c, String h) {
        nome = n;
        cpf = c;
        cnh = h;
    }

    String mostrar() {
        return nome + " CPF:" + cpf + " CNH:" + cnh;
    }
}