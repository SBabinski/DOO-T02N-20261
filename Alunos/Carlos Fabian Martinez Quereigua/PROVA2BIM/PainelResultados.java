package view;

import model.Serie;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class PainelResultados extends JPanel {

    private JLabel lblTitulo;
    private JPanel painelCards;

    public PainelResultados() {
        setLayout(new BorderLayout());

        lblTitulo = new JLabel("Resultados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 8, 0));

        painelCards = new JPanel();
        painelCards.setLayout(new BoxLayout(painelCards, BoxLayout.Y_AXIS));
        painelCards.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(painelCards);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setTitulo(String titulo) {
        lblTitulo.setText(titulo);
    }

    public void exibirSeries(List<Serie> series, Consumer<Serie> aoSelecionarSerie) {
        painelCards.removeAll();

        if (series == null || series.isEmpty()) {
            exibirMensagem("Nenhuma série encontrada.");
            return;
        }

        for (Serie serie : series) {
            PainelSerie card = new PainelSerie(serie);

            card.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    aoSelecionarSerie.accept(serie);
                    marcarCardSelecionado(card);
                }
            });

            painelCards.add(card);
            painelCards.add(Box.createVerticalStrut(8));
        }

        atualizarTela();
    }

    public void exibirMensagem(String mensagem) {
        painelCards.removeAll();

        JLabel label = new JLabel(mensagem);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

        painelCards.add(label);

        atualizarTela();
    }

    private void marcarCardSelecionado(PainelSerie cardSelecionado) {
        for (Component component : painelCards.getComponents()) {
            if (component instanceof PainelSerie painelSerie) {
                painelSerie.setSelecionado(painelSerie == cardSelecionado);
            }
        }
    }

    private void atualizarTela() {
        painelCards.revalidate();
        painelCards.repaint();
    }
}