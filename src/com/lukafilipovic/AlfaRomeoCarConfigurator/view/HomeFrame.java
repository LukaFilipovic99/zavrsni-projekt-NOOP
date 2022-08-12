package com.lukafilipovic.AlfaRomeoCarConfigurator.view;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.UserController;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class HomeFrame extends JFrame {
    private JLabel configureNewCarLbl;
    private JButton configureNewCarBtn;
    private JLabel viewConfiguredCarsLbl;
    private JButton viewConfiguredCarsBtn;
    private JPanel selectPanel;
    private NavPanel navPanel;
    private User user;

    public HomeFrame(){
        super("Alfa Romeo Konfigurator");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initSelectPanelLayout();
        setLayout(new BorderLayout());
        add(navPanel,BorderLayout.NORTH);
        add(selectPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initComps(){
        Font font=new Font("Arial", Font.PLAIN, 20);
        Font fontBtn=new Font("Arial", Font.BOLD, 30);
        configureNewCarLbl=new JLabel("<html><p>Konfigurirajte vaš<br>novi Alfa Romeo</p></html>");
        configureNewCarLbl.setFont(font);
        configureNewCarBtn=new JButton(">");
        configureNewCarBtn.setFont(fontBtn);
        configureNewCarBtn.setBackground(Color.DARK_GRAY);
        configureNewCarBtn.setForeground(Color.WHITE);
        viewConfiguredCarsLbl= new JLabel("<html><p>Pregledajte već<br>konfigurirane automobile</p></html>");
        viewConfiguredCarsLbl.setFont(font);
        viewConfiguredCarsBtn=new JButton(">");
        viewConfiguredCarsBtn.setFont(fontBtn);
        viewConfiguredCarsBtn.setBackground(Color.DARK_GRAY);
        viewConfiguredCarsBtn.setForeground(Color.WHITE);
        selectPanel=new JPanel();
        navPanel=new NavPanel();
    }

    private void initSelectPanelLayout(){
        selectPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 30, 0, 30);
        gbc.anchor = GridBagConstraints.CENTER;
        selectPanel.add(configureNewCarLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        selectPanel.add(viewConfiguredCarsLbl, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        selectPanel.add(configureNewCarBtn, gbc);
        gbc.gridx=1;
        selectPanel.add(viewConfiguredCarsBtn,gbc);
    }


}
