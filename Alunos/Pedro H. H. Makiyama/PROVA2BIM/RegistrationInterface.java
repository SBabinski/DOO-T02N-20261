package com.pedrohhm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pedrohhm.controller.TvSeriesController;
import com.pedrohhm.controller.UserController;
import com.pedrohhm.exception.InvalidUsernameException;

public class RegistrationInterface {

    private final int WIDTH = 350;
    private final int HEIGHT = 300;

    private final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);

    private final UserController userController;
    private final TvSeriesController tvSeriesController;

    private JFrame registrationFrame;

    private JPanel mainRegistrationPanel;
    private JPanel labelsPanel;
    private JPanel usernamePanel;

    private JTextField usernameTextField;

    private JButton confirmationButton;
    
    // CONSTRUCTOR \\

    public RegistrationInterface(UserController userController, TvSeriesController tvSeriesController){

        this.userController = userController;
        this.tvSeriesController = tvSeriesController;

        createRegistrationFrame();
        createMainRegistrationPanel();
        createLabelsPanel();
        createTopLabel();
        createBottomLabel();
        createUsernamePanel();
        createUsernameTextField();
        createConfirmationButton();
        createListeners();
    }

    // MÉTODOS PARA CRIAR OS COMPONENTES \\

    private void createRegistrationFrame(){

        registrationFrame = new JFrame("Interface de Cadastro");
        registrationFrame.setSize(WIDTH, HEIGHT);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setResizable(false);
        registrationFrame.setLocationRelativeTo(null);
    }
    
    private void createMainRegistrationPanel(){

        mainRegistrationPanel = new JPanel(new BorderLayout());
        registrationFrame.add(mainRegistrationPanel);
    }

    private void createLabelsPanel(){

        labelsPanel = new JPanel(new BorderLayout());
        labelsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 2));
        mainRegistrationPanel.add(labelsPanel, BorderLayout.NORTH);
    }

    private void createTopLabel(){

        JLabel topLabel = new JLabel("Olá, visitante!");
        topLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 4));
        topLabel.setFont(DEFAULT_FONT);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        labelsPanel.add(topLabel, BorderLayout.NORTH);
    }

    private void createBottomLabel(){

        JLabel bottomLabel = new JLabel("<html>Para se cadastrar no sistema, insira<br> seu nome de usuário abaixo.");
        bottomLabel.setFont(DEFAULT_FONT);
        bottomLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomLabel.setVerticalAlignment(JLabel.NORTH);
        labelsPanel.add(bottomLabel, BorderLayout.CENTER);
    }

    private void createUsernamePanel(){

        usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.setBorder(BorderFactory.createTitledBorder("Registrar Username"));
        mainRegistrationPanel.add(usernamePanel, BorderLayout.CENTER);
    }

    private void createUsernameTextField(){

        usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(new Dimension(WIDTH - 100, HEIGHT / 3));
        usernameTextField.setFont(DEFAULT_FONT);
        usernameTextField.setHorizontalAlignment(JTextField.CENTER);
        usernameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        usernamePanel.add(usernameTextField, BorderLayout.WEST);
    }

    private void createConfirmationButton(){

        confirmationButton = new JButton("OK");
        confirmationButton.setFont(DEFAULT_FONT);
        usernamePanel.add(confirmationButton, BorderLayout.CENTER);
    }

    // METODO PARA MOSTRAR A INTERFACE \\

    public void show() {

        registrationFrame.setVisible(true);
    }

    // REGISTRAR USUÁRIO E CRIAR LISTENERS \\

    private void registerUser() {

        try {

            String username = usernameTextField.getText();

            userController.registerUser(username);

            registrationFrame.dispose();

            MainInterface mainInterface =
                new MainInterface(userController, tvSeriesController);
            
            mainInterface.show();

        } catch (InvalidUsernameException e) {

            JOptionPane.showMessageDialog(
                registrationFrame,
                e.getMessage(),
                "Nome de usuário inválido",
                JOptionPane.WARNING_MESSAGE
            );
        }

        catch (Exception e) {

            JOptionPane.showMessageDialog(
                registrationFrame,
                "Não foi possível concluir o cadastro.\nTente novamente.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void createListeners() {

        confirmationButton.addActionListener(event -> registerUser());

        usernameTextField.addActionListener(event -> registerUser());
    }
}
