package Telas;

import Objetos.Usuario;
import Service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin {
    
    JFrame tela = new JFrame();
    CardLayout cardLayout = new CardLayout();
    JPanel fundo = new JPanel(cardLayout);
    JPanel painelLogin = new JPanel();
    JLabel titulo = new JLabel("Login");
    JLabel nomeTitulo = new JLabel("Usuário");
    JTextField nomeUsuario = new JTextField();
    JLabel senhaTitulo = new JLabel("Senha");
    JPasswordField senhaUsuario = new JPasswordField();
    JButton botaoEntrar = new JButton("Entrar");
    JButton botaoCadastrar = new JButton("Clique aqui para cadastrar-se!");
    JButton botaoSair = new JButton ("Sair");

    TelaCadastro telaCadastro = new TelaCadastro();
    private UsuarioService usuarioService=new UsuarioService();

    Color corFundoClaro = new Color(220, 175, 250);
    Color corFundoBotao = new Color(101, 21, 140);

    public TelaLogin(){
        criarTelaLogin();
        configurarBotoes();
    }

    public void criarTelaLogin(){
        tela.setTitle("Login");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(500, 600);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);

        painelLogin.setBackground(corFundoClaro);
        painelLogin.setLayout(new BoxLayout(painelLogin, BoxLayout.Y_AXIS));
        painelLogin.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        titulo.setFont(new Font("Arial Black", Font.BOLD, 48));
        titulo.setForeground(Color.BLACK);
        titulo.setAlignmentX(titulo.CENTER_ALIGNMENT);

        nomeTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        nomeTitulo.setAlignmentX(nomeTitulo.CENTER_ALIGNMENT);
        nomeTitulo.setForeground(Color.BLACK);

        nomeUsuario.setForeground(Color.BLACK);
        nomeUsuario.setMaximumSize(new Dimension(300, 30));
        nomeUsuario.setAlignmentX(nomeUsuario.CENTER_ALIGNMENT);

        senhaTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        senhaTitulo.setAlignmentX(senhaTitulo.CENTER_ALIGNMENT);
        senhaTitulo.setForeground(Color.BLACK);

        senhaUsuario.setForeground(Color.BLACK);
        senhaUsuario.setMaximumSize(new Dimension(300, 30));
        senhaUsuario.setAlignmentX(senhaUsuario.CENTER_ALIGNMENT);

        botaoEntrar.setFocusPainted(false);
        botaoEntrar.setBackground(corFundoBotao);
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setMaximumSize(new Dimension(300, 70));
        botaoEntrar.setFont(new Font("Arial Black", Font.BOLD, 24));
        botaoEntrar.setAlignmentX(botaoEntrar.CENTER_ALIGNMENT);

        botaoCadastrar.setContentAreaFilled(false);
        botaoCadastrar.setBorderPainted(false);
        botaoCadastrar.setFocusPainted(false);
        botaoCadastrar.setOpaque(false);
        botaoCadastrar.setFont(new Font("Arial Black", Font.BOLD, 12));
        botaoCadastrar.setAlignmentX(botaoCadastrar.CENTER_ALIGNMENT);
        botaoCadastrar.setForeground(Color.BLUE);

        botaoSair.setFocusPainted(false);
        botaoSair.setBackground(Color.RED);
        botaoSair.setForeground(Color.WHITE);
        botaoSair.setMaximumSize(new Dimension(150, 35));
        botaoSair.setFont(new Font("Arial Black", Font.BOLD, 12));
        botaoSair.setAlignmentX(botaoSair.CENTER_ALIGNMENT);

        fundo.add(painelLogin, "login");
        fundo.add(telaCadastro.getPainelCadastro(), "cadastro");
        tela.add(fundo);
        cardLayout.show(fundo, "login");
        painelLogin.add(titulo);
        painelLogin.add(Box.createVerticalStrut(50));
        painelLogin.add(nomeTitulo);
        painelLogin.add(nomeUsuario);
        painelLogin.add(Box.createVerticalStrut(35));
        painelLogin.add(senhaTitulo);
        painelLogin.add(senhaUsuario);
        painelLogin.add(Box.createVerticalStrut(50));
        painelLogin.add(botaoEntrar);
        painelLogin.add(botaoCadastrar);
        painelLogin.add(Box.createVerticalStrut(25));
        painelLogin.add(botaoSair);
        tela.setVisible(true);
    }

    public void configurarBotoes(){

        //configura a animação do botão que direciona para a tela de cadastro
        botaoCadastrar.addMouseListener(new java.awt.event.MouseAdapter(){

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                botaoCadastrar.setForeground(Color.GRAY);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                botaoCadastrar.setForeground(Color.BLUE);
            }
        });

        //configura o botão que encerra o sistema
        botaoSair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        //configura o botão que direciona para a tela de cadastro
        botaoCadastrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(fundo, "cadastro");
            }
        });

        //configura o botão que permite entrar na conta
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeUsuario.getText();

                    char[] senhaArray = senhaUsuario.getPassword();
                    String senha = new String(senhaArray).trim();
                    String senhaBase64 = java.util.Base64.getEncoder()
                            .encodeToString(senha.getBytes());

                    Usuario usuario = usuarioService.buscarUsuario(nome, senhaBase64);

                    JOptionPane.showMessageDialog(
                            tela,
                            "Login realizado com sucesso",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    tela.dispose();
                    new TelaPrincipal(usuario);

                } catch (RuntimeException erro) {
                    erro.printStackTrace();

                    JOptionPane.showMessageDialog(
                        tela,
                        erro.toString(),
                        "Erro no login",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        //configura o botão de voltar que está na classe TelaCadastro
        telaCadastro.getBotaoVoltar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(fundo, "login");
            }
        });

        //configura o botão para criar conta que está na class TelaCadastro
        telaCadastro.getBotaoCriarConta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{

                    if(!telaCadastro.getSenhaUsuario().equals(telaCadastro.getConfirmarSenhaUsuario())){
                        throw new SenhaDivergenteException();
                    }

                    Usuario usuario=new Usuario(
                        telaCadastro.getNomeUsuario(),
                        telaCadastro.getApelidoUsuario(),
                        telaCadastro.getSenhaUsuario()
                    );

                    usuarioService.salvarUsuario(usuario);

                    JOptionPane.showMessageDialog(
                        tela,
                        "Usuário criado com sucesso",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                }catch(SenhaDivergenteException erro){

                    JOptionPane.showMessageDialog(
                        tela,
                        erro.getMessage(),
                        "Erro de senha",
                        JOptionPane.WARNING_MESSAGE
                    );

                }catch(IllegalArgumentException erro){

                    JOptionPane.showMessageDialog(
                        tela,
                        erro.getMessage(),
                        "Erro de validação",
                        JOptionPane.WARNING_MESSAGE
                    );

                }catch(RuntimeException erro){

                    JOptionPane.showMessageDialog(
                        tela,
                        erro.getMessage(),
                        "Erro ao salvar",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

    }
}
