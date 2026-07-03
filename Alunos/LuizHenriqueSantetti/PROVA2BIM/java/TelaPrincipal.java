import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal extends JFrame {
    private DadosUsuario dados;
    private Persistencia persistencia;
    private TvMazeServico servico;
    private ArrayList<Serie> resultados = new ArrayList<Serie>();
    private JTextField campoPesquisa;
    private JButton botaoPesquisar;
    private JLabel textoStatus;
    private JLabel textoBoasVindas;
    private JTable tabelaBusca;
    private JTable tabelaFavoritos;
    private JTable tabelaAssistidas;
    private JTable tabelaQueroAssistir;
    private boolean pesquisando;

    public TelaPrincipal(DadosUsuario dados, Persistencia persistencia) {
        this.dados = dados;
        this.persistencia = persistencia;
        servico = new TvMazeServico();
        montarTela();
        atualizarTodasTabelas();
    }

    private void montarTela() {
        setTitle("Minhas Series - TVMaze");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        add(criarTopo(), BorderLayout.NORTH);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Busca", criarAbaBusca());
        abas.addTab("Favoritos", criarAbaLista(dados.getFavoritos(), "Favoritos"));
        abas.addTab("Ja Assistidas", criarAbaLista(dados.getAssistidas(), "Ja Assistidas"));
        abas.addTab("Tô querendo assistir", criarAbaLista(dados.getQueroAssistir(), "Quero Assistir"));
        add(abas, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (!persistencia.salvarDados(dados)) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Nao foi possivel salvar os dados.");
                }
            }
        });
    }

    private JPanel criarTopo() {
        JPanel painel = new JPanel(new BorderLayout());
        JPanel usuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textoBoasVindas = new JLabel("Ola, " + dados.getUsuario().getNome() + "!");
        JButton alterarNome = new JButton("Alterar nome");
        alterarNome.addActionListener(e -> alterarNome());
        usuario.add(textoBoasVindas);
        usuario.add(alterarNome);

        JPanel busca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        busca.add(new JLabel("Nome da serie:"));
        campoPesquisa = new JTextField(30);
        botaoPesquisar = new JButton("Pesquisar");
        textoStatus = new JLabel("Pronto");
        busca.add(campoPesquisa);
        busca.add(botaoPesquisar);
        busca.add(textoStatus);
        botaoPesquisar.addActionListener(e -> pesquisar());
        campoPesquisa.addActionListener(e -> pesquisar());
        painel.add(usuario, BorderLayout.NORTH);
        painel.add(busca, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarAbaBusca() {
        JPanel painel = new JPanel(new BorderLayout());
        tabelaBusca = criarTabela();
        painel.add(new JScrollPane(tabelaBusca), BorderLayout.CENTER);
        JPanel botoes = new JPanel(new FlowLayout());
        JButton detalhes = new JButton("Ver detalhes");
        JButton favorito = new JButton("Adicionar aos Favoritos");
        JButton assistida = new JButton("Marcar como Assistida");
        JButton quero = new JButton("Quero Assistir");
        detalhes.addActionListener(e -> mostrarDetalhes(selecionar(tabelaBusca, resultados)));
        favorito.addActionListener(e -> adicionarResultado(dados.getFavoritos(), "Favoritos"));
        assistida.addActionListener(e -> adicionarResultado(dados.getAssistidas(), "Ja Assistidas"));
        quero.addActionListener(e -> adicionarResultado(dados.getQueroAssistir(), "Quero Assistir"));
        botoes.add(detalhes);
        botoes.add(favorito);
        botoes.add(assistida);
        botoes.add(quero);
        painel.add(botoes, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarAbaLista(ArrayList<Serie> lista, String nomeLista) {
        JPanel painel = new JPanel(new BorderLayout());
        JTable tabela = criarTabela();
        if (nomeLista.equals("Favoritos"))
            tabelaFavoritos = tabela;
        else if (nomeLista.equals("Ja Assistidas"))
            tabelaAssistidas = tabela;
        else
            tabelaQueroAssistir = tabela;
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel controles = new JPanel(new GridLayout(2, 1));
        JPanel ordenacao = new JPanel(new FlowLayout());
        JComboBox<String> criterios = new JComboBox<String>(new String[] {
                "Nome", "Nota (maior primeiro)", "Estado", "Estreia (mais antiga)" });
        JButton ordenar = new JButton("Ordenar");
        ordenar.addActionListener(e -> {
            try {
                dados.ordenarLista(lista, (String) criterios.getSelectedItem());
                atualizarTabela(tabela, lista);
                salvarOuAvisar();
            } catch (Exception erro) {
                mostrarErro("Nao foi possivel ordenar a lista.");
            }
        });
        ordenacao.add(new JLabel("Ordenar por:"));
        ordenacao.add(criterios);
        ordenacao.add(ordenar);

        JPanel acoes = new JPanel(new FlowLayout());
        JButton detalhes = new JButton("Ver detalhes");
        JButton remover = new JButton("Remover");
        detalhes.addActionListener(e -> mostrarDetalhes(selecionar(tabela, lista)));
        remover.addActionListener(e -> removerSerie(tabela, lista));
        acoes.add(detalhes);
        acoes.add(remover);
        controles.add(ordenacao);
        controles.add(acoes);
        painel.add(controles, BorderLayout.SOUTH);
        return painel;
    }

    private JTable criarTabela() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[] { "Nome", "Idioma", "Generos", "Nota", "Estado", "Estreia" }, 0) {
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };
        JTable tabela = new JTable(modelo);
        tabela.setAutoCreateRowSorter(false);
        tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        return tabela;
    }

    private void pesquisar() {
        String nome = campoPesquisa.getText() == null ? "" : campoPesquisa.getText().trim();
        if (nome.isEmpty()) {
            mostrarErro("Digite o nome de uma serie.");
            return;
        }
        if (nome.length() > 150) {
            mostrarErro("O texto da pesquisa e muito grande.");
            return;
        }
        if (pesquisando)
            return;
        pesquisando = true;
        botaoPesquisar.setEnabled(false);
        textoStatus.setText("Pesquisando...");

        // SwingWorker executa a consulta fora da tela para a janela continuar
        // respondendo.
        SwingWorker<ArrayList<Serie>, Void> trabalho = new SwingWorker<ArrayList<Serie>, Void>() {
            protected ArrayList<Serie> doInBackground() throws Exception {
                return servico.buscarSeries(nome);
            }

            protected void done() {
                try {
                    resultados = get();
                    atualizarTabela(tabelaBusca, resultados);
                    textoStatus.setText(
                            resultados.isEmpty() ? "Nenhuma serie encontrada." : resultados.size() + " resultado(s).");
                } catch (Exception e) {
                    Throwable causa = e.getCause();
                    mostrarErro(causa == null || causa.getMessage() == null ? "Nao foi possivel consultar o TVMaze."
                            : causa.getMessage());
                    textoStatus.setText("Falha na pesquisa.");
                } finally {
                    pesquisando = false;
                    botaoPesquisar.setEnabled(true);
                }
            }
        };
        trabalho.execute();
    }

    private void adicionarResultado(ArrayList<Serie> lista, String nomeLista) {
        Serie serie = selecionar(tabelaBusca, resultados);
        if (serie == null)
            return;
        if (!dados.adicionar(lista, serie)) {
            mostrarErro("Esta serie ja esta na lista.");
            return;
        }
        atualizarTodasTabelas();
        salvarOuAvisar();
        JOptionPane.showMessageDialog(this, "Serie adicionada em " + nomeLista + ".");
    }

    private void removerSerie(JTable tabela, ArrayList<Serie> lista) {
        int linha = tabela.getSelectedRow();
        if (linha < 0 || linha >= lista.size()) {
            mostrarErro("Selecione uma serie primeiro.");
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja remover esta serie?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION)
            return;
        lista.remove(linha);
        atualizarTabela(tabela, lista);
        salvarOuAvisar();
    }

    private Serie selecionar(JTable tabela, ArrayList<Serie> lista) {
        int linha = tabela.getSelectedRow();
        if (linha < 0 || linha >= lista.size()) {
            mostrarErro("Selecione uma serie primeiro.");
            return null;
        }
        return lista.get(linha);
    }

    private void mostrarDetalhes(Serie serie) {
        if (serie == null)
            return;
        String detalhes = "Nome: " + serie.getNomeExibicao()
                + "\nIdioma: " + serie.getIdiomaExibicao()
                + "\nGeneros: " + serie.getGenerosExibicao()
                + "\nNota: " + serie.getNotaExibicao()
                + "\nEstado: " + serie.getEstadoExibicao()
                + "\nEstreia: " + serie.getDataEstreiaExibicao()
                + "\nTermino: " + serie.getDataTerminoExibicao()
                + "\nEmissora: " + serie.getEmissoraExibicao();
        JOptionPane.showMessageDialog(this, detalhes, "Detalhes da serie", JOptionPane.INFORMATION_MESSAGE);
    }

    private void alterarNome() {
        String nome = JOptionPane.showInputDialog(this, "Digite o novo nome:", dados.getUsuario().getNome());
        if (nome == null)
            return;
        if (nome.trim().isEmpty()) {
            mostrarErro("O nome nao pode ficar vazio.");
            return;
        }
        dados.getUsuario().setNome(nome);
        textoBoasVindas.setText("Ola, " + dados.getUsuario().getNome() + "!");
        salvarOuAvisar();
    }

    public void atualizarTodasTabelas() {
        try {
            atualizarTabela(tabelaBusca, resultados);
            atualizarTabela(tabelaFavoritos, dados.getFavoritos());
            atualizarTabela(tabelaAssistidas, dados.getAssistidas());
            atualizarTabela(tabelaQueroAssistir, dados.getQueroAssistir());
        } catch (Exception e) {
            mostrarErro("Nao foi possivel atualizar uma tabela.");
        }
    }

    private void atualizarTabela(JTable tabela, ArrayList<Serie> lista) {
        if (tabela == null)
            return;
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        if (lista == null)
            return;
        for (Serie serie : lista) {
            if (serie != null)
                modelo.addRow(new Object[] { serie.getNomeExibicao(), serie.getIdiomaExibicao(),
                        serie.getGenerosExibicao(), serie.getNotaExibicao(), serie.getEstadoExibicao(),
                        serie.getDataEstreiaExibicao() });
        }
    }

    private void salvarOuAvisar() {
        if (!persistencia.salvarDados(dados))
            mostrarErro("Nao foi possivel salvar os dados.");
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
