package aulas.listagem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import aulas.BancoDados;
import aulas.dados.RegistroDados;

public class VendaListagem {
	static Scanner scanner =  new Scanner(System.in);
	static List<RegistroDados> registrosVenda = BancoDados.getRegistros();
	
	public static void validarTipoListar() {
		int opc=0;
		System.out.println("Digite a maneira que deseja Listar os Registros:");
		System.out.println("[1] - Todos");
		System.out.println("[2] - Filtrar");
		System.out.println("[3] - Voltar");
		opc = scanner.nextInt();
		scanner.nextLine();
		if(opc == 3) {
			BancoDados.mensagemVoltar();
			return;
		}
		validarOpcTipoListar(opc);
	}

	public static void validarOpcTipoListar(int opc) {
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

	public static void listarRegistroFiltrado() {
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
	
	public static void listarRegistradoFm(LocalDate data) {
		boolean houveRegistro = false;
		if(registrosVenda.size() != 0) {
			for(int i=0;i < registrosVenda.size();i++) {
				LocalDate dataRegistro = registrosVenda.get(i).getDataRegistro();
				if(dataRegistro.getYear() == data.getYear()) {
					if(dataRegistro.getMonth() == data.getMonth()) {
						System.out.printf("%d - ", i+1);
						registrosVenda.get(i).listarRegistroFiltrado();
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

	public static void listarRegistradoFdm(LocalDate data) {
		boolean houveRegistro = false;
		if(registrosVenda.size() != 0) {
			for(int i=0;i < registrosVenda.size();i++) {
				LocalDate dataRegistro = registrosVenda.get(i).getDataRegistro();
				if(data.getMonth() == dataRegistro.getMonth()) {
					if(data.getDayOfMonth() == dataRegistro.getDayOfMonth()) {
						System.out.printf("%d - ", i+1);
						registrosVenda.get(i).listarRegistroFiltrado();
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

	public static LocalDate validarOpcRegistroFiltrado(int opc) {
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

	public static void listarRegistro() {
		if(registrosVenda.size() != 0) {
			for(int i=0;i < registrosVenda.size();i++) {
				System.out.printf("%d - ", i+1);
				registrosVenda.get(i).listarRegistro();
			}
		} else {
			BancoDados.mensagemListaVazia("Registro de Venda");
		}
	}
}
