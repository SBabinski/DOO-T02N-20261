package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Diálogo exibido na primeira execução para cadastrar o nome do usuário.
 */
public class SetupDialog extends JDialog {

    private static final Color BG       = new Color(30, 30, 46);
    private static final Color SURFACE  = new Color(49, 50, 68);
    private static final Color PURPLE   = new Color(203, 166, 247);
    private static final Color BLUE     = new Color(137, 180, 250);
    private static final Color TEXT     = Color.WHITE;
    private static final Color SUBTEXT  = new Color(166, 173, 200);

    private JTextField txtNome;
    private String nomeUsuario = "Usuário";

    public SetupDialog(Frame parent) {
        super(parent, "Bem-vindo ao SeriesTracker", true);
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(420, 240);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 16));
        painel.setBackground(BG);
        painel.setBorder(BorderFactory.createEmptyBorder(24, 36, 20, 36));

        // ── Título ────────────────────────────────────────────────────────────
        JLabel lblTitulo = new JLabel("SeriesTracker", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(PURPLE);
        painel.add(lblTitulo, BorderLayout.NORTH);

        // ── Formulário ────────────────────────────────────────────────────────
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        JLabel lblPergunta = new JLabel("Como podemos te chamar?");
        lblPergunta.setForeground(SUBTEXT);
        lblPergunta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(lblPergunta, gbc);

        txtNome = new JTextField();
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNome.setBackground(SURFACE);
        txtNome.setForeground(TEXT);
        txtNome.setCaretColor(TEXT);
        txtNome.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(88, 91, 112)),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        gbc.gridy = 1;
        form.add(txtNome, gbc);
        painel.add(form, BorderLayout.CENTER);

        // ── Botão ─────────────────────────────────────────────────────────────
        JButton btnComecar = criarBotao("Começar", BLUE);
        btnComecar.addActionListener(e -> confirmar());
        txtNome.addActionListener(e -> confirmar());

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rodape.setBackground(BG);
        rodape.add(btnComecar);
        painel.add(rodape, BorderLayout.SOUTH);

        add(painel);

        // Fechar janela sem confirmar usa o nome padrão
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                nomeUsuario = "Usuário";
                dispose();
            }
        });

        txtNome.requestFocusInWindow();
    }

    private void confirmar() {
        String nome = txtNome.getText().trim();
        nomeUsuario = nome.isEmpty() ? "Usuário" : nome;
        dispose();
    }

    private JButton criarBotao(String texto, Color fundo) {
        JButton btn = new JButton(texto);
        btn.setBackground(fundo);
        btn.setForeground(new Color(30, 30, 46));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 36));
        return btn;
    }

    /** Retorna o nome digitado pelo usuário (nunca null). */
    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
