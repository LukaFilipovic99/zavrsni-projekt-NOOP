package com.lukafilipovic.AlfaRomeoCarConfigurator.view.home;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.user.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.authentification.LogInFrame;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.NavPanel;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car.SelectCarModelFrame;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.view_cars.ViewConfiguredCarsFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * Home frame of the app which shows after successful log in.
 * User can select if he want to configure new car or view cars he already configured.
 */
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
    private Controller controller;

    public HomeFrame() {
        super("Alfa Romeo Konfigurator");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initSelectPanelLayout();
        setLayout(new BorderLayout());
        add(navPanel, BorderLayout.NORTH);
        add(selectPanel, BorderLayout.CENTER);
        setVisible(true);
        activatePanel();
        activateNavPanel();
    }

    private void initComps() {
        Font font = new Font("Arial", Font.PLAIN, 20);
        Font fontBtn = new Font("Arial", Font.BOLD, 35);
        configureNewCarLbl = new JLabel("<html><p>Konfigurirajte vaš<br>novi Alfa Romeo</p></html>");
        configureNewCarLbl.setFont(font);
        configureNewCarBtn = new JButton(">");
        configureNewCarBtn.setFont(fontBtn);
        configureNewCarBtn.setBackground(Color.DARK_GRAY);
        configureNewCarBtn.setForeground(Color.WHITE);
        viewConfiguredCarsLbl = new JLabel("<html><p>Pregledajte već<br>konfigurirane automobile</p></html>");
        viewConfiguredCarsLbl.setFont(font);
        viewConfiguredCarsBtn = new JButton(">");
        viewConfiguredCarsBtn.setFont(fontBtn);
        viewConfiguredCarsBtn.setBackground(Color.DARK_GRAY);
        viewConfiguredCarsBtn.setForeground(Color.WHITE);
        selectPanel = new JPanel();
        navPanel = new NavPanel();
        controller = new Controller();
    }

    private void initSelectPanelLayout() {
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        selectPanel.add(configureNewCarBtn, gbc);
        gbc.gridx = 1;
        selectPanel.add(viewConfiguredCarsBtn, gbc);
    }

    /**
     * By clicking on one of the buttons, user can navigate through app. Clicking on configureNewCarBtn sends user to the SelectCarModelFrame
     * where he can start configuring his car and clicking on viewConfiguredCarsBtn sends user to the ViewConfiguredCarsFrame where he can see
     * already configured cars.
     */
    private void activatePanel() {
        configureNewCarBtn.addActionListener(e -> {
            SelectCarModelFrame selectCarModelFrame = new SelectCarModelFrame();
            selectCarModelFrame.setUser(user);
            controller.setUserNameOnNavPanel(selectCarModelFrame.getNavPanel(), user);
            dispose();
        });
        viewConfiguredCarsBtn.addActionListener(e -> {
            ViewConfiguredCarsFrame viewConfiguredCarsFrame = new ViewConfiguredCarsFrame();
            viewConfiguredCarsFrame.setUser(user);
            controller.setUserNameOnNavPanel(viewConfiguredCarsFrame.getNavPanel(), user);
            dispose();
        });
    }

    /**
     * Activate buttons on NavPanel which can log out user (logOutBtn) or send user to the HomeFrame (getBackToHomepageBtn).
     */
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
