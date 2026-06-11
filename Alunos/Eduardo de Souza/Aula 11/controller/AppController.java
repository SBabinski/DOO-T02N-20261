package attDOO.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import attDOO.api.AppApi;
import attDOO.dto.ClimaResponse;
import attDOO.dto.CurrentConditions;
import attDOO.dto.Day;
import attDOO.view.AppView;

public class AppController {

    private AppView view;

    private AppApi api;

    public AppController(AppView view) {

        this.view = view;

        this.api = new AppApi();

        adicionarEventos();
    }

    public void adicionarEventos() {

        view.botaoBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                buscarCidade();
            }
        });
    }

    public void buscarCidade() {

        String cidade =
                view.campoCidade.getText().trim();

        if (cidade.isEmpty()
                || cidade.equalsIgnoreCase("Digite aqui....")) {

            JOptionPane.showMessageDialog(
                    null,
                    "Informe o nome da cidade.",
                    "Campo obrigatório",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        ClimaResponse clima =
                api.buscarClima(cidade);

        if (clima == null) {

            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possível obter os dados do clima.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        CurrentConditions atual =
                clima.currentConditions;

        Day hoje =
                clima.days.get(0);

        view.lblCidade.setText(cidade);

        view.lblTemperatura.setText(
                String.format("%.1f °C", atual.temp));

        view.lblMaxima.setText(
                String.format("%.1f °C", hoje.tempmax));

        view.lblMinima.setText(
                String.format("%.1f °C", hoje.tempmin));

        view.lblUmidade.setText(
                String.format("%.1f %%", atual.humidity));

        view.lblCondicao.setText(
                atual.conditions);

        view.lblPrecipitacao.setText(
                String.format("%.1f mm", atual.precip));

        view.lblVento.setText(
                String.format("%.1f km/h", atual.windspeed));

        view.lblDirecao.setText(
                String.format("%.0f°", atual.winddir));
    }
}