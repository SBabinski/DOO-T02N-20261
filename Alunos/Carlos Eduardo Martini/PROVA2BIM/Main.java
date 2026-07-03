import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JPanel menuAberto = new JPanel();
    static BuscaSeries buscador = new BuscaSeries();
    static GerenceJson gerenciador = new GerenceJson();
    static Checkpoints save = new Checkpoints();
    static String usuarioLogado = "";
    static String telaAtual = "login";

    public static void main(String[] args) {
        Color corFundo = Color.decode("#222632"); // paleta de cores:
        Color azulDetalhes = Color.decode("#1a1b24");
        Color brancoDif = Color.decode("#9499a1");

        Font fontTitulo = new Font("Arial", Font.BOLD, 80);// fontes
        Font fontText = new Font("Arial", Font.BOLD, 20);

        JFrame JANELA = new JFrame("Sistema de Séries");
        JANELA.setSize(900, 600);
        JANELA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelPrincipal = new JPanel();
        JANELA.add(painelPrincipal);
        painelPrincipal.setBackground(corFundo);
        painelPrincipal.setLayout(null);

        JPanel barSupe = new JPanel();
        barSupe.setBackground(azulDetalhes);
        barSupe.setBounds(0, 0, 900, 50);

        String usuarioSalvo = carregarUsuarioLocal();

        if (!usuarioSalvo.isEmpty()) {
            telaInicio(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA, usuarioSalvo);
            abreMenu(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA);
        } else {
            telaLogin(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA);
        }
    }

    public static void telaLogin(Color brancoDif, Color azulDetalhes, Font fontTitulo, Font fontText,
            JPanel painelPrincipal, JPanel barSupe, JFrame JANELA) {
        JLabel BemVindo = new JLabel("BEM VINDO!");
        BemVindo.setForeground(brancoDif);
        BemVindo.setFont(fontTitulo);
        BemVindo.setBounds(205, 95, 1000, 100);

        JLabel comoChamar = new JLabel("Como devemos te chamar?");
        comoChamar.setForeground(brancoDif);
        comoChamar.setFont(fontText);
        comoChamar.setBounds(335, 215, 400, 30);

        JTextField nomeAqui = new JTextField(150);
        nomeAqui.setForeground(brancoDif);
        nomeAqui.setBackground(azulDetalhes);
        nomeAqui.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        nomeAqui.setBounds(200, 265, 500, 40);

        JButton botaoPronto = new JButton("PRONTO");
        botaoPronto.setForeground(brancoDif);
        botaoPronto.setBackground(azulDetalhes);
        botaoPronto.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botaoPronto.setFocusPainted(false);
        botaoPronto.setBounds(345, 315, 200, 30);

        botaoPronto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDigitado = nomeAqui.getText();

                if (!nomeDigitado.trim().isEmpty()) {
                    salvarUsuarioLocal(nomeDigitado);

                    painelPrincipal.removeAll();
                    painelPrincipal.repaint();

                    telaInicio(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA,
                            nomeDigitado);
                    abreMenu(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA);
                } else {
                    JOptionPane.showMessageDialog(JANELA, "Por favor, digite um nome!");
                }
            }
        });

        painelPrincipal.add(barSupe);
        painelPrincipal.add(BemVindo);
        painelPrincipal.add(comoChamar);
        painelPrincipal.add(nomeAqui);
        painelPrincipal.add(botaoPronto);

        JANELA.setVisible(true);// deixar no final
    }

    public static void telaInicio(Color brancoDif, Color azulDetalhes, Font fontTitulo, Font fontText,
            JPanel painelPrincipal, JPanel barSupe, JFrame JANELA, String nomeUsuario) {
        barSupe.setLayout(null);

        usuarioLogado = nomeUsuario;
        telaAtual = "inicio";

        ImageIcon iconeMenu = new ImageIcon("Menu.png");
        JButton botMenu = new JButton(iconeMenu);
        botMenu.setContentAreaFilled(false);
        botMenu.setBorderPainted(false);
        botMenu.setFocusPainted(false);
        botMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botMenu.setBounds(15, 5, 40, 40);

        botMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!telaAtual.equals("inicio")) {
                    painelPrincipal.removeAll();
                    painelPrincipal.repaint();

                    telaInicio(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA,
                            usuarioLogado);
                    abreMenu(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA);
                } else {
                    menuAberto.setVisible(!menuAberto.isVisible());
                }
            }
        });

        painelPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (menuAberto.isVisible()) {
                    menuAberto.setVisible(false);
                }
            }
        });

        JTextField pesquisAqui = new JTextField(150);
        pesquisAqui.setBackground(Color.WHITE);
        pesquisAqui.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pesquisAqui.setBounds(200, 5, 500, 40);

        pesquisAqui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoBusca = pesquisAqui.getText();

                if (!textoBusca.trim().isEmpty()) {

                    System.out.println("Buscando na API: " + textoBusca);

                    try {
                        String jsonBruto = buscador.buscaApi(textoBusca);

                        String nome = gerenciador.extraCampo(jsonBruto, "name");
                        String idioma = gerenciador.extraCampo(jsonBruto, "language");
                        String estado = gerenciador.extraCampo(jsonBruto, "status");
                        String estreia = gerenciador.extraCampo(jsonBruto, "premiered");
                        String termino = gerenciador.extraCampo(jsonBruto, "ended");
                        String nota = gerenciador.extraiNota(jsonBruto);
                        String emissora = gerenciador.extraiEmissora(jsonBruto);
                        String generos = gerenciador.extraiGeneros(jsonBruto);
                        String urlPoster = gerenciador.extraiImagem(jsonBruto);

                        Serie serieEncontrada = new Serie(nome, idioma, generos, nota, estado, estreia, termino,
                                emissora, urlPoster);

                        telaDetalhes(brancoDif, azulDetalhes, fontTitulo, fontText, painelPrincipal, barSupe, JANELA,
                                serieEncontrada);

                        pesquisAqui.setText("");

                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(JANELA, "Série não encontrada ou erro na conexão.");
                    }
                }
            }
        });

        JLabel usuario = new JLabel(nomeUsuario);
        usuario.setForeground(Color.WHITE);
        usuario.setFont(fontText);
        usuario.setBounds(770, 10, 200, 30);

        barSupe.add(botMenu);
        barSupe.add(pesquisAqui);
        barSupe.add(usuario);

        painelPrincipal.add(barSupe);

        JLabel tituloSecao = new JLabel("sugestões");
        tituloSecao.setForeground(brancoDif);
        tituloSecao.setFont(fontText);
        tituloSecao.setBounds(50, 80, 200, 30);
        painelPrincipal.add(tituloSecao);

        String[] nomesDasSeries = {
                "the walking dead", "supernatural", "the boys", "the flash", "invencivel",
                "loki", "rick and morty", "jojo", "stranger things", "breaking bad"
        };

        

        int colunasMaximas = 5;
        int inicioX = 50;
        int inicioY = 130;

        for (int i = 0; i < nomesDasSeries.length; i++) {
            int linha = i / colunasMaximas;
            int coluna = i % colunasMaximas;

            int x = inicioX + (coluna * 150);
            int y = inicioY + (linha * 250);

            String nomeSerie = nomesDasSeries[i];

            try {
                String jsonBruto = buscador.buscaApi(nomeSerie);
                
                String urlPosterReal = gerenciador.extraiImagem(jsonBruto);
                String nomeOficialReal = gerenciador.extraCampo(jsonBruto, "name");

                if (!urlPosterReal.equals("nao encontrado")) {
                    addPoster(painelPrincipal, x, y, urlPosterReal, nomeOficialReal, fontText, brancoDif);
                } else {
                    addPoster(painelPrincipal, x, y, "", nomeOficialReal, fontText, brancoDif);
                }

            } catch (Exception e) {
                System.out.println("Erro ao carregar sugestão da API para: " + nomeSerie);
                addPoster(painelPrincipal, x, y, "", nomeSerie, fontText, brancoDif);
            }
        }

        JANELA.setVisible(true);
    }

    public static void addPoster(JPanel painel, int posX, int posY, String urlImagem, String tituloSerie, Font fonte,
            Color brancoDif) {
        try {
            URL url = new URL(urlImagem);

            ImageIcon iconeOriginal = new ImageIcon(url);

            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(130, 180, Image.SCALE_SMOOTH);
            JLabel labelPoster = new JLabel(new ImageIcon(imagemRedimensionada));
            labelPoster.setBounds(posX, posY, 130, 180);

            JLabel labelTitulo = new JLabel(tituloSerie);
            labelTitulo.setForeground(brancoDif);
            labelTitulo.setFont(fonte);
            labelTitulo.setBounds(posX, posY + 190, 130, 20);

            painel.add(labelPoster);
            painel.add(labelTitulo);

        } catch (Exception e) {
            System.out.println("Nota: Link de imagem falhou para: " + tituloSerie);

            JPanel posterErro = new JPanel();
            posterErro.setBackground(Color.DARK_GRAY);
            posterErro.setBounds(posX, posY, 130, 180);
            painel.add(posterErro);

            JLabel labelTitulo = new JLabel(tituloSerie);
            labelTitulo.setForeground(brancoDif);
            labelTitulo.setFont(fonte);
            labelTitulo.setBounds(posX, posY + 190, 130, 20);
            painel.add(labelTitulo);
        }
    }

    public static void abreMenu(Color whites, Color azulDetalhes, Font fontTitulo, Font fontText,
            JPanel painelPrincipal, JPanel barSupe, JFrame JANELA) {

        menuAberto.setBackground(azulDetalhes);
        menuAberto.setBounds(0, 50, 200, 900);
        menuAberto.setLayout(null);
        menuAberto.setVisible(false);

        String[] nomesBotoes = { "FAVORITOS", "ASSISTIDAS", "ASSISTIR MAIS TARDE" };
        int posicaoY = 20;

        for (int i = 0; i < nomesBotoes.length; i++) {
            JButton botaoMenu = new JButton(nomesBotoes[i]);

            botaoMenu.setContentAreaFilled(false);
            botaoMenu.setBorderPainted(false);
            botaoMenu.setFocusPainted(false);

            botaoMenu.setForeground(whites);
            botaoMenu.setHorizontalAlignment(SwingConstants.LEFT);
            botaoMenu.setFont(new Font("Arial", Font.BOLD, 14));
            botaoMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            botaoMenu.setBounds(0, posicaoY, 200, 40);

            String nomeDoBotaoClicado = nomesBotoes[i];

            botaoMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuAberto.setVisible(false);

                    System.out.println("Abrindo a lista de: " + nomeDoBotaoClicado);

                    painelPrincipal.removeAll();

                    painelPrincipal.add(barSupe);
                    painelPrincipal.add(menuAberto);
                    painelPrincipal.setComponentZOrder(menuAberto, 0);

                    JLabel tituloLista = new JLabel(nomeDoBotaoClicado);
                    tituloLista.setForeground(whites);
                    tituloLista.setFont(fontText);
                    tituloLista.setBounds(50, 80, 400, 30);
                    painelPrincipal.add(tituloLista);

                    String[] opcoesFiltro = { "Ordem Alfabética", "Nota", "Estado", "Data de Estreia" };
                    JComboBox<String> comboFiltro = new JComboBox<>(opcoesFiltro);
                    comboFiltro.setBounds(500, 80, 180, 30);
                    painelPrincipal.add(comboFiltro);

                    java.util.ArrayList<Serie> seriesDaLista = save.lerLista(nomeDoBotaoClicado);

                    JPanel painelItens = new JPanel();
                    painelItens.setLayout(null);
                    painelItens.setBackground(painelPrincipal.getBackground());
                    painelItens.setBounds(0, 130, 900, 450);
                    painelPrincipal.add(painelItens);

                    comboFiltro.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ev) {
                            String filtroSelecionado = (String) comboFiltro.getSelectedItem();

                            if (filtroSelecionado.equals("Ordem Alfabética")) {
                                java.util.Collections.sort(seriesDaLista,
                                        (s1, s2) -> s1.getNomeSerie().compareToIgnoreCase(s2.getNomeSerie()));
                            } else if (filtroSelecionado.equals("Nota")) {
                                java.util.Collections.sort(seriesDaLista,
                                        (s1, s2) -> s2.getNotGeral().compareTo(s1.getNotGeral())); // Maior nota
                                                                                                   // primeiro
                            } else if (filtroSelecionado.equals("Estado")) {
                                java.util.Collections.sort(seriesDaLista,
                                        (s1, s2) -> s1.getEstado().compareToIgnoreCase(s2.getEstado()));
                            } else if (filtroSelecionado.equals("Data de Estreia")) {
                                java.util.Collections.sort(seriesDaLista,
                                        (s1, s2) -> s1.getDataEstreia().compareTo(s2.getDataEstreia()));
                            }

                            atualizarListaTela(painelItens, seriesDaLista, whites, nomeDoBotaoClicado);
                        }
                    });

                    java.util.Collections.sort(seriesDaLista,
                            (s1, s2) -> s1.getNomeSerie().compareToIgnoreCase(s2.getNomeSerie()));
                    atualizarListaTela(painelItens, seriesDaLista, whites, nomeDoBotaoClicado);

                    painelPrincipal.repaint();
                    painelPrincipal.revalidate();
                }
            });

            menuAberto.add(botaoMenu);

            posicaoY += 35;
        }
        painelPrincipal.add(menuAberto);
        painelPrincipal.setComponentZOrder(menuAberto, 0);
        painelPrincipal.repaint();
        painelPrincipal.revalidate();
    }

    public static void telaDetalhes(Color brancoDif, Color azulDetalhes, Font fontTitulo, Font fontText,
            JPanel painelPrincipal, JPanel barSupe, JFrame JANELA, Serie serie) {

        telaAtual = "detalhes";

        painelPrincipal.removeAll();
        painelPrincipal.add(barSupe);

        Font fonteTituloLocal = new Font("Arial", Font.BOLD, 42);
        Font fonteTextoLocal = new Font("Arial", Font.PLAIN, 16);

        JLabel poster = new JLabel();
        poster.setBounds(50, 100, 220, 320);
        poster.setBackground(Color.DARK_GRAY);
        poster.setOpaque(true);

        try {
            if (!serie.getUrlPoster().equals("nao encontrado")) {
                java.net.URL url = new java.net.URL(serie.getUrlPoster());
                ImageIcon iconeOriginal = new ImageIcon(url);
                java.awt.Image imgRedimensionada = iconeOriginal.getImage().getScaledInstance(220, 320,
                        java.awt.Image.SCALE_SMOOTH);
                poster.setIcon(new ImageIcon(imgRedimensionada));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar o pôster da internet.");
        }
        painelPrincipal.add(poster);

        JLabel labelNota = new JLabel("Nota: " + serie.getNotGeral());
        labelNota.setForeground(brancoDif);
        labelNota.setFont(fonteTextoLocal);
        labelNota.setHorizontalAlignment(SwingConstants.CENTER);
        labelNota.setBounds(50, 430, 220, 25);
        painelPrincipal.add(labelNota);

        JLabel titulo = new JLabel(serie.getNomeSerie());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(fonteTituloLocal);
        titulo.setBounds(300, 95, 360, 50);
        painelPrincipal.add(titulo);

        String[] infos = {
                "Idioma: " + serie.getIdioma(),
                "Gêneros: " + serie.getGeneros(),
                "Estado: " + serie.getEstado(),
                "Período: " + serie.getEmissora() + " até " + serie.getDataEstreia(),
                "Streaming: " + serie.getDataTermino()
        };

        int posicaoY = 160;
        for (String info : infos) {
            JLabel labelInfo = new JLabel(info);
            labelInfo.setForeground(brancoDif);
            labelInfo.setFont(fonteTextoLocal);
            labelInfo.setBounds(300, posicaoY, 360, 25);
            painelPrincipal.add(labelInfo);
            posicaoY += 35;
        }

        JButton botFavorito = new JButton("Favoritar");
        botFavorito.setBounds(680, 160, 160, 30);
        botFavorito.setFont(fonteTextoLocal);
        botFavorito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adicionando aos Favoritos: " + serie.getNomeSerie());

                save.salvarNaLista(serie, "Favoritos");

                JOptionPane.showMessageDialog(JANELA, serie.getNomeSerie() + " adicionada aos Favoritos!");
            }
        });
        painelPrincipal.add(botFavorito);

        JButton botAssistido = new JButton("Já Assisti");
        botAssistido.setBounds(680, 205, 160, 30);
        botAssistido.setFont(fonteTextoLocal);
        botAssistido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adicionando às Assistidas: " + serie.getNomeSerie());

                save.salvarNaLista(serie, "Assistidas");

                JOptionPane.showMessageDialog(JANELA, serie.getNomeSerie() + " marcada como Assistida!");
            }
        });
        painelPrincipal.add(botAssistido);

        JButton botMaisTarde = new JButton("Ver Mais Tarde...");
        botMaisTarde.setBounds(680, 250, 160, 30);
        botMaisTarde.setFont(fonteTextoLocal);
        botMaisTarde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Adicionando para Ver Mais Tarde: " + serie.getNomeSerie());

                save.salvarNaLista(serie, "Assistir Mais Tarde");

                JOptionPane.showMessageDialog(JANELA, serie.getNomeSerie() + " adicionada para Ver Mais Tarde!");
            }
        });
        painelPrincipal.add(botMaisTarde);

        painelPrincipal.repaint();
        painelPrincipal.revalidate();
    }

    private static void atualizarListaTela(JPanel painelItens, java.util.ArrayList<Serie> lista, Color whites, String tipoLista) {
        painelItens.removeAll();
        int itemY = 0;

        if (lista.isEmpty()) {
            JLabel avisoVazio = new JLabel("Nenhuma série adicionada ainda.");
            avisoVazio.setForeground(Color.GRAY);
            avisoVazio.setFont(new Font("Arial", Font.ITALIC, 16));
            avisoVazio.setBounds(50, itemY, 400, 25);
            painelItens.add(avisoVazio);
        } else {
            for (Serie serie : lista) {
                String textoLinha = "•  " + serie.getNomeSerie().toUpperCase() + 
                                    "  (⭐ " + serie.getNotGeral() + 
                                    " | Status: " + serie.getEstado() + ")";
                                    
                JButton btnItem = new JButton(textoLinha);
                btnItem.setForeground(whites);
                btnItem.setFont(new Font("Arial", Font.PLAIN, 16));
                btnItem.setBounds(50, itemY, 700, 25);
                btnItem.setHorizontalAlignment(SwingConstants.LEFT);
                
                btnItem.setContentAreaFilled(false);
                btnItem.setBorderPainted(false);
                btnItem.setFocusPainted(false);
                btnItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                btnItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Pergunta se quer mesmo remover
                        int resposta = JOptionPane.showConfirmDialog(painelItens, 
                            "Deseja remover '" + serie.getNomeSerie() + "' da lista?", 
                            "Remover Série", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.WARNING_MESSAGE);
                            
                        if (resposta == JOptionPane.YES_OPTION) {
                            lista.remove(serie); 
                            save.reescreverLista(lista, tipoLista); 
                            atualizarListaTela(painelItens, lista, whites, tipoLista); 
                        }
                    }
                });

                painelItens.add(btnItem);
                itemY += 35;
            }
        }
        painelItens.repaint();
        painelItens.revalidate();
    }

    private static void salvarUsuarioLocal(String nome) {
        try {
            java.io.FileWriter escritor = new java.io.FileWriter("usuario.txt");
            escritor.write(nome);
            escritor.close();
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar usuário local: " + e.getMessage());
        }
    }

    private static String carregarUsuarioLocal() {
        try {
            java.io.File arquivo = new java.io.File("usuario.txt");
            if (arquivo.exists()) {
                java.io.BufferedReader leitor = new java.io.BufferedReader(new java.io.FileReader(arquivo));
                String nome = leitor.readLine();
                leitor.close();
                return nome != null ? nome.trim() : "";
            }
        } catch (java.io.IOException e) {
            System.out.println("Erro ao carregar usuário local: " + e.getMessage());
        }
        return "";
    }
}