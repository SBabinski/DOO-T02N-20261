package Calculadora.services;

import java.util.Date;

import Calculadora.model.DadosCliente;
import Calculadora.model.DadosLoja;
import Calculadora.model.DadosPedido;
import Calculadora.model.DadosVendedor;

public class ProcessaPedido {
    
    public DadosPedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, DadosCliente cliente, DadosVendedor vendedor, DadosLoja loja) {

        DadosPedido pedido = new DadosPedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja);

        if (confirmarPagamento(pedido)) {
            System.out.println("\nPedido processado com sucesso!");
            pedido.gerarDescricaoVenda();
        } else {
            System.out.println("\nPedido recusado! Reserva vencida.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(DadosPedido pedido) {
        Date hoje = new Date();
        return !hoje.after(pedido.getDataVencimentoReserva());
    }
}