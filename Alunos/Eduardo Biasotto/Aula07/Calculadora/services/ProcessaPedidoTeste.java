package Calculadora.services;

import java.util.Calendar;
import java.util.Date;
import Calculadora.model.*;

public class ProcessaPedidoTeste {

    public static void main(String[] args) {
    System.out.println("Iniciando testes...");

    ProcessaPedido processador = new ProcessaPedido();

    // Dados para teste
    DadosEndereco endereco = new DadosEndereco("Santa Tereza do Oeste", "Parque Verde", "Guanabara", "PR", "Delegacia", 267);
    DadosCliente cliente = new DadosCliente("Eduardo", 30, endereco);
    DadosVendedor vendedor = new DadosVendedor("Jorge", 25, null, endereco, 4000.0);
    DadosEndereco enderecoLoja = new DadosEndereco("Cascavel", "FAG", "Av. das torres", "PR", "Complemento", 1);
    DadosLoja loja = new DadosLoja("My Plant", "My Plant LTDA", "00.000.000/0001-00", enderecoLoja);

    Date hoje = new Date();

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, 5);
    Date dataFutura = cal.getTime();

    System.out.println("\n--- TESTE 1: Reserva válida ---");
    processador.processar(1, hoje, hoje, dataFutura, cliente, vendedor, loja);

    cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, -5);
    Date dataPassada = cal.getTime();

    System.out.println("\n--- TESTE 2: Reserva vencida ---");
    processador.processar(2, hoje, hoje, dataPassada, cliente, vendedor, loja);
}
}