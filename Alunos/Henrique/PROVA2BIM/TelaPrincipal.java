package fag;

import javax.swing.*;
import java.util.List;
import java.awt.*;

public class TelaPrincipal extends JFrame{
	
	JTextField campo = new JTextField(20);
	Usuario usuario;
	
	public TelaPrincipal() {
	    setTitle("Minhas Séries");
	    setSize(800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);

	    JPanel painel = new JPanel();
	    painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
	    painel.setBackground(new Color(30, 30, 30));
	    painel.setBorder(BorderFactory.createEmptyBorder(100, 200, 300, 200));

	    JLabel titulo = new JLabel("Minhas Séries", SwingConstants.CENTER);
	    titulo.setFont(new Font("Arial", Font.BOLD, 28));
	    titulo.setForeground(Color.WHITE);
	    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel label = new JLabel("Digite seu nome:");
	    label.setFont(new Font("Arial", Font.BOLD, 14));
	    label.setForeground(Color.WHITE);
	    label.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JTextField campo = new JTextField(20);
	    campo.setMaximumSize(new Dimension(300, 35));
	    campo.setFont(new Font("Arial", Font.PLAIN, 14));
	    campo.setBackground(new Color(60, 60, 60));
	    campo.setForeground(Color.WHITE);
	    campo.setCaretColor(Color.WHITE);

	    JButton botao = new JButton("Entrar");
	    botao.setBackground(new Color(26, 115, 232));
	    botao.setForeground(Color.WHITE);
	    botao.setFont(new Font("Arial", Font.BOLD, 14));
	    botao.setMaximumSize(new Dimension(300, 40));
	    botao.setFocusPainted(false);
	    botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    botao.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    campo.addActionListener(e -> botao.doClick());

	    painel.add(titulo);
	    painel.add(Box.createVerticalStrut(30));
	    painel.add(label);
	    painel.add(Box.createVerticalStrut(10));
	    painel.add(campo);
	    painel.add(Box.createVerticalStrut(15));
	    painel.add(botao);

	    botao.addActionListener(e -> {
	        String nome = campo.getText().trim();
	        if (!nome.isEmpty()) {
	            Usuario carregado = Persistencia.carregar();
	            if (carregado != null && carregado.getNome().equals(nome)) {
	                usuario = carregado;
	            } else {
	                usuario = new Usuario(nome);
	                TvMaze service = new TvMaze();
	                List<Serie> breaking = service.buscarSerie("breaking bad");
	                if (!breaking.isEmpty()) usuario.getFavoritos().add(breaking.get(0));
	                List<Serie> friends = service.buscarSerie("friends");
	                if (!friends.isEmpty()) usuario.getAssistidos().add(friends.get(0));
	                List<Serie> witcher = service.buscarSerie("the witcher");
	                if (!witcher.isEmpty()) usuario.getAssistir().add(witcher.get(0));
	            }
	            JOptionPane.showMessageDialog(null, "Bem vindo, " + nome + "!");
	            dispose();
	            TelaMenu menu = new TelaMenu(usuario);
	            menu.setVisible(true);
	        } else {
	            JOptionPane.showMessageDialog(null, "Digite seu nome!");
	        }
	    });

	    add(painel);
	}
	
	public static void main(String[] args) {
		TelaPrincipal tela = new TelaPrincipal();
		tela.setVisible(true);
		
		
		
	}
	
}
