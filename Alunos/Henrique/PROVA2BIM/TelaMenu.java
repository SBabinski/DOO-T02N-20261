package fag;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaMenu extends JFrame {

    Usuario usuario;

    public TelaMenu(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Minhas Séries - " + usuario.getNome());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Persistencia.salvar(usuario);
                System.exit(0);
            }
        });
        setLocationRelativeTo(null);

        JPanel lateral = new JPanel();
        lateral.setLayout(new BoxLayout(lateral, BoxLayout.Y_AXIS));
        lateral.setBackground(new Color(43, 43, 43));
        lateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BorderLayout());
        conteudo.setBackground(new Color(30, 30, 30));
        add(conteudo, BorderLayout.CENTER);

        JButton btBuscar = criarBotao("Buscar Séries");
        JButton btFavoritos = criarBotao("Favoritos");
        JButton btAssistidos = criarBotao("Já Assistidas");
        JButton btAssistir = criarBotao("Quero Assistir");

        btBuscar.addActionListener(e -> {
            conteudo.removeAll();

            JPanel painelBusca = new JPanel(new BorderLayout());
            painelBusca.setBackground(new Color(30, 30, 30));

            JPanel topo = new JPanel();
            topo.setBackground(new Color(30, 30, 30));

            JTextField campoBusca = new JTextField(20);
            campoBusca.setPreferredSize(new Dimension(400, 35));
            campoBusca.setFont(new Font("Arial", Font.PLAIN, 14));
            campoBusca.setBackground(new Color(60, 60, 60));
            campoBusca.setForeground(Color.WHITE);
            campoBusca.setCaretColor(Color.WHITE);

            JLabel labelSerie = new JLabel("Série:");
            labelSerie.setFont(new Font("Arial", Font.BOLD, 14));
            labelSerie.setForeground(Color.WHITE);

            JButton btnBuscar2 = criarBotao("Buscar");
            btnBuscar2.setMaximumSize(new Dimension(100, 35));
            btnBuscar2.setPreferredSize(new Dimension(100, 35));
            
            campoBusca.addActionListener(ev -> btnBuscar2.doClick());

            topo.add(labelSerie);
            topo.add(campoBusca);
            topo.add(btnBuscar2);

            DefaultListModel<String> modelo = new DefaultListModel<>();
            JList<String> lista = new JList<>(modelo);
            lista.setBackground(new Color(45, 45, 45));
            lista.setForeground(Color.WHITE);
            lista.setSelectionBackground(new Color(26, 115, 232));
            lista.setSelectionForeground(Color.WHITE);

            JScrollPane scroll = new JScrollPane(lista);
            scroll.getViewport().setBackground(new Color(45, 45, 45));

            List<Serie> seriesEncontradas = new ArrayList<>();

            btnBuscar2.addActionListener(ev -> {
                String nomeBusca = campoBusca.getText().trim();
                if (!nomeBusca.isEmpty()) {
                    modelo.clear();
                    seriesEncontradas.clear();
                    TvMaze service = new TvMaze();
                    List<Serie> resultados = service.buscarSerie(nomeBusca);
                    for (Serie s : resultados) {
                        modelo.addElement(s.getNome() + " (" + s.getStatus() + ") - " + s.getNota());
                        seriesEncontradas.add(s);
                    }
                }
            });

            lista.addListSelectionListener(ev -> {
                if (!ev.getValueIsAdjusting()) {
                    int index = lista.getSelectedIndex();
                    if (index >= 0) {
                        Serie selecionada = seriesEncontradas.get(index);
                        int opcao = JOptionPane.showOptionDialog(null,
                            "Nome: " + selecionada.getNome() + "\n" +
                            "Idioma: " + selecionada.getIdioma() + "\n" +
                            "Gêneros: " + selecionada.getGeneros() + "\n" +
                            "Nota: " + selecionada.getNota() + "\n" +
                            "Status: " + selecionada.getStatus() + "\n" +
                            "Estreia: " + selecionada.getEstreia() + "\n" +
                            "Término: " + selecionada.getTermino() + "\n" +
                            "Emissora: " + (selecionada.getEmissora() != null ? selecionada.getEmissora().getNome() : "N/A"),
                            "Detalhes",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"Favoritos", "Já Assistida", "Quero Assistir", "Fechar"},
                            "Fechar"
                        );
                        if (opcao == 0) usuario.getFavoritos().add(selecionada);
                        else if (opcao == 1) usuario.getAssistidos().add(selecionada);
                        else if (opcao == 2) usuario.getAssistir().add(selecionada);
                    }
                }
            });

            painelBusca.add(topo, BorderLayout.NORTH);
            painelBusca.add(scroll, BorderLayout.CENTER);
            conteudo.add(painelBusca);
            conteudo.revalidate();
            conteudo.repaint();
        });

        btFavoritos.addActionListener(e -> {
            conteudo.removeAll();

            JPanel painelLista = new JPanel(new BorderLayout());
            painelLista.setBackground(new Color(30, 30, 30));

            JLabel titulo = new JLabel("Favoritos", SwingConstants.CENTER);
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("Arial", Font.BOLD, 16));

            JComboBox<String> ordenacao = new JComboBox<>(new String[]{"Nome", "Nota", "Status", "Data de Estreia"});
            ordenacao.setBackground(new Color(60, 60, 60));
            ordenacao.setForeground(Color.WHITE);
            ordenacao.setFont(new Font("Arial", Font.PLAIN, 13));

            DefaultListModel<String> modeloFav = new DefaultListModel<>();
            JList<String> listaFav = new JList<>(modeloFav);
            listaFav.setBackground(new Color(45, 45, 45));
            listaFav.setForeground(Color.WHITE);
            listaFav.setSelectionBackground(new Color(26, 115, 232));
            listaFav.setSelectionForeground(Color.WHITE);

            JScrollPane scroll = new JScrollPane(listaFav);
            scroll.getViewport().setBackground(new Color(45, 45, 45));

            JPanel topoLista = new JPanel();
            topoLista.setBackground(new Color(30, 30, 30));
            topoLista.add(titulo);
            topoLista.add(ordenacao);
            painelLista.add(topoLista, BorderLayout.NORTH);

            for (Serie s : usuario.getFavoritos()) {
                modeloFav.addElement(s.getNome() + " (" + s.getStatus() + ") - " + s.getNota());
            }

            ordenacao.addActionListener(ev -> {
                String criterio = (String) ordenacao.getSelectedItem();
                List<Serie> lista = new ArrayList<>(usuario.getFavoritos());
                if (criterio.equals("Nome")) lista.sort((a, b) -> a.getNome().compareTo(b.getNome()));
                else if (criterio.equals("Nota")) lista.sort((a, b) -> Double.compare(b.getNota(), a.getNota()));
                else if (criterio.equals("Status")) lista.sort((a, b) -> a.getStatus().compareTo(b.getStatus()));
                else if (criterio.equals("Data de Estreia")) lista.sort((a, b) -> a.getEstreia().compareTo(b.getEstreia()));
                modeloFav.clear();
                for (Serie s : lista) modeloFav.addElement(s.getNome() + " (" + s.getStatus() + ") - " + s.getNota());
            });

            JButton btnRemover = criarBotao("Remover");
            btnRemover.setBackground(new Color(200, 50, 50));
            btnRemover.addActionListener(ev -> {
                int index = listaFav.getSelectedIndex();
                if (index >= 0) {
                    usuario.getFavoritos().remove(index);
                    modeloFav.remove(index);
                }
            });

            painelLista.add(scroll, BorderLayout.CENTER);
            painelLista.add(btnRemover, BorderLayout.SOUTH);
            conteudo.add(painelLista);
            conteudo.revalidate();
            conteudo.repaint();
        });

        btAssistidos.addActionListener(e -> {
            conteudo.removeAll();

            JPanel painelLista = new JPanel(new BorderLayout());
            painelLista.setBackground(new Color(30, 30, 30));

            JLabel titulo = new JLabel("Já Assistidas", SwingConstants.CENTER);
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("Arial", Font.BOLD, 16));

            JComboBox<String> ordenacao = new JComboBox<>(new String[]{"Nome", "Nota", "Status", "Data de Estreia"});
            ordenacao.setBackground(new Color(60, 60, 60));
            ordenacao.setForeground(Color.WHITE);
            ordenacao.setFont(new Font("Arial", Font.PLAIN, 13));

            DefaultListModel<String> modeloFav = new DefaultListModel<>();
            JList<String> listaFav = new JList<>(modeloFav);
            listaFav.setBackground(new Color(45, 45, 45));
            listaFav.setForeground(Color.WHITE);
            listaFav.setSelectionBackground(new Color(26, 115, 232));
            listaFav.setSelectionForeground(Color.WHITE);

            JScrollPane scroll = new JScrollPane(listaFav);
            scroll.getViewport().setBackground(new Color(45, 45, 45));

            JPanel topoLista = new JPanel();
            topoLista.setBackground(new Color(30, 30, 30));
            topoLista.add(titulo);
            topoLista.add(ordenacao);
            painelLista.add(topoLista, BorderLayout.NORTH);

            for (Serie s : usuario.getAssistidos()) {
                modeloFav.addElement(s.getNome() + " (" + s.getStatus() + ") - " + s.getNota());
            }

            ordenacao.addActionListener(ev -> {
                String criterio = (String) ordenacao.getSelectedItem();
                List<Serie> lista = new ArrayList<>(usuario.getAssistidos());
                if (criterio.equals("Nome")) lista.sort((a, b) -> a.getNome().compareTo(b.getNome()));
                else if (criterio.equals("Nota")) lista.sort((a, b) -> Double.compare(b.getNota(), a.getNota()));
                else if (criterio.equals("Status")) lista.sort((a, b) -> a.getStatus().compareTo(b.getStatus()));
                else if (criterio.equals("Data de Estreia")) lista.sort((a, b) -> a.getEstreia().compareTo(b.getEstreia()));
                modeloFav.clear();
                for (Serie s : lista) modeloFav.addElement(s.getNome() + " (" + s.getStatus() + ") - " + s.getNota());
            });

            JButton btnRemover = criarBotao("Remover");
            btnRemover.setBackground(new Color(200, 50, 50));
            btnRemover.addActionListener(ev -> {
                int index = listaFav.getSelectedIndex();
                if (index >= 0) {
                    usuario.getAssistidos().remove(index);
                    modeloFav.remove(index);
                }
            });

            painelLista.add(scroll, BorderLayout.CENTER);
            painelLista.add(btnRemover, BorderLayout.SOUTH);
            conteudo.add(painelLista);
            conteudo.revalidate();
            conteudo.repaint();
        });

        btAssistir.addActionListener(e -> {
            conteudo.removeAll();

            JPanel painelLista = new JPanel(new BorderLayout());
            painelLista.setBackground(new Color(30, 30, 30));

            JLabel titulo = new JLabel("Quero Assistir", SwingConstants.CENTER);
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("Arial", Font.BOLD, 16));

            JComboBox<String> ordenacao = new JComboBox<>(new String[]{"Nome", "Nota", "Status", "Data de Estreia"});
            ordenacao.setBackground(new Color(60, 60, 60));
            ordenacao.setForeground(Color.WHITE);
            ordenacao.setFont(new Font("Arial", Font.PLAIN, 13));

            DefaultListModel<String> modeloFav = new DefaultListModel<>();
            JList<String> listaFav = new JList<>(modeloFav);
            listaFav.setBackground(new Color(45, 45, 45));
            listaFav.setForeground(Color.WHITE);
            listaFav.setSelectionBackground(new Color(26, 115, 232));
            listaFav.setSelectionForeground(Color.WHITE);

            JScrollPane scroll = new JScrollPane(listaFav);
            scroll.getViewport().setBackground(new Color(45, 45, 45));

            JPanel topoLista = new JPanel();
            topoLista.setBackground(new Color(30, 30, 30));
            topoLista.add(titulo);
            topoLista.add(ordenacao);
            painelLista.add(topoLista, BorderLayout.NORTH);

            for (Serie s : usuario.getAssistir()) {
                modeloFav.addElement(s.getNome() + " (" + s.getStatus() + ") - " + s.getNota());
            }

            ordenacao.addActionListener(ev -> {
                String criterio = (String) ordenacao.getSelectedItem();
                List<Serie> lista = new ArrayList<>(usuario.getAssistir());
                if (criterio.equals("Nome")) lista.sort((a, b) -> a.getNome().compareTo(b.getNome()));
                else if (criterio.equals("Nota")) lista.sort((a, b) -> Double.compare(b.getNota(), a.getNota()));
                else if (criterio.equals("Status")) lista.sort((a, b) -> a.getStatus().compareTo(b.getStatus()));
                else if (criterio.equals("Data de Estreia")) lista.sort((a, b) -> a.getEstreia().compareTo(b.getEstreia()));
                modeloFav.clear();
                for (Serie s : lista) modeloFav.addElement(s.getNome() + " (" + s.getStatus() + ") - " + s.getNota());
            });

            JButton btnRemover = criarBotao("Remover");
            btnRemover.setBackground(new Color(200, 50, 50));
            btnRemover.addActionListener(ev -> {
                int index = listaFav.getSelectedIndex();
                if (index >= 0) {
                    usuario.getAssistir().remove(index);
                    modeloFav.remove(index);
                }
            });

            painelLista.add(scroll, BorderLayout.CENTER);
            painelLista.add(btnRemover, BorderLayout.SOUTH);
            conteudo.add(painelLista);
            conteudo.revalidate();
            conteudo.repaint();
        });

        lateral.add(btBuscar);
        lateral.add(Box.createVerticalStrut(8));
        lateral.add(btFavoritos);
        lateral.add(Box.createVerticalStrut(8));
        lateral.add(btAssistidos);
        lateral.add(Box.createVerticalStrut(8));
        lateral.add(btAssistir);

        add(lateral, BorderLayout.WEST);
        btBuscar.doClick();
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(26, 115, 232));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(160, 45));
        btn.setMaximumSize(new Dimension(160, 45));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 60), 1));
        return btn;
    }
}