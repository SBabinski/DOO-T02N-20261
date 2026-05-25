import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class PrevTemp {

    //RESULTADOS DAS PESQUISAS
    static JLabel condicao = new JLabel("Condição: -");
    static JLabel temperatura = new JLabel("Temp: -");
    static JLabel tempMax = new JLabel("Max: -");
    static JLabel tempMin = new JLabel("Min: -");
    static JLabel Umidade = new JLabel("Umidade: -");
    static JLabel Vento = new JLabel("Vento: -");

    public static void main(String[] args) {
        //CONFIGURAÇÃO DA JANELA
        JFrame janela = new JFrame("Meu App Clima");
        
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //DENTRO DA JANELA
        ImageIcon imagNuvem = new ImageIcon("fundopreto.jpg");
        JLabel fundoTela = new JLabel(imagNuvem);//Imagem de fundo
        janela.setContentPane(fundoTela);

        fundoTela.setLayout(new GridLayout(9, 1, 5, 5));
        JLabel instrucao = new JLabel("Digite a cidade alvo:");
        JTextField campoCidede = new JTextField(15);//campo de digitação
        JButton botaoBuscar = new JButton("🔍");//botao para buscar

        Font fonte = new Font("SansSerif", Font.BOLD, 22);
        Font fonteTitulo = new Font("SansSerif", Font.BOLD, 30);
        instrucao.setFont(fonteTitulo);
        instrucao.setForeground(Color.WHITE);

        condicao.setFont(fonte);
        condicao.setForeground(Color.WHITE);
        temperatura.setFont(fonte);
        temperatura.setForeground(Color.WHITE);//muda a cor e a fonte das letras
        tempMax.setFont(fonte);
        tempMax.setForeground(Color.WHITE);
        tempMin.setFont(fonte);
        tempMin.setForeground(Color.WHITE);
        Umidade.setFont(fonte);
        Umidade.setForeground(Color.WHITE);
        Vento.setFont(fonte);
        Vento.setForeground(Color.WHITE);

        //janela.add(instrucao);
        //janela.add(campoCidede);//adiciona dentro da janela
        //janela.add(botaoBuscar);

        fundoTela.setLayout(new GridBagLayout());

        JPanel painelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 150)); // Nossa cor preta transparente
                g.fillRect(0, 0, getWidth(), getHeight()); // Pinta a bandeja toda
                super.paintComponent(g);
            }
        };
        painelCentral.setOpaque(false); // O comando mágico que impede o texto "fantasma"
        painelCentral.setLayout(new GridLayout(9, 1, 10, 10));       

        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        painelCentral.add(instrucao);

        JPanel painelBusca = new JPanel(new BorderLayout(5, 0));
        painelBusca.setOpaque(false); // Deixa transparente
        painelBusca.add(campoCidede, BorderLayout.CENTER);
        painelBusca.add(botaoBuscar, BorderLayout.EAST);
        painelCentral.add(painelBusca);

        painelCentral.add(condicao);
        painelCentral.add(temperatura);
        painelCentral.add(tempMax);
        painelCentral.add(tempMin);//adiciona dentro da janela
        painelCentral.add(Umidade);
        painelCentral.add(Vento);

        fundoTela.add(painelCentral);

        //FAZ A BUSCA
        botaoBuscar.addActionListener(e -> {
            String texto = campoCidede.getText();
            buscaUrl(texto);
        });
        campoCidede.addActionListener(e -> {
            String texto = campoCidede.getText();
            buscaUrl(texto);
        });

         

        janela.setVisible(true);
    }

    public static void buscaUrl(String cidadeDigitada){
            
            String cidadeForm = cidadeDigitada.replace(" ", "%20");//Transforma o espaço em %20

            //System.out.println("cidade: " + cidadeForm); // para testes 
            //MONTA A URL
            String chaveAPI = "S6A6FU5YM8PHC6XTZH5J9Y3Y5";
            String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + cidadeForm
                        + "?unitGroup=metric&key=" + chaveAPI
                        + "&contentType=json&lang=pt"; 

            //System.out.println("LINK: " + url);// para testes

            //NAVEGAÇÃO
            try{
                java.net.URL urlConexao = new java.net.URL(url);

                java.net.HttpURLConnection conexao = (java.net.HttpURLConnection) urlConexao.openConnection();
                conexao.setRequestMethod("GET"); 

                java.io.BufferedReader leitor = new java.io.BufferedReader(new java.io.InputStreamReader(conexao.getInputStream(), "UTF-8"));
                StringBuilder resposta = new StringBuilder();
                String linha;

                while ((linha = leitor.readLine()) != null) {
                    resposta.append(linha);
                }
                leitor.close();

                //System.out.println("\n--- DADOS DA INTERNET ---");//para testes
                //System.out.println(resposta.toString());//para testes

                String json = resposta.toString();//transforma o textão em uma variavel menorzinha
                
                //BUSCA PELA TEMPERATURA
                int posicaoTemp = json.indexOf("\"temp\":");
                //System.out.println("A palavra temp está na posição: " + posicaoTemp);//para testes
                int inicioTemp = posicaoTemp + 7;
                int fimTemp = json.indexOf(",", inicioTemp);
                String valorTemp = json.substring(inicioTemp, fimTemp);
                //System.out.println("A temperatura limpinha é: " + valorTemp);//para testes
                temperatura.setText("Temp: " + valorTemp + " °C");

                //BUSCA PELA MAX
                int posMax = json.indexOf("\"tempmax\":");
                int inicioMax = posMax + 10;
                int fimMax = json.indexOf(",", inicioMax);
                String valorMax = json.substring(inicioMax, fimMax);
                tempMax.setText("Max: " + valorMax + " °C");

                //BUSCA PELA MIN
                int posMin = json.indexOf("\"tempmin\":");
                int inicioMin = posMin + 10;
                int fimMin = json.indexOf(",", inicioMin);
                String valorMin = json.substring(inicioMin, fimMin);
                tempMin.setText("Min: " + valorMin + " °C");

                //BUSCA PELA UMIDADE
                int posUmid = json.indexOf("\"humidity\":");
                int inicioUmid = posUmid + 11;
                int fimUmid = json.indexOf(",", inicioUmid);
                String valorUmid = json.substring(inicioUmid, fimUmid);
                Umidade.setText("Umidade: " + valorUmid + "%");

                //BUSCA PELO VENTO
                int posVento = json.indexOf("\"windspeed\":");
                int inicioVento = posVento + 12;
                int fimVento = json.indexOf(",", inicioVento);
                String valorVento = json.substring(inicioVento, fimVento);
                Vento.setText("Vento: " + valorVento + " km/h");


                //BUSCA A CONDIÇÃO
                int posCond = json.indexOf("\"conditions\":\"");
                int inicioCond = posCond + 14; 
                int fimCond = json.indexOf("\"", inicioCond); // Procura a aspa fechando em vez da vírgula
                String valorCond = json.substring(inicioCond, fimCond);
                condicao.setText("Condição: " + valorCond);

                //Icones da condição
                String textoCondicao = valorCond.toLowerCase(); 
                String nomeArquivoIcone = "";

                if (textoCondicao.contains("chuva")) {
                    nomeArquivoIcone = "chuva.png";
                } else if (textoCondicao.contains("nublado") || textoCondicao.contains("encoberto")) {
                    nomeArquivoIcone = "nuvem.png";
                } else {
                    nomeArquivoIcone = "sol.png";
                }

                ImageIcon iconeOriginal = new ImageIcon(nomeArquivoIcone);
                Image imagemNativa = iconeOriginal.getImage();
                Image imagemRedimensionada = imagemNativa.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                condicao.setIcon(new ImageIcon(imagemRedimensionada));
            }
            catch(Exception erro){
                System.out.println("Temos um erro de conexão: "+ erro.getMessage());
            }
    }
}
