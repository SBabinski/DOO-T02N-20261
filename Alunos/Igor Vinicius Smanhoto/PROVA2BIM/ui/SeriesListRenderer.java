package com.tvtracker.ui;

import com.tvtracker.model.Series;

import javax.swing.*;
import java.awt.*;

/**
 * Renderer personalizado para células de JList contendo objetos Series.
 * Exibe as informações principais de forma visual e organizada.
 */
public class SeriesListRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                   int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);

        // Verifica e converte o tipo antes de usar (compatível com Java 8)
        if (!(value instanceof Series)) return label;
        Series series = (Series) value;

        // Monta o texto HTML para layout rico dentro da célula
        String ratingStr = series.getRatingFormatted();
        String starIcon = (series.getRating() > 0) ? "&#9733; " : "";
        Color statusColor = AppTheme.getStatusColor(series.getStatus());
        String statusHex = String.format("#%02x%02x%02x",
                statusColor.getRed(), statusColor.getGreen(), statusColor.getBlue());
        String accentHex = String.format("#%02x%02x%02x",
                AppTheme.GOLD.getRed(), AppTheme.GOLD.getGreen(), AppTheme.GOLD.getBlue());

        String html = String.format(
                "<html><body style='padding:4px 8px;'>"
                + "<b style='font-size:13px;'>%s</b>&nbsp;&nbsp;"
                + "<span style='color:%s; font-size:11px;'>%s</span><br>"
                + "<span style='color:#aaaacc; font-size:11px;'>%s</span>&nbsp;&#183;&nbsp;"
                + "<span style='color:%s; font-size:11px;'>%s%s</span>"
                + "</body></html>",
                escapeHtml(series.getName()),
                statusHex, escapeHtml(series.getStatusInPortuguese()),
                escapeHtml(series.getGenresAsString()),
                accentHex, starIcon, ratingStr
        );

        label.setText(html);
        label.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        // Aplica cores de seleção e fundo alternado nas linhas
        if (isSelected) {
            label.setBackground(AppTheme.ACCENT);
            label.setForeground(Color.WHITE);
        } else {
            // Alterna entre dois tons de fundo para facilitar leitura
            label.setBackground(index % 2 == 0 ? AppTheme.BG_CARD : AppTheme.BG_PANEL);
            label.setForeground(AppTheme.TEXT_PRIMARY);
        }

        label.setOpaque(true);
        return label;
    }

    // Escapa caracteres HTML especiais nos textos das séries
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;");
    }
}
