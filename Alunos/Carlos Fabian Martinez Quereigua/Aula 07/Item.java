package fag.objetos;

public class Item {
	private static int idCount = 1;
    private int id;
    private String nome;
    private String tipo;
    private double valor;
    
    public Item() {
    	this.id = idCount++;
    }

    public Item(String nome, String tipo, double valor) {
    	this.id = idCount++;
    	setNome(nome);
    	setTipo(tipo);
    	setValor(valor);
    }

    public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null && !nome.isBlank()) {
			this.nome = nome;
		}
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if(tipo != null && !tipo.isBlank()) {
			this.tipo = tipo;
		}
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		if(valor >= 0) {
			this.valor = valor;
		}
	}

	public void gerarDescricao() {
    	System.out.println("ID: " + id);
    	System.out.println("Nome: " + nome);
    	System.out.println("Tipo: " + tipo);
    	System.out.println("Valor: R$ " + valor);
    }
}
