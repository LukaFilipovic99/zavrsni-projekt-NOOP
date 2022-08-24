package com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.authentification.LogInFrame;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.NavPanel;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.home.HomeFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * Frame which shows after car configuration is done.
 */
@Getter
@Setter
public class FinishConfigurationFrame extends JFrame {
    private NavPanel navPanel;
    private JLabel alfaCodeTxtLbl;
    private JLabel alfaCode;
    private JLabel infoTextLbl1;
    private JLabel infoTextLbl2;
    private JLabel infoTextLbl3;
    private JPanel infoPanel;
    private User user;
    private Controller controller;

    public FinishConfigurationFrame() {
        super("Alfa Romeo Konfigurator");
        initComps();
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initInfoPanelLayout();
        setLayout(new BorderLayout());
        add(navPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        setVisible(true);
        activateNavPanel();
    }

    private void initComps() {
        Font font1 = new Font("Arial", Font.BOLD, 40);
        Font font2 = new Font("Arial", Font.PLAIN, 20);
        Font font3 = new Font("Arial", Font.PLAIN, 14);
        infoPanel = new JPanel();
        navPanel = new NavPanel();
        alfaCodeTxtLbl = new JLabel("VAŠ ALFA KOD: ");
        alfaCodeTxtLbl.setFont(font2);
        alfaCode = new JLabel();
        alfaCode.setFont(font1);
        infoTextLbl1 = new JLabel("Alfa kod identificira opcije koje ste odabrali prilikom konfiguracije vozila.");
        infoTextLbl1.setFont(font3);
        infoTextLbl2 = new JLabel("Uzmite ovaj kod prilikom posjete prodajnog mjesta kako bi se konfiguracija mogla automatski");
        infoTextLbl2.setFont(font3);
        infoTextLbl3 = new JLabel("replicirati i kako bi mogli zatražiti ponudu za konfigurirani automobil.");
        infoTextLbl3.setFont(font3);

        controller = new Controller();
    }

    private void initInfoPanelLayout() {
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 30, 0, 30);
        gbc.anchor = GridBagConstraints.CENTER;
        infoPanel.add(alfaCodeTxtLbl, gbc);
        gbc.gridy = 1;
        infoPanel.add(alfaCode, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        infoPanel.add(infoTextLbl1, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        infoPanel.add(infoTextLbl2, gbc);
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        infoPanel.add(infoTextLbl3, gbc);
    }

    private void activateNavPanel() {
        navPanel.getBackToHomepageBtn().addActionListener(e -> {
            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setUser(user);
            controller.setUserNameOnNavPanel(homeFrame.getNavPanel(), user);
            dispose();
        });
        navPanel.getLogOutBtn().addActionListener(e -> {
            LogInFrame logInFrame = new LogInFrame();
            dispose();
        });
    }
}
