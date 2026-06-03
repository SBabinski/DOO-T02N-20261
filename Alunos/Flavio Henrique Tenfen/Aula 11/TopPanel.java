import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TopPanel extends JPanel {

    public interface SearchListener {
        void onSearch(String city, String apiKey);
    }

    private final JTextField apiKeyField;
    private final JTextField cityField;
    private final JButton    btnSearch;
    private final boolean    apiKeyFixed;

    public TopPanel(SearchListener listener) {
        this.apiKeyFixed = !AppConfig.API_KEY.isBlank();
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel inner = new JPanel(new GridBagLayout());
        inner.setOpaque(false);
        inner.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;

        // Nome do app
        JLabel appName = UIHelper.label("🌤  Weather App", AppConfig.F_APPNAME, AppConfig.TEXT_MUTED);
        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 3; gc.insets = new Insets(0, 0, 14, 0);
        inner.add(appName, gc);

        int nextRow = 1;

        if (!apiKeyFixed) {
            gc.gridy = nextRow++; gc.insets = new Insets(0, 0, 4, 0);
            inner.add(UIHelper.label("API Key", AppConfig.F_LABEL, AppConfig.TEXT_MUTED), gc);

            apiKeyField = new JPasswordField(40);
            UIHelper.styleField(apiKeyField);
            apiKeyField.setFont(AppConfig.F_MONO);
            apiKeyField.setToolTipText("Obtenha sua chave em visualcrossing.com/sign-up");
            gc.gridy = nextRow++; gc.insets = new Insets(0, 0, 10, 0);
            inner.add(apiKeyField, gc);
        } else {
            apiKeyField = new JTextField();
        }

        // Label cidade
        gc.gridy = nextRow++; gc.gridwidth = 3; gc.insets = new Insets(0, 0, 4, 0);
        inner.add(UIHelper.label("Cidade", AppConfig.F_LABEL, AppConfig.TEXT_MUTED), gc);

        // Linha: campo + botão
        cityField = new JTextField(20);
        UIHelper.styleField(cityField);
        cityField.setToolTipText("Ex: Cascavel, São Paulo, Rio de Janeiro...");
        gc.gridy = nextRow; gc.gridwidth = 2; gc.weightx = 1; gc.insets = new Insets(0, 0, 0, 10);
        inner.add(cityField, gc);

        btnSearch = UIHelper.buildButton("Buscar", 100);
        gc.gridx = 2; gc.gridwidth = 1; gc.weightx = 0; gc.insets = new Insets(0, 0, 0, 0);
        inner.add(btnSearch, gc);

        add(inner, BorderLayout.CENTER);

        // Separador glass na base
        add(new GlassDivider(), BorderLayout.SOUTH);

        ActionListener go = e -> fireSearch(listener);
        btnSearch.addActionListener(go);
        cityField.addActionListener(go);
    }

    private void fireSearch(SearchListener l) {
        l.onSearch(
            cityField.getText().trim(),
            apiKeyFixed ? AppConfig.API_KEY : apiKeyField.getText().trim()
        );
    }

    public void setSearchEnabled(boolean enabled) { btnSearch.setEnabled(enabled); }

    private static class GlassDivider extends JPanel {
        GlassDivider() { setOpaque(false); setPreferredSize(new Dimension(1, 1)); }
        @Override protected void paintComponent(Graphics g) {
            g.setColor(AppConfig.GLASS_BDR);
            g.fillRect(0, 0, getWidth(), 1);
        }
    }
}
