import javax.swing.*;
import java.awt.*;

public class Controlador {

    private JFrame tela;
    private JPanel painelPrincipal;
    private CardLayout cardLayout;

    private TelaInicial telaInicial;
    private TelaResultado telaResultado;

    public void iniciarApp() {
        tela = new JFrame("Weather Report");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(750, 800);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        telaInicial = new TelaInicial(this);
        telaResultado = new TelaResultado(this);

        painelPrincipal.add(telaInicial.getPainel(), "selecao");
        painelPrincipal.add(telaResultado.getPainel(), "resultado");

        tela.add(painelPrincipal);
        tela.setVisible(true);
    }

    public void mostrarTela(String nomeTela) {
        cardLayout.show(painelPrincipal, nomeTela);
    }

    public void atualizarResultado(PrevisaoResultado previsao) {
        telaResultado.atualizarResultado(previsao);
    }

}