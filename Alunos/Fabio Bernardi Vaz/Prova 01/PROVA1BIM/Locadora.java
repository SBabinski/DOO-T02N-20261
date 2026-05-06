package fag;

import java.util.ArrayList;
import java.util.List;

public class Locadora {

    private String nome;
    private Locacao[] locacoes;
    private int totalLocacoes;
    private static final int CAPACIDADE_MAXIMA = 10;

    public Locadora(String nome) {
        this.nome = nome;
        this.locacoes = new Locacao[CAPACIDADE_MAXIMA];
        this.totalLocacoes = 0;
    }

    public void adicionarLocacao(Locacao nova) {
        
        for (int i = 0; i < totalLocacoes; i++) {
            if (locacoes[i] != null && locacoes[i].isDevolucaoRealizada()) {
                locacoes[i] = nova; 
                System.out.println("Locação registrada reutilizando vaga antiga.");
                return; 
            }
        }

        
        if (totalLocacoes < locacoes.length) {
            locacoes[totalLocacoes] = nova;
            totalLocacoes++;
            System.out.println("Locação registrada em nova vaga.");
        } else {
            System.out.println("Erro: Limite de locações atingido!");
        }
    }

    public void listarLocacoesSemDevolucao() {

    System.out.println("\nLocações sem devolução");
        int contador = 0;

        for (int i = 0; i < totalLocacoes; i++) {
            if (!locacoes[i].isDevolucaoRealizada()) {
                locacoes[i].apresentarse();
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Nenhuma locação em aberto no momento.");
        } else {
            System.out.println("\nTotal de locações em aberto: " + contador);
        }
    }

    public void listarTodasLocacoes() {
        System.out.println("\nTodas as locações" );

        if (totalLocacoes == 0) {
            System.out.println("Nenhuma locação registrada.");
        }

        for (int i = 0; i < totalLocacoes; i++) {
            locacoes[i].apresentarse();
        }

    }

    public List<Locacao> obterLocacoesEmAberto() {
        List<Locacao> abertas = new ArrayList<>();

        for (int i = 0; i < totalLocacoes; i++) {
            if (locacoes[i] != null && !locacoes[i].isDevolucaoRealizada()) {
                abertas.add(locacoes[i]);
            }
        }
        return abertas;
    }

    public Locacao[] getLocacoes() {
        return locacoes;
    }

    public int getTotalLocacoes() {
        return totalLocacoes;
    }

    public String getNome() {
        return nome;
    }
}
