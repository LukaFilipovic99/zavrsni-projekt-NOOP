package com.lukafilipovic.AlfaRomeoCarConfigurator.view.view_cars;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.NavPanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ViewConfiguredCarsFrame extends JFrame {
    private JScrollPane viewPanel;
    private JPanel selectPanel;
    private JButton showAllBtn;
    private JTextField alfaCodeField;
    private JLabel showLbl;
    private JButton showBtn;
    private JTextArea carsTxtArea;
    private JLabel showAllLbl;
    private NavPanel navPanel;

    private User user;
    private Controller controller;

    public ViewConfiguredCarsFrame(){
        super("Alfa Romeo konfigurator");
        setSize(1200,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initSelectPanelLayout();
        Dimension dim=selectPanel.getPreferredSize();
        dim.height=350;
        viewPanel.setPreferredSize(dim);
        setLayout(new BorderLayout());
        add(navPanel, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);
        add(selectPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initComps() {
        Font fontTxt = new Font("Arial", Font.PLAIN, 16);
        Font fontBtn = new Font("Arial", Font.BOLD, 30);
        Font fontField = new Font("Arial", Font.BOLD, 24);
        carsTxtArea = new JTextArea();
        carsTxtArea.setEditable(false);
        carsTxtArea.setFont(fontTxt);
        carsTxtArea.setBackground(Color.BLACK);
        carsTxtArea.setForeground(Color.WHITE);
        viewPanel = new JScrollPane(carsTxtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        selectPanel = new JPanel();
        showAllLbl = new JLabel("Prikaži sve konfigurirane automobile:");
        showAllLbl.setFont(fontTxt);
        showAllBtn = new JButton("Prikaži sve");
        showAllBtn.setFont(fontBtn);
        showAllBtn.setBackground(Color.DARK_GRAY);
        showAllBtn.setForeground(Color.WHITE);

        showLbl = new JLabel("Prikaži konfigurirane autmobile prema Alfa kodu: ");
        showLbl.setFont(fontTxt);
        alfaCodeField = new JTextField(10);
        alfaCodeField.setFont(fontField);
        showBtn = new JButton("Prikaži");
        showBtn.setFont(fontBtn);
        showBtn.setBackground(Color.DARK_GRAY);
        showBtn.setForeground(Color.WHITE);

        navPanel=new NavPanel();
        user=new User();
        controller=new Controller();
    }

    private void initSelectPanelLayout() {
        selectPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 50, 0, 30);
        gbc.anchor = GridBagConstraints.CENTER;
        selectPanel.add(showAllLbl, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 50, 0, 30);
        selectPanel.add(showAllBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 50, 0, 30);
        gbc.anchor = GridBagConstraints.CENTER;
        selectPanel.add(showLbl, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 50, 0, 30);
        selectPanel.add(alfaCodeField, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 50, 50, 30);
        selectPanel.add(showBtn, gbc);
    }

}
