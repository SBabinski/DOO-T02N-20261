public class Locacao {
    Cliente cliente;
    Veiculo veiculo;
    String retirada;
    String devolucao;
    boolean devolvida;

    Locacao(Cliente c, Veiculo v, String r) {
        cliente = c;
        veiculo = v;
        retirada = r;
        devolucao = "";
        devolvida = false;
    }

    void devolver(String d) {
        devolucao = d;
        devolvida = true;
    }

    int dias() {
        String[] r = retirada.split("/");
        String[] d = devolucao.split("/");
        return (Integer.parseInt(d[1])*30 + Integer.parseInt(d[0])) - 
               (Integer.parseInt(r[1])*30 + Integer.parseInt(r[0]));
    }

    double total() {
        return dias() * veiculo.diaria;
    }

    String mostrar() {
        String s = cliente.mostrar() + " - " + veiculo.placa + " (" + retirada + ")";
        if (devolvida) {
            s += " dev:" + devolucao + " total:R$" + total();
        } else {
            s += " ATIVA";
        }
        return s;
    }
}