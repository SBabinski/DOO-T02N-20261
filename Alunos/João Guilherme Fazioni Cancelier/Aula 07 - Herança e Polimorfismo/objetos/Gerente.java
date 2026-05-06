package objetos;

public class Gerente extends Funcionario {
    private static int contador = 0;
	private int idGerente;

    public Gerente(String nome, int idade, String cidade, String bairro, String rua, double salarioBase, Loja loja) {
        super(nome, idade, cidade, bairro, rua, salarioBase, loja);
        this.idGerente = ++contador;

        this.getSalarioRecebido().clear();
        this.getSalarioRecebido().add(3000.0);
        this.getSalarioRecebido().add(3500.0);
        this.getSalarioRecebido().add(4000.0);
        
    }
    @Override
    public void apresentarse() {
        super.apresentarse();
        System.out.println("ID Gerente: " + idGerente);
    }

    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.35;
    }
    public int getIdGerente() {
        return idGerente;
    }
}
