package aulas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import aulas.objetos.Registro;

public class Loja {
	static Scanner scanner =  new Scanner(System.in);
	static ArrayList<Registro> registro = new ArrayList<>();
	
	public static void main(String[] args) {
		chamarMenu();
	}
	
	public static void chamarMenu() {
		int opc=0;
		do {
			System.out.println("\n");
			System.out.println("[Menu]");
			System.out.println("[1] - Calcular Preço Total");
			System.out.println("[2] - Calcular Troco");
			System.out.println("[3] - Registro");
			System.out.println("[4] - Sair");
			System.out.println("Digite a Opção:");
			opc = scanner.nextInt();
			scanner.nextLine();
			if(opc != 4) {
				validarOpc(opc);
			} else {
				System.out.println("Saindo...");
			}
		} while (opc!=4);
	}
	
	public static void validarOpc(int opc){
		switch(opc) {
		case 1:
			calcularPrecoTotal();
			break;
		case 2:
			calcularTroco(0,0,false);
			break;
		case 3:
			validarTipoListar();
			break;
		default:
			System.out.println("Opção Inválida, Selecione uma Opção Correta!");
		}
	}
	
	private static void validarTipoListar() {
		int opc=0;
		System.out.println("Digite a maneira que deseja Listar os Registros:");
		System.out.println("[1] - Todos");
		System.out.println("[2] - Filtrar");
		System.out.println("[3] - Cancelar");
		opc = scanner.nextInt();
		scanner.nextLine();
		if(opc == 3) {
			return;
		}
		validarOpcTipoListar(opc);
	}

	private static void validarOpcTipoListar(int opc) {
		switch(opc) {
		case 1: 
			listarRegistro();
			break;
		case 2:
			listarRegistroFiltrado();
			break;
		default:
			System.out.println("Opção inválida! Digite novamente");
			validarTipoListar();
		}
	}

	private static void listarRegistroFiltrado() {
		LocalDate data= null;
		System.out.println("Deseja filtrar de qual maneira:");
		System.out.println("[1] Dia/Mês");
		System.out.println("[2] Mês");
		System.out.println("[3] Cancelar");
		int opc = scanner.nextInt();
		scanner.nextLine();
		if(opc != 3) {
			data = validarOpcRegistroFiltrado(opc);
		} else {
			System.out.println("Saindo...");
		}
		
		if(opc == 1) {
			listarRegistradoFdm(data);
		} else if(opc == 2) {
			listarRegistradoFm(data);
		}
	}
	
	private static void listarRegistradoFm(LocalDate data) {
		boolean houveRegistro = false;
		if(registro.size() != 0) {
			for(int i=0;i < registro.size();i++) {
				LocalDate dataRegistro = registro.get(i).getDataRegistro();
				if(dataRegistro.getYear() == data.getYear()) {
					if(dataRegistro.getMonth() == data.getMonth()) {
						System.out.printf("%d - ", i+1);
						registro.get(i).listarRegistroFiltrado();
						houveRegistro = true;
					}
				}
			}
		} else {
			System.out.println("Sem registro");
		}
		if(houveRegistro == false) {
			System.out.println("Sem registros na data mencionada");
		}
	}

	private static void listarRegistradoFdm(LocalDate data) {
		boolean houveRegistro = false;
		if(registro.size() != 0) {
			for(int i=0;i < registro.size();i++) {
				LocalDate dataRegistro = registro.get(i).getDataRegistro();
				if(data.getMonth() == dataRegistro.getMonth()) {
					if(data.getDayOfMonth() == dataRegistro.getDayOfMonth()) {
						System.out.printf("%d - ", i+1);
						registro.get(i).listarRegistroFiltrado();
					}
				}
				}
			} else {
			System.out.println("Sem registro");
		}
		if(houveRegistro == false) {
			System.out.println("Sem registros na data mencionada");
		}
	}

	private static LocalDate validarOpcRegistroFiltrado(int opc) {
		LocalDate data = null;
		DateTimeFormatter dataFormatada;
		String dataRecebida = null;
		switch(opc) {
		case 1:
			System.out.println("Digite: Dia/Mês/Ano - Ex: 01/12/2026");
			dataRecebida = scanner.nextLine();
			dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = LocalDate.parse(dataRecebida, dataFormatada);
			break;
		case 2:
			System.out.println("Digite o Mês/Ano - Ex: 12/2026");
			dataRecebida = scanner.nextLine();
			dataRecebida = ("01/" + dataRecebida );
			dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = LocalDate.parse(dataRecebida, dataFormatada);
			break;
		default:
			System.out.println("Opção inválida! Digite novamente");
			listarRegistroFiltrado();
		}
		
		return data;
	}

	private static void listarRegistro() {
		if(registro.size() != 0) {
			for(int i=0;i < registro.size();i++) {
				System.out.printf("%d - ", i+1);
				registro.get(i).listarRegistro();
			}
		} else {
			System.out.println("Sem registro");
		}
	}

	public static void calcularPrecoTotal() {
			System.out.println("Digite a Quantidade da Planta Referida:");
			int qntd = scanner.nextInt();
			
			System.out.println("Digite o Valor da Planta Referida:");
			double valor = scanner.nextDouble();
			
			double totalVenda = qntd * valor;
			
		    double totalVendaD = validarDescontoEspecial(qntd, totalVenda);
		    
		    if(totalVendaD != totalVenda) {
		    	boolean descontoAplicado = true;
				calcularTroco(qntd, totalVendaD, descontoAplicado);
		    } else { 
		    	boolean descontoAplicado = false;
				calcularTroco(qntd, totalVendaD, descontoAplicado);
			}
	}
	
	public static void calcularTroco(int qntd, double totalVenda, boolean descontoAplicado) {
		if(totalVenda == 0) {
			calcularPrecoTotal();
		}
		System.out.printf("Valor da Venda: %.2f\n", totalVenda);
		System.out.println("Digite o Valor Recebido do Cliente:");
		double valorRecebido = scanner.nextDouble();
		
		if(valorRecebido < totalVenda) {
			int opc = validarValorRecebidoInferior();
			if(opc == 1) {
				calcularTroco(qntd, totalVenda, descontoAplicado);
			}
		}
		
		double troco = valorRecebido - totalVenda;
		
		System.out.printf("Troco da Venda: %.2f \n", troco);
			
		LocalDate dataRegistro = LocalDate.now();

		Registro venda = new Registro(qntd, totalVenda, descontoAplicado, dataRegistro);
		registro.add(venda);
	}
	
	private static int validarValorRecebidoInferior() {
		System.out.println("Valor recebido do cliente é menor que o Total da Venda");
		System.out.println("O que deseja fazer: ");
		System.out.println("[1] - Inserir o valor recebido do cliente novamente");
		System.out.println("[2] - Cancelar a venda");
		int opc = scanner.nextInt();
		
		switch(opc) {
		case 1:
			System.out.println("Voltando ao valor recebido..");
			break;
		case 2:
			System.out.println("Retornando ao menu..");
			chamarMenu();
			break;
		default:
			chamarMenu();
		}
		
		return opc;
	}

	public static double validarDescontoEspecial(int qntd, double totalVenda) {
		if(qntd > 10) {
			double desconto = 0.95;
			totalVenda = totalVenda * desconto;
		}
		return totalVenda;
	}

}
