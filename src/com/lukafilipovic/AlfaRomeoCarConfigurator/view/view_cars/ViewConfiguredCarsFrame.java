package com.lukafilipovic.AlfaRomeoCarConfigurator.view.view_cars;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.user.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.authentification.LogInFrame;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.NavPanel;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.home.HomeFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Frame where user can view cars he configured.
 */
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

    public ViewConfiguredCarsFrame() {
        super("Alfa Romeo konfigurator");
        setSize(1200, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initSelectPanelLayout();
        Dimension dim = selectPanel.getPreferredSize();
        dim.height = 350;
        viewPanel.setPreferredSize(dim);
        setLayout(new BorderLayout());
        add(navPanel, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);
        add(selectPanel, BorderLayout.SOUTH);
        setVisible(true);
        viewAllCars();
        viewCarById();
        activateNavPanel();
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

        navPanel = new NavPanel();
        user = new User();
        controller = new Controller();
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

    /**
     * Clicking on showAllBtn calls controller method which gets data about all cars configured by user.
     */
    private void viewAllCars() {
        showAllBtn.addActionListener(e -> {
            JDialog dialog = new JDialog();
            Runnable runViewAllCars = () -> {
                try {
                    controller.showAllCarsOnTxtArea(user, carsTxtArea);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dialog.dispose();
            };
            Runnable runShowDialog = () -> {
                JOptionPane optionPane = new JOptionPane("Podaci o automobilima se učitavaju...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                dialog.setTitle("Pregled automobila");
                dialog.setModal(true);
                dialog.setContentPane(optionPane);
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                dialog.setLocation((int) d.getWidth() / 2 - (int) dialog.getPreferredSize().getWidth() / 2 - 7,
                        (int) d.getHeight() / 2 - (int) dialog.getPreferredSize().getHeight() / 2 - 18);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.pack();
                dialog.setVisible(true);
            };
            Thread t1 = new Thread(runViewAllCars);
            Thread t2 = new Thread(runShowDialog);
            t1.start();
            t2.start();
        });
    }

    /**
     * Clicking on showBtn calls controller method which gets data about car with id user has written in alfaCodeTxtField and shows data on carsTxtArea.
     */
    private void viewCarById() {
        showBtn.addActionListener(e -> {
            String carId = alfaCodeField.getText();
            JDialog dialog = new JDialog();
            Runnable runViewCar = () -> {
                try {
                    controller.showCarWithIdOnTxtArea(user, carId, carsTxtArea);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dialog.dispose();
            };
            Runnable runShowDialog = () -> {
                JOptionPane optionPane = new JOptionPane("Podaci o automobilu se učitavaju...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                dialog.setTitle("Pregled automobila");
                dialog.setModal(true);
                dialog.setContentPane(optionPane);
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                dialog.setLocation((int) d.getWidth() / 2 - (int) dialog.getPreferredSize().getWidth() / 2 - 7,
                        (int) d.getHeight() / 2 - (int) dialog.getPreferredSize().getHeight() / 2 - 18);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.pack();
                dialog.setVisible(true);
            };
            Thread t1 = new Thread(runViewCar);
            Thread t2 = new Thread(runShowDialog);
            t1.start();
            t2.start();
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
