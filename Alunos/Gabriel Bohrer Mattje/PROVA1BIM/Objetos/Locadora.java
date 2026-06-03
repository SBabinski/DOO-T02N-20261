package PROVA1BIM.Objetos;

import java.util.ArrayList;

public class Locadora {

    ArrayList<Locacao> locacoes = new ArrayList<>();

    public void adicionarLocacao(Locacao l) {
        if (locacoes.size() < 10) {
            locacoes.add(l);
        } else {
            System.out.println("Limite de locações atingido.");
        }
    }

    public void listarAtivas() {
        System.out.println("====LOCAÇÕES ATIVAS====");
        for (Locacao l : locacoes) {
            if (l.estaAtiva()) {
                System.out.println("Cliente: " + l.getCliente().getNome());
                System.out.println("Placa: " + l.getVeiculo().getPlaca());
                System.out.println("Total: R$" + l.calcularTotal());
            }
        }
    }

    public ArrayList<Locacao> getLocacoes() {
        return locacoes;
    }
}