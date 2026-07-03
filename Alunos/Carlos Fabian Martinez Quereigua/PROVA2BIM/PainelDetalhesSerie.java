package view;

import model.Serie;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PainelDetalhesSerie extends JPanel {

    private JLabel lblNome;
    private JLabel lblIdioma;
    private JLabel lblGeneros;
    private JLabel lblNota;
    private JLabel lblEstado;
    private JLabel lblEstreia;
    private JLabel lblTermino;
    private JLabel lblEmissora;

    public PainelDetalhesSerie() {
        setLayout(new GridLayout(8, 1, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Informações da série"));

        lblNome = criarLabel();
        lblIdioma = criarLabel();
        lblGeneros = criarLabel();
        lblNota = criarLabel();
        lblEstado = criarLabel();
        lblEstreia = criarLabel();
        lblTermino = criarLabel();
        lblEmissora = criarLabel();

        add(lblNome);
        add(lblIdioma);
        add(lblGeneros);
        add(lblNota);
        add(lblEstado);
        add(lblEstreia);
        add(lblTermino);
        add(lblEmissora);

        limpar();
    }

    private JLabel criarLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    public void exibirSerie(Serie serie) {
        if (serie == null) {
            limpar();
            return;
        }

        lblNome.setText("Nome: " + valorTexto(serie.getName()));
        lblIdioma.setText("Idioma: " + valorTexto(serie.getLanguage()));
        lblGeneros.setText("Gêneros: " + formatarGeneros(serie.getGenres()));
        lblNota.setText("Nota geral: " + valorNota(serie.getNotaGeral()));
        lblEstado.setText("Estado: " + valorTexto(serie.getStatus()));
        lblEstreia.setText("Estreia: " + valorData(serie.getPremiered()));
        lblTermino.setText("Término: " + valorData(serie.getEnded()));
        lblEmissora.setText("Emissora: " + serie.getNomeEmissora());
    }

    public void limpar() {
        lblNome.setText("Nome: -");
        lblIdioma.setText("Idioma: -");
        lblGeneros.setText("Gêneros: -");
        lblNota.setText("Nota geral: -");
        lblEstado.setText("Estado: -");
        lblEstreia.setText("Estreia: -");
        lblTermino.setText("Término: -");
        lblEmissora.setText("Emissora: -");
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