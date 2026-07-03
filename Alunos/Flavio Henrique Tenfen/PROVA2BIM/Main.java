import javax.swing.*;
import modelo.PerfilUsuario;
import modelo.Serie;
import servico.ServicoPerfil;
import servico.ServicoTvMaze;
import tela.JanelaPerfil;
import tela.JanelaPrincipal;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::iniciar);
    }

    private static void iniciar() {
        java.awt.Color fundoCombo = new java.awt.Color(26, 30, 48);
        java.awt.Color textoCombo = new java.awt.Color(230, 234, 250);
        java.awt.Color selCombo   = new java.awt.Color(100, 80, 220);
        UIManager.put("ComboBox.background",          fundoCombo);
        UIManager.put("ComboBox.foreground",          textoCombo);
        UIManager.put("ComboBox.selectionBackground", selCombo);
        UIManager.put("ComboBox.selectionForeground", textoCombo);
        UIManager.put("ComboBox.buttonBackground",    fundoCombo);
        UIManager.put("ComboBoxUI", "javax.swing.plaf.basic.BasicComboBoxUI");
        UIManager.put("List.background",              fundoCombo);
        UIManager.put("List.foreground",              textoCombo);
        UIManager.put("List.selectionBackground",     selCombo);
        UIManager.put("List.selectionForeground",     textoCombo);

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignorado) {}

        ServicoPerfil  servicoPerfil  = new ServicoPerfil();
        ServicoTvMaze  servicoTvMaze  = new ServicoTvMaze();
        PerfilUsuario  perfil         = servicoPerfil.carregar();
        if (perfil.getNome() == null || perfil.getNome().isBlank()) {
            JanelaPerfil janelaPerfil = new JanelaPerfil(null, perfil);
            janelaPerfil.setVisible(true);
            perfil.setNome(janelaPerfil.isConfirmado() ? janelaPerfil.getNomeDigitado() : "Usuário");
        }

        if (perfil.getFavoritas().isEmpty()
                && perfil.getAssistidas().isEmpty()
                && perfil.getQueroAssistir().isEmpty()) {
            preCarregarDados(perfil);
        }

        try { servicoPerfil.salvar(perfil); } catch (Exception ignorado) {}

        new JanelaPrincipal(perfil, servicoPerfil, servicoTvMaze);
    }

    private static void preCarregarDados(PerfilUsuario perfil) {

        Serie breakingBad = criarSerie(169, "Breaking Bad", "Inglês",
                new String[]{"Drama", "Crime", "Thriller"},
                9.3, "Ended", "2008-01-20", "2013-09-29", "AMC",
                "Um professor de química do ensino médio diagnosticado com câncer se torna "
                + "fabricante de metanfetamina para garantir o futuro financeiro da família.");

        Serie peakyBlinders = criarSerie(269, "Peaky Blinders", "Inglês",
                new String[]{"Drama", "Crime", "Thriller"},
                8.8, "Ended", "2013-09-12", "2022-04-03", "BBC One",
                "Um gangster de Birmingham lidera sua família criminosa no período após a Primeira Guerra Mundial.");

        Serie dark = criarSerie(305, "Dark", "Alemão",
                new String[]{"Drama", "Thriller", "Ficção Científica"},
                8.8, "Ended", "2017-12-01", "2020-06-27", "Netflix",
                "Viagens no tempo conectam quatro famílias em uma pequena cidade alemã ao longo de três épocas.");

        Serie theBoys = criarSerie(15299, "The Boys", "Inglês",
                new String[]{"Drama", "Ação", "Crime"},
                8.7, "Running", "2019-07-26", null, "Amazon Prime Video",
                "Em um mundo onde super-heróis abusam de seus poderes, um grupo de vigilantes decide combatê-los.");

        Serie lastOfUs = criarSerie(63881, "The Last of Us", "Inglês",
                new String[]{"Drama", "Ação", "Aventura"},
                8.7, "Running", "2023-01-15", null, "HBO",
                "Em um mundo pós-apocalíptico devastado por uma infecção fúngica, um sobrevivente protege uma jovem imune.");

        Serie blackMirror = criarSerie(3050, "Black Mirror", "Inglês",
                new String[]{"Drama", "Ficção Científica", "Thriller"},
                8.0, "Running", "2011-12-04", null, "Channel 4 / Netflix",
                "Antologia que explora os efeitos sombrios das novas tecnologias na sociedade moderna.");

        perfil.adicionarFavorita(breakingBad);
        perfil.adicionarFavorita(peakyBlinders);
        perfil.adicionarFavorita(dark);

        perfil.adicionarAssistida(breakingBad);
        perfil.adicionarAssistida(peakyBlinders);
        perfil.adicionarAssistida(dark);

        perfil.adicionarQueroAssistir(theBoys);
        perfil.adicionarQueroAssistir(lastOfUs);
        perfil.adicionarQueroAssistir(blackMirror);
    }

    private static Serie criarSerie(int id, String nome, String idioma,
                                     String[] generos, double nota, String estado,
                                     String estreia, String termino,
                                     String emissora, String sinopse) {
        return new Serie(id, nome, idioma, generos, nota, estado, estreia, termino, emissora, sinopse);
    }
}
