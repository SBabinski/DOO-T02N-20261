package view;

import model.Serie;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PainelSerie extends JPanel {

    private Serie serie;

    public PainelSerie(Serie serie) {
        this.serie = serie;

        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

        montarCard();
    }

    private void montarCard() {
        JPanel painelTexto = new JPanel();
        painelTexto.setLayout(new GridLayout(5, 1));
        painelTexto.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        painelTexto.setBackground(Color.WHITE);

        JLabel lblNome = new JLabel(valorTexto(serie.getName()));
        lblNome.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel lblGeneros = new JLabel("Gêneros: " + formatarGeneros(serie.getGenres()));
        JLabel lblNota = new JLabel("Nota: " + valorNota(serie.getNotaGeral()));
        JLabel lblEstado = new JLabel("Estado: " + valorTexto(serie.getStatus()));
        JLabel lblEstreia = new JLabel("Estreia: " + valorData(serie.getPremiered()));

        painelTexto.add(lblNome);
        painelTexto.add(lblGeneros);
        painelTexto.add(lblNota);
        painelTexto.add(lblEstado);
        painelTexto.add(lblEstreia);

        add(painelTexto, BorderLayout.CENTER);
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSelecionado(boolean selecionado) {
        if (selecionado) {
            setBorder(new LineBorder(Color.BLUE, 2));
        } else {
            setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        }
    }

    private String valorTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            return "Não informado";
        }
        return texto;
    }

    private String valorNota(Double nota) {
        if (nota == null) {
            return "Não informado";
        }
        return String.valueOf(nota);
    }

    private String valorData(LocalDate data) {
        if (data == null) {
            return "Não informado";
        }
        return data.toString();
    }

    private String formatarGeneros(List<String> generos) {
        if (generos == null || generos.isEmpty()) {
            return "Não informado";
        }
        return String.join(", ", generos);
    }
}