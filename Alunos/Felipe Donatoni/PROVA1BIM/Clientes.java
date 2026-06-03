public class Clientes {
    
    String nome;
    String CPF;
    String CNH;

    public Clientes (String nome, String CPF, String CNH) {
        this.nome = nome;
        this.CPF = CPF;
        this.CNH = CNH;
    }
    public void exibir(){
        System.out.println("Nome cliente: " + nome);
        System.out.println("CPF: " + CPF);
        System.out.println("CNH: " + CNH);
    }
}
