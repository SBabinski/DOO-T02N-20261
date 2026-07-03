package tela;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.PerfilUsuario;

public class JanelaPerfil extends JDialog {

    private final JTextField campoNome;
    private boolean confirmado = false;

    public JanelaPerfil(Frame janelaPai, PerfilUsuario perfil) {
        super(janelaPai, "Perfil do Usuário", true);
        setSize(400, 230);
        setLocationRelativeTo(janelaPai);
        setResizable(false);
        getContentPane().setBackground(TemaVisual.FUNDO_PAINEL);
        setLayout(new BorderLayout(0, 0));

        JPanel cabecalho = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(TemaVisual.ACENTO);
                g.fillRect(0, 0, getWidth(), 3);
            }
        };
        cabecalho.setBackground(TemaVisual.FUNDO_CABECALHO);
        cabecalho.setBorder(new EmptyBorder(20, 26, 14, 26));

        JLabel lblTitulo = ComponentesVisuais.labelTitulo("Meu Perfil");
        cabecalho.add(lblTitulo, BorderLayout.NORTH);
        cabecalho.add(ComponentesVisuais.labelCorpo("Como você quer ser chamado?"), BorderLayout.CENTER);
        add(cabecalho, BorderLayout.NORTH);

        JPanel corpo = new JPanel();
        corpo.setLayout(new BoxLayout(corpo, BoxLayout.Y_AXIS));
        corpo.setBackground(TemaVisual.FUNDO_PAINEL);
        corpo.setBorder(new EmptyBorder(18, 26, 18, 26));

        campoNome = ComponentesVisuais.campoDeBusca(20);
        campoNome.setFont(new Font("Dialog", Font.PLAIN, 14));
        campoNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        campoNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (perfil.getNome() != null) campoNome.setText(perfil.getNome());
        campoNome.addActionListener(e -> confirmar());
        corpo.add(campoNome);
        add(corpo, BorderLayout.CENTER);

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));
        rodape.setBackground(TemaVisual.FUNDO_CABECALHO);
        rodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, TemaVisual.BORDA));

        JButton btnCancelar = ComponentesVisuais.botaoSecundario("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        JButton btnConfirmar = ComponentesVisuais.botaoPrimario("Salvar");
        btnConfirmar.addActionListener(e -> confirmar());

        rodape.add(btnCancelar);
        rodape.add(btnConfirmar);
        add(rodape, BorderLayout.SOUTH);
    }

    private void confirmar() {
        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira seu nome ou apelido.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        confirmado = true;
        dispose();
    }

    public boolean isConfirmado()   { return confirmado; }
    public String  getNomeDigitado(){ return campoNome.getText().trim(); }
}
