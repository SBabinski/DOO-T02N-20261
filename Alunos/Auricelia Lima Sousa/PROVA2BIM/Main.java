import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main extends JFrame {
    private JTextField campoBusca;
    private JTextArea areaResultados;
    private ServicoSerie servico;
    private GerenciadorDados gerenciador;
    private ArrayList<Serie> favoritos, assistidos, desejados, resultadosAtuais;

    public Main() {
        servico = new ServicoSerie();
        gerenciador = new GerenciadorDados();
        favoritos = gerenciador.carregarSeries("favoritos");
        assistidos = gerenciador.carregarSeries("assistidos");
        desejados = gerenciador.carregarSeries("desejados");
        resultadosAtuais = new ArrayList<>();

        String user = JOptionPane.showInputDialog("Seu nome:");
        setTitle("Minhas Séries - " + (user == null || user.isEmpty() ? "Usuario" : user));
        setSize(850, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topo = new JPanel(new FlowLayout());
        campoBusca = new JTextField(25);
        JButton btnBusca = new JButton("Buscar");
        topo.add(new JLabel("Série:")); topo.add(campoBusca); topo.add(btnBusca);

        areaResultados = new JTextArea();
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultados);

        JPanel acoes = new JPanel(new GridLayout(3, 3, 5, 5));
        JButton addFav = new JButton("Add Favorito"), addAss = new JButton("Add Assistida"), addDes = new JButton("Add Desejada");
        JButton verFav = new JButton("Ver Favoritos"), verAss = new JButton("Ver Assistidas"), verDes = new JButton("Ver Desejadas");
        JButton remFav = new JButton("Remover Favorito"), remAss = new JButton("Remover Assistida"), remDes = new JButton("Remover Desejada");

        acoes.add(addFav); acoes.add(addAss); acoes.add(addDes);
        acoes.add(verFav); acoes.add(verAss); acoes.add(verDes);
        acoes.add(remFav); acoes.add(remAss); acoes.add(remDes);

        add(topo, BorderLayout.NORTH); add(scroll, BorderLayout.CENTER); add(acoes, BorderLayout.SOUTH);

        btnBusca.addActionListener(e -> buscar());
        addFav.addActionListener(e -> adicionar(favoritos, "favoritos"));
        addAss.addActionListener(e -> adicionar(assistidos, "assistidos"));
        addDes.addActionListener(e -> adicionar(desejados, "desejados"));
        verFav.addActionListener(e -> exibir(favoritos, "Favoritos"));
        verAss.addActionListener(e -> exibir(assistidos, "Assistidas"));
        verDes.addActionListener(e -> exibir(desejados, "Desejadas"));
        remFav.addActionListener(e -> remover(favoritos, "favoritos"));
        remAss.addActionListener(e -> remover(assistidos, "assistidos"));
        remDes.addActionListener(e -> remover(desejados, "desejados"));

        setVisible(true);
    }

    public void buscar() {
        try {
            String nome = campoBusca.getText();
            if (nome.isEmpty()) throw new SerieException("Digite um nome!");
            resultadosAtuais = servico.extrairSeries(servico.buscarDados(nome));
            formatar(resultadosAtuais, "Resultados");
        } catch (SerieException ex) { 
            JOptionPane.showMessageDialog(this, ex.getMessage()); 
        }
    }

    public void adicionar(ArrayList<Serie> lista, String arq) {
        if (resultadosAtuais.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Busque por uma série primeiro!");
            return;
        }
        
        String nomeAdicionar = JOptionPane.showInputDialog(this, "Digite o nome exato da série que deseja salvar:");
        if (nomeAdicionar == null || nomeAdicionar.trim().isEmpty()) return;

        Serie encontrada = null;
        for (Serie s : resultadosAtuais) {
            if (s.getNome().trim().equalsIgnoreCase(nomeAdicionar.trim())) {
                encontrada = s;
                break;
            }
        }

        if (encontrada != null) {
            lista.add(encontrada);
            gerenciador.salvarSeries(lista, arq);
            JOptionPane.showMessageDialog(this, "\"" + encontrada.getNome() + "\" salvo com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Série não encontrada nos resultados da busca atual.");
        }
    }

    public void remover(ArrayList<Serie> lista, String arq) {
        String nome = JOptionPane.showInputDialog("Nome para remover:");
        if (nome == null) return;
        boolean removido = lista.removeIf(s -> s.getNome().trim().equalsIgnoreCase(nome.trim()));
        if (removido) {
            gerenciador.salvarSeries(lista, arq);
            JOptionPane.showMessageDialog(this, "Removido!");
            formatar(lista, "Lista Atualizada");
        }
    }

    public void exibir(ArrayList<Serie> lista, String titulo) {
        String[] opt = {"Nome", "Nota", "Estado", "Estreia"};
        int esc = JOptionPane.showOptionDialog(this, "Ordenar por:", "Filtro", 0, 1, null, opt, opt);
        if (esc == 0) lista.sort(Comparator.comparing(Serie::getNome));
        else if (esc == 1) lista.sort(Comparator.comparing(Serie::getNota));
        else if (esc == 2) lista.sort(Comparator.comparing(Serie::getEstado));
        else if (esc == 3) lista.sort(Comparator.comparing(Serie::getDataEstreia));
        formatar(lista, titulo);
    }

    public void formatar(ArrayList<Serie> lista, String titulo) {
        StringBuilder sb = new StringBuilder("--- " + titulo.toUpperCase() + " ---\n");
        for (Serie s : lista) {
            sb.append("\nNome: ").append(s.getNome())
              .append("\nIdioma: ").append(s.getIdioma())
              .append("\nGeneros: ").append(s.getGeneros())
              .append("\nNota: ").append(s.getNota())
              .append("\nStatus: ").append(s.getEstado())
              .append("\nEstreia: ").append(s.getDataEstreia())
              .append("\nTermino: ").append(s.getDataTermino())
              .append("\nEmissora: ").append(s.getEmissora())
              .append("\n---------------------------------");
        }
        areaResultados.setText(sb.toString());
    }

    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> new Main()); 
    }
}
