package tela;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.PerfilUsuario;
import modelo.Serie;
import servico.ServicoPerfil;
import servico.ServicoTvMaze;

public class TelaBusca extends JPanel {

    private final ServicoTvMaze servicoTvMaze;
    private final PerfilUsuario perfil;
    private final ServicoPerfil servicoPerfil;

    private final JTextField campoBusca;
    private final JPanel painelResultados;
    private final JLabel lblStatus;

    public TelaBusca(ServicoTvMaze servicoTvMaze,
                     PerfilUsuario perfil,
                     ServicoPerfil servicoPerfil) {
        this.servicoTvMaze = servicoTvMaze;
        this.perfil        = perfil;
        this.servicoPerfil = servicoPerfil;

        setLayout(new BorderLayout(0, 0));
        setBackground(TemaVisual.FUNDO_BASE);

        JPanel areaHero = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(TemaVisual.ACENTO.getRed(),
                                        TemaVisual.ACENTO.getGreen(),
                                        TemaVisual.ACENTO.getBlue(), 25),
                        0, getHeight(), TemaVisual.FUNDO_BASE);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        areaHero.setLayout(new BoxLayout(areaHero, BoxLayout.Y_AXIS));
        areaHero.setOpaque(false);
        areaHero.setBorder(new EmptyBorder(28, 26, 22, 26));

        JLabel lblTitulo = ComponentesVisuais.labelTitulo("Buscar Séries");
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        areaHero.add(lblTitulo);
        areaHero.add(Box.createVerticalStrut(4));

        JLabel lblSub = ComponentesVisuais.labelCorpo("Pesquise em milhares de séries via TVMaze");
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);
        areaHero.add(lblSub);
        areaHero.add(Box.createVerticalStrut(16));

        JPanel barraBusca = new JPanel(new BorderLayout(10, 0));
        barraBusca.setOpaque(false);
        barraBusca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        barraBusca.setAlignmentX(Component.LEFT_ALIGNMENT);

        campoBusca = ComponentesVisuais.campoDeBusca(30);
        campoBusca.setFont(new Font("Dialog", Font.PLAIN, 14));
        campoBusca.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) realizarBusca();
            }
        });

        JButton btnBuscar = ComponentesVisuais.botaoPrimario("Buscar");
        btnBuscar.setFont(new Font("Dialog", Font.BOLD, 13));
        btnBuscar.addActionListener(e -> realizarBusca());

        barraBusca.add(campoBusca, BorderLayout.CENTER);
        barraBusca.add(btnBuscar,  BorderLayout.EAST);
        areaHero.add(barraBusca);
        areaHero.add(Box.createVerticalStrut(10));

        lblStatus = ComponentesVisuais.labelDiscreto("Digite o nome de uma série e pressione Enter.");
        lblStatus.setAlignmentX(Component.LEFT_ALIGNMENT);
        areaHero.add(lblStatus);

        painelResultados = new JPanel();
        painelResultados.setLayout(new BoxLayout(painelResultados, BoxLayout.Y_AXIS));
        painelResultados.setBackground(TemaVisual.FUNDO_BASE);
        painelResultados.setBorder(new EmptyBorder(14, 18, 18, 18));

        JScrollPane scroll = ComponentesVisuais.scrollEscuro(painelResultados);

        JPanel wrapper = new JPanel(new BorderLayout(0, 0));
        wrapper.setBackground(TemaVisual.FUNDO_BASE);
        wrapper.add(areaHero, BorderLayout.NORTH);
        wrapper.add(scroll,   BorderLayout.CENTER);
        add(wrapper, BorderLayout.CENTER);
    }


    private void realizarBusca() {
        String consulta = campoBusca.getText().trim();
        if (consulta.isEmpty()) {
            definirStatus("Digite um nome para buscar.", TemaVisual.COR_CANCELADA);
            return;
        }
        definirStatus("Buscando \"" + consulta + "\"...", TemaVisual.TEXTO_DISCRETO);
        painelResultados.removeAll();
        painelResultados.revalidate();
        painelResultados.repaint();

        SwingWorker<List<Serie>, Void> tarefa = new SwingWorker<>() {
            @Override protected List<Serie> doInBackground() throws Exception {
                return servicoTvMaze.buscarSeries(consulta);
            }
            @Override protected void done() {
                try { exibirResultados(get(), consulta); }
                catch (Exception ex) {
                    definirStatus("Erro na busca: " + ex.getMessage(), TemaVisual.COR_CANCELADA);
                    painelResultados.removeAll();
                    painelResultados.revalidate();
                    painelResultados.repaint();
                }
            }
        };
        tarefa.execute();
    }

    private void exibirResultados(List<Serie> series, String consulta) {
        painelResultados.removeAll();
        if (series.isEmpty()) {
            definirStatus("Nenhuma série encontrada para \"" + consulta + "\".", TemaVisual.COR_CANCELADA);
            JLabel lblVazio = new JLabel("Nenhum resultado encontrado.", SwingConstants.CENTER);
            lblVazio.setFont(TemaVisual.FONTE_CORPO);
            lblVazio.setForeground(TemaVisual.TEXTO_DISCRETO);
            lblVazio.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblVazio.setBorder(new EmptyBorder(40, 0, 0, 0));
            painelResultados.add(lblVazio);
        } else {
            definirStatus(series.size() + " resultado(s) para \"" + consulta + "\".", TemaVisual.COR_NO_AR);
            for (Serie serie : series) {
                CardSerie card = new CardSerie(serie, perfil, this::abrirDetalhes, () -> {});
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                painelResultados.add(card);
                painelResultados.add(Box.createVerticalStrut(8));
            }
        }
        painelResultados.revalidate();
        painelResultados.repaint();
    }

    private void abrirDetalhes(Serie serie) {
        Window janela = SwingUtilities.getWindowAncestor(this);
        Frame frame = (janela instanceof Frame f) ? f : null;
        new JanelaDetalhes(frame, serie, perfil, servicoPerfil, () -> {}).setVisible(true);
    }

    private void definirStatus(String texto, Color cor) {
        lblStatus.setText(texto);
        lblStatus.setForeground(cor);
    }
}
