import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JanelaPrincipal extends JFrame {

    private final Biblioteca biblioteca;

    private final JLabel saudacao = new JLabel();
    private final PainelLista painelFavoritos;
    private final PainelLista painelAssistidas;
    private final PainelLista painelDeseja;

    public JanelaPrincipal(Biblioteca biblioteca, TVMazeClient cliente) {
        this.biblioteca = biblioteca;
        this.painelFavoritos = new PainelLista(biblioteca, TipoLista.FAVORITOS);
        this.painelAssistidas = new PainelLista(biblioteca, TipoLista.ASSISTIDAS);
        this.painelDeseja = new PainelLista(biblioteca, TipoLista.DESEJA_ASSISTIR);

        setTitle("Acompanhador de Series - TVmaze");
        setSize(980, 600);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                encerrar();
            }
        });

        add(montarCabecalho(), BorderLayout.NORTH);
        add(montarAbas(cliente), BorderLayout.CENTER);

        atualizarSaudacao();
    }

    private JPanel montarCabecalho() {
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        saudacao.setFont(saudacao.getFont().deriveFont(Font.BOLD, 16f));
        cabecalho.add(saudacao, BorderLayout.WEST);

        JPanel direita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JButton trocarNome = new JButton("Trocar nome");
        trocarNome.addActionListener(e -> trocarNome());
        direita.add(trocarNome);
        cabecalho.add(direita, BorderLayout.EAST);

        return cabecalho;
    }

    private JTabbedPane montarAbas(TVMazeClient cliente) {
        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Buscar series", new PainelBusca(biblioteca, cliente));
        abas.addTab("Favoritos", painelFavoritos);
        abas.addTab("Ja assistidas", painelAssistidas);
        abas.addTab("Quero assistir", painelDeseja);

        abas.addChangeListener(e -> {
            java.awt.Component atual = abas.getSelectedComponent();
            if (atual instanceof PainelLista) {
                ((PainelLista) atual).atualizar();
            }
        });

        return abas;
    }

    private void atualizarSaudacao() {
        saudacao.setText("Ola, " + biblioteca.getUsuario().getNome() + "!");
    }

    private void trocarNome() {
        String atual = biblioteca.getUsuario().getNome();
        String novo = JOptionPane.showInputDialog(this,
                "Como voce gostaria de ser chamado(a)?",
                atual);
        if (novo == null) {
            return;
        }
        biblioteca.getUsuario().setNome(novo);
        try {
            biblioteca.salvar();
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this,
                    "Nome alterado, mas houve erro ao salvar:\n" + ex.getMessage(),
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        atualizarSaudacao();
    }

    private void encerrar() {
        try {
            biblioteca.salvar();
        } catch (PersistenciaException ex) {
            int opcao = JOptionPane.showConfirmDialog(this,
                    "Nao foi possivel salvar os dados:\n" + ex.getMessage()
                            + "\n\nDeseja sair mesmo assim?",
                    "Erro ao salvar", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (opcao != JOptionPane.YES_OPTION) {
                return;
            }
        }
        dispose();
        System.exit(0);
    }
}