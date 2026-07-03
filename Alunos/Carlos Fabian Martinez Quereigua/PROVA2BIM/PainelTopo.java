package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PainelTopo extends JPanel {

    private JTextField txtNomeSerie;
    private JButton btnBuscar;

    private JButton btnSeriesAssistidas;
    private JButton btnSeriesFavoritas;
    private JButton btnSeriesDesejoAssistir;

    private JComboBox<String> comboOrdenacao;

    private JLabel lblUsuario;

    public PainelTopo(String nomeUsuario) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        criarPainelPesquisa(nomeUsuario);
        criarPainelBotoes();
    }

    private void criarPainelPesquisa(String nomeUsuario) {
        JPanel painelPesquisa = new JPanel(new BorderLayout());

        JPanel painelEsquerdo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblNome = new JLabel("Nome:");
        txtNomeSerie = new JTextField(25);
        btnBuscar = new JButton("Buscar");

        painelEsquerdo.add(lblNome);
        painelEsquerdo.add(txtNomeSerie);
        painelEsquerdo.add(btnBuscar);

        JPanel painelDireito = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        lblUsuario = new JLabel("Usuário: " + nomeUsuario);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));

        painelDireito.add(lblUsuario);

        painelPesquisa.add(painelEsquerdo, BorderLayout.WEST);
        painelPesquisa.add(painelDireito, BorderLayout.EAST);

        add(painelPesquisa, BorderLayout.NORTH);
    }

    private void criarPainelBotoes() {
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnSeriesAssistidas = new JButton("Séries assistidas");
        btnSeriesFavoritas = new JButton("Favoritas");
        btnSeriesDesejoAssistir = new JButton("Desejo assistir");

        comboOrdenacao = new JComboBox<>(new String[]{
                "Ordenar por...",
                "Nome",
                "Nota geral",
                "Estado",
                "Data de estreia"
        });

        painelBotoes.add(btnSeriesAssistidas);
        painelBotoes.add(btnSeriesFavoritas);
        painelBotoes.add(btnSeriesDesejoAssistir);
        painelBotoes.add(comboOrdenacao);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    public String getNomePesquisado() {
        return txtNomeSerie.getText().trim();
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnSeriesAssistidas() {
        return btnSeriesAssistidas;
    }

    public JButton getBtnSeriesFavoritas() {
        return btnSeriesFavoritas;
    }

    public JButton getBtnSeriesDesejoAssistir() {
        return btnSeriesDesejoAssistir;
    }

    public JComboBox<String> getComboOrdenacao() {
        return comboOrdenacao;
    }

    public void setNomeUsuario(String nomeUsuario) {
        lblUsuario.setText("Usuário: " + nomeUsuario);
    }
}