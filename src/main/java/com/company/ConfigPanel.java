package com.company;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final com.company.MainFrame frame;
    private JLabel label;

    public ConfigPanel(com.company.MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Select a mode of playing:");
        add(label);
    }

    public void modifyHelpMessage(String message) {
        this.label.setText(message);
    }
}