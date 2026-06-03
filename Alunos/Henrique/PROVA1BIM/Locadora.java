package fag;

public class Locadora {
	
	Locacao[] locacoes = new Locacao[10];
	int total = 0;
	
	
	public void adicionarLocacao(Locacao l) {
		if(total < 10) {
			locacoes[total] = l;
			total++;
			System.out.println("Locação registrada com sucesso!");
		}else {
			System.out.println("Limite de 10 Locações Atingidos!");
		}
	}
	
	public void listarAtivas() {
		System.out.println("---- LOCAÇÕES ATIVAS----");
		boolean ativa = false;
		for (int i = 0; i < total; i++) {
			if (!locacoes[i].devolvido) {
				locacoes[i].ExibirDados();
				ativa = true;
			}
		}
		if(!ativa) {
			System.out.println("Nenhuma Locação Ativa.");
		}
	}
}
