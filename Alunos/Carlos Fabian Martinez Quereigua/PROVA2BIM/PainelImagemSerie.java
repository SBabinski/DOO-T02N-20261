package view;

import model.Serie;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class PainelImagemSerie extends JPanel {

    private JLabel lblImagem;

    private JButton btnAssistida;
    private JButton btnFavorita;
    private JButton btnDesejada;

    public PainelImagemSerie() {
        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(250, 0));
        setBorder(BorderFactory.createTitledBorder("Imagem da série"));

        criarComponentes();

        limparImagem();
    }

    private void criarComponentes() {
        lblImagem = new JLabel("Sem imagem", SwingConstants.CENTER);
        lblImagem.setPreferredSize(new Dimension(220, 320));
        lblImagem.setBorder(new LineBorder(Color.GRAY));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(3, 1, 5, 5));

        btnAssistida = new JButton("Marcar como assistida");
        btnFavorita = new JButton("Marcar como favorita");
        btnDesejada = new JButton("Marcar como desejo assistir");

        painelBotoes.add(btnAssistida);
        painelBotoes.add(btnFavorita);
        painelBotoes.add(btnDesejada);

        add(lblImagem, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public void exibirImagemSerie(Serie serie) {
        if (serie == null) {
            limparImagem();
            return;
        }

        try {
            String urlImagem = serie.getUrlImage();

            if (urlImagem == null || urlImagem.isBlank()) {
                limparImagem();
                return;
            }

            ImageIcon imagemOriginal = new ImageIcon(new URL(urlImagem));

            Image imagemRedimensionada = imagemOriginal.getImage()
                    .getScaledInstance(200, 300, Image.SCALE_SMOOTH);

            lblImagem.setIcon(new ImageIcon(imagemRedimensionada));
            lblImagem.setText("");

        } catch (Exception e) {
            lblImagem.setIcon(null);
            lblImagem.setText("Erro ao carregar imagem");
        }
    }

    public void limparImagem() {
        lblImagem.setIcon(null);
        lblImagem.setText("Sem imagem");
    }

    public void atualizarTextoBotoes(boolean estaAssistida, boolean estaFavorita, boolean estaDesejada) {
        if (estaAssistida) {
            btnAssistida.setText("Retirar das assistidas");
        } else {
            btnAssistida.setText("Marcar como assistida");
        }

        if (estaFavorita) {
            btnFavorita.setText("Retirar das favoritas");
        } else {
            btnFavorita.setText("Marcar como favorita");
        }

        if (estaDesejada) {
            btnDesejada.setText("Retirar de desejo assistir");
        } else {
            btnDesejada.setText("Marcar como desejo assistir");
        }
    }

    public JButton getBtnAssistida() {
        return btnAssistida;
    }

    public JButton getBtnFavorita() {
        return btnFavorita;
    }

    public JButton getBtnDesejada() {
        return btnDesejada;
    }
}