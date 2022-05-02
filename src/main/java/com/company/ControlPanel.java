package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final com.company.MainFrame frame;
    private final JButton loginButton = new JButton("Login");
    private final JButton registerButton = new JButton("Register");
    private final JButton playButton = new JButton("Play without account");
    private final JButton exitButton = new JButton("Exit");

    public ControlPanel(com.company.MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        add(registerButton);
        add(loginButton);
        add(playButton);
        add(exitButton);
        setLayout(new GridLayout(1, 4));
        loginButton.addActionListener(this::loginIntoGame);
        registerButton.addActionListener(this::registerInGame);
        playButton.addActionListener(this::playWithoutAccount);
        exitButton.addActionListener(this::exitGame);
    }

    private void registerInGame(ActionEvent e){
        frame.canvas.login();
    }

    private void loginIntoGame(ActionEvent e) {
        frame.canvas.login();
    }

    private void playWithoutAccount(ActionEvent e) {
        frame.canvas.playWithoutAccount();
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}