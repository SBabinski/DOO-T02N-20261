import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class TelaListas extends JFrame {

    private Usuario usuario;

    private JPanel painelCards;
    private JComboBox<String> seletorLista;
    private JComboBox<String> ordenarCombo;
    private Serie serieSelecionada;

    public TelaListas(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Minhas Listas");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(35, 35, 35));

        painelCards = new JPanel();
        painelCards.setLayout(new BoxLayout(painelCards, BoxLayout.Y_AXIS));
        painelCards.setBackground(new Color(35, 35, 35));
        painelCards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(painelCards);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        seletorLista = new JComboBox<>(new String[]{
                "Favoritos",
                "Assistidas",
                "Quero assistir"
        });

        ordenarCombo = new JComboBox<>(new String[]{
                "Nome (A-Z)",
                "Nota (maior)",
                "Status",
                "Data estreia"
        });

        JButton btnAtualizar = new JButton("Atualizar");

        styleBtn(btnAtualizar);

        JPanel topo = new JPanel();
        topo.setBackground(new Color(35, 35, 35));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Minhas Listas");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        topo.add(titulo);
        topo.add(seletorLista);
        topo.add(ordenarCombo);
        topo.add(btnAtualizar);

        JPanel rodape = new JPanel();
        rodape.setBackground(new Color(35, 35, 35));

        JButton btnFav = new JButton("Favoritos");
        JButton btnAssistidas = new JButton("Assistidas");
        JButton btnQuero = new JButton("Quero assistir");

        styleBtn(btnFav);
        styleBtn(btnAssistidas);
        styleBtn(btnQuero);

        rodape.add(btnFav);
        rodape.add(btnAssistidas);
        rodape.add(btnQuero);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        btnAtualizar.addActionListener(e -> carregar());
        seletorLista.addActionListener(e -> carregar());
        ordenarCombo.addActionListener(e -> carregar());

        btnFav.addActionListener(e -> moverSelecionado("Favoritos"));
        btnAssistidas.addActionListener(e -> moverSelecionado("Assistidas"));
        btnQuero.addActionListener(e -> moverSelecionado("Quero assistir"));

        carregar();
        setVisible(true);
    }

    private void carregar() {

        painelCards.removeAll();

        List<Serie> lista = new ArrayList<>(getListaAtual());
        ordenar(lista);

        for (Serie s : lista) {
            painelCards.add(criarCard(s));
            painelCards.add(Box.createVerticalStrut(10));
        }

        painelCards.revalidate();
        painelCards.repaint();
    }

    private List<Serie> getListaAtual() {

        String tipo = (String) seletorLista.getSelectedItem();

        if ("Favoritos".equals(tipo)) return usuario.getFavoritos();
        if ("Assistidas".equals(tipo)) return usuario.getAssistidas();
        if ("Quero assistir".equals(tipo)) return usuario.getQueroAssistir();

        return usuario.getFavoritos();
    }

    private void ordenar(List<Serie> lista) {

        String tipo = (String) ordenarCombo.getSelectedItem();

        if ("Nome (A-Z)".equals(tipo)) {
            lista.sort(Comparator.comparing(Serie::getNome));
        }

        if ("Nota (maior)".equals(tipo)) {
            lista.sort(Comparator.comparingDouble(Serie::getNota).reversed());
        }

        if ("Status".equals(tipo)) {
            lista.sort(Comparator.comparing(Serie::getStatus));
        }

        if ("Data estreia".equals(tipo)) {
            lista.sort(Comparator.comparing(Serie::getEstreia));
        }
    }

    private JPanel criarCard(Serie s) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(s.equals(serieSelecionada)
                ? new Color(60, 60, 60)
                : new Color(35, 35, 35));

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                serieSelecionada = s;
                carregar();
            }
        });

        JLabel img = new JLabel();
        img.setPreferredSize(new Dimension(110, 150));

        try {
            ImageIcon icon = new ImageIcon(new java.net.URL(s.getImagemUrl()));
            Image image = icon.getImage().getScaledInstance(110, 150, Image.SCALE_SMOOTH);
            img.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            img.setText("Sem imagem");
            img.setForeground(Color.WHITE);
        }

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(card.getBackground());

        JLabel nome = new JLabel(s.getNome());
        nome.setForeground(Color.WHITE);
        nome.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel nota = new JLabel("Nota: " + s.getNota());
        nota.setForeground(Color.LIGHT_GRAY);

        JLabel status = new JLabel("Status: " + s.getStatus());
        status.setForeground(Color.LIGHT_GRAY);

        info.add(nome);
        info.add(nota);
        info.add(status);

        card.add(img, BorderLayout.WEST);
        card.add(info, BorderLayout.CENTER);

        return card;
    }

    private void moverSelecionado(String destino) {

        if (serieSelecionada == null) return;

        usuario.getFavoritos().remove(serieSelecionada);
        usuario.getAssistidas().remove(serieSelecionada);
        usuario.getQueroAssistir().remove(serieSelecionada);

        if ("Favoritos".equals(destino)) usuario.getFavoritos().add(serieSelecionada);
        if ("Assistidas".equals(destino)) usuario.getAssistidas().add(serieSelecionada);
        if ("Quero assistir".equals(destino)) usuario.getQueroAssistir().add(serieSelecionada);

        carregar();
    }

    private void styleBtn(JButton btn) {
        btn.setBackground(new Color(60, 60, 60));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }
}