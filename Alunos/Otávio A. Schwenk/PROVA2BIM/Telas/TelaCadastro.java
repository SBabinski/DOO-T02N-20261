package Telas;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro {

    JPanel painelCadastro = new JPanel();
    JLabel titulo = new JLabel("Cadastrar");
    JLabel nomeTitulo = new JLabel("Usuário");
    JTextField nomeUsuario = new JTextField();
    JLabel apelidoTitulo = new JLabel("Apelido");
    JTextField apelidoUsuario = new JTextField();
    JLabel senhaTitulo = new JLabel("Senha");
    JPasswordField senhaUsuario = new JPasswordField();
    JLabel confirmarSenhaTitulo = new JLabel("Confirmar Senha");
    JPasswordField confirmarSenhaUsuario = new JPasswordField();
    JButton botaoCriarConta = new JButton("Criar Conta");
    JButton botaoVoltar = new JButton ("Voltar");
    Color corFundoClaro = new Color(229, 227, 230);
    Color corFundoBotao = new Color(101, 21, 140);

    public TelaCadastro(){
        criarTelaCadastro();
    }

    public void criarTelaCadastro(){
        painelCadastro.setBackground(corFundoClaro);
        painelCadastro.setLayout(new BoxLayout(painelCadastro, BoxLayout.Y_AXIS));
        painelCadastro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        titulo.setFont(new Font("Arial Black", Font.BOLD, 48));
        titulo.setForeground(Color.BLACK);
        titulo.setAlignmentX(titulo.CENTER_ALIGNMENT);

        nomeTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        nomeTitulo.setAlignmentX(nomeTitulo.CENTER_ALIGNMENT);
        nomeTitulo.setForeground(Color.BLACK);

        nomeUsuario.setForeground(Color.BLACK);
        nomeUsuario.setMaximumSize(new Dimension(300, 30));
        nomeUsuario.setAlignmentX(nomeUsuario.CENTER_ALIGNMENT);

        apelidoTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        apelidoTitulo.setAlignmentX(apelidoTitulo.CENTER_ALIGNMENT);
        apelidoTitulo.setForeground(Color.BLACK);

        apelidoUsuario.setForeground(Color.BLACK);
        apelidoUsuario.setMaximumSize(new Dimension(300, 30));
        apelidoUsuario.setAlignmentX(apelidoUsuario.CENTER_ALIGNMENT);

        senhaTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        senhaTitulo.setAlignmentX(senhaTitulo.CENTER_ALIGNMENT);
        senhaTitulo.setForeground(Color.BLACK);

        senhaUsuario.setForeground(Color.BLACK);
        senhaUsuario.setMaximumSize(new Dimension(300, 30));
        senhaUsuario.setAlignmentX(senhaUsuario.CENTER_ALIGNMENT);

        confirmarSenhaTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
        confirmarSenhaTitulo.setAlignmentX(confirmarSenhaTitulo.CENTER_ALIGNMENT);
        confirmarSenhaTitulo.setForeground(Color.BLACK);

        confirmarSenhaUsuario.setForeground(Color.BLACK);
        confirmarSenhaUsuario.setMaximumSize(new Dimension(300, 30));
        confirmarSenhaUsuario.setAlignmentX(confirmarSenhaUsuario.CENTER_ALIGNMENT);

        botaoCriarConta.setFocusPainted(false);
        botaoCriarConta.setBackground(corFundoBotao);
        botaoCriarConta.setForeground(Color.WHITE);
        botaoCriarConta.setMaximumSize(new Dimension(300, 70));
        botaoCriarConta.setFont(new Font("Arial Black", Font.BOLD, 24));
        botaoCriarConta.setAlignmentX(botaoCriarConta.CENTER_ALIGNMENT);

        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setMaximumSize(new Dimension(150, 35));
        botaoVoltar.setFont(new Font("Arial Black", Font.BOLD, 12));
        botaoVoltar.setAlignmentX(botaoVoltar.CENTER_ALIGNMENT);

        painelCadastro.add(titulo);
        painelCadastro.add(Box.createVerticalStrut(15));
        painelCadastro.add(nomeTitulo);
        painelCadastro.add(nomeUsuario);
        painelCadastro.add(Box.createVerticalStrut(10));
        painelCadastro.add(apelidoTitulo);
        painelCadastro.add(apelidoUsuario);
        painelCadastro.add(Box.createVerticalStrut(10));
        painelCadastro.add(senhaTitulo);
        painelCadastro.add(senhaUsuario);
        painelCadastro.add(Box.createVerticalStrut(10));
        painelCadastro.add(confirmarSenhaTitulo);
        painelCadastro.add(confirmarSenhaUsuario);
        painelCadastro.add(Box.createVerticalStrut(30));
        painelCadastro.add(botaoCriarConta);
        painelCadastro.add(Box.createVerticalStrut(25));
        painelCadastro.add(botaoVoltar);
    }

    public JPanel getPainelCadastro(){
        return painelCadastro;
    }

    public JButton getBotaoVoltar(){
        return botaoVoltar;
    }

    public JButton getBotaoCriarConta(){
        return botaoCriarConta;
    }
    
    public String getSenhaUsuario(){
        return new String(senhaUsuario.getPassword());
    }

    public String getConfirmarSenhaUsuario(){
        return new String(confirmarSenhaUsuario.getPassword());
    }

    public String getNomeUsuario(){
        return nomeUsuario.getText();
    }

    public String getApelidoUsuario(){
        return apelidoUsuario.getText();
    }

}