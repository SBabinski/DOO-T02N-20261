package fag.objetos;

public class ProcessaLocacao {
	
	public static void processa(Cliente cliente, Veiculo veiculo, String dataRetirada, String dataDevolucao) {
		
		if(temEspaco()) {
			
			for (int i = 0; i < BancoDeDados.locacoes.length; i++) {
			    if (BancoDeDados.locacoes[i] == null) {
			    	BancoDeDados.locacoes[i] = new Locacao(cliente, veiculo, dataRetirada, dataDevolucao);
			        break;
			    }
			}
		}
		else {
			System.out.println("Não há possibilidade de nova locação");
		}
	}
	
	
	private static boolean temEspaco() {
		for (Locacao l : BancoDeDados.locacoes) {
		    if (l == null) return true;
		}
		return false;
	}




	

}
