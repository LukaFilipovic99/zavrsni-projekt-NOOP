package com.lukafilipovic.AlfaRomeoCarConfigurator.view.authentification;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.home.HomeFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Log in frame.
 */
@Getter
@Setter
public class LogInFrame extends JFrame {
    private JLabel emailLbl;
    private JTextField emailTxt;
    private JLabel passwordLbl;
    private JPasswordField passwordTxt;
    private JButton logInBtn;
    private JLabel signUpLbl;
    private JButton signUpBtn;
    private Controller controller;
    private User user;

    public LogInFrame() {
        super("Alfa Romeo Konfigurator");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initLayout();
        setVisible(true);
        activateFrame();
    }

    private void initComps() {
        Font font = new Font("Arial", Font.BOLD, 24);
        emailLbl = new JLabel("E-mail: ");
        emailLbl.setFont(font);
        emailTxt = new JTextField(15);
        emailTxt.setFont(font);
        passwordLbl = new JLabel("Lozinka: ");
        passwordLbl.setFont(font);
        passwordTxt = new JPasswordField(15);
        passwordTxt.setFont(font);
        logInBtn = new JButton("Prijavi se");
        logInBtn.setFont(font);
        signUpLbl = new JLabel("Nemate korisnički račun? Registrirajte se ovdje.");
        signUpBtn = new JButton("Registracija");
        controller = new Controller();
    }

    private void initLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(emailLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(emailTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(passwordLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(passwordTxt, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(logInBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(signUpBtn, gbc);
    }

    /**
     * Clicking logInBtn calls controller method which load user from the database if login information are right and then sends user to the HomeFrame.
     * Clicking signUpBtn sends user to the SignUpFrame.
     */
    private void activateFrame() {
        logInBtn.addActionListener(e -> {
            user = new User();
            JDialog dialog = new JDialog();
            Runnable runLogIn = () -> {
                int status = 0;
                try {
                    status = controller.logIn(emailTxt.getText(), String.valueOf(passwordTxt.getPassword()));
                    user = controller.getUser();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dialog.dispose();
                if (status == 1) {
                    HomeFrame homeFrame = new HomeFrame();
                    homeFrame.setUser(user);
                    controller.setUserNameOnNavPanel(homeFrame.getNavPanel(), user);
                    dispose();
                }
            };
            Runnable runShowDialog = () -> {
                JOptionPane optionPane = new JOptionPane("Prijava u tijeku...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                dialog.setTitle("Prijava");
                dialog.setModal(true);
                dialog.setContentPane(optionPane);
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                dialog.setLocation((int) d.getWidth() / 2 - (int) dialog.getPreferredSize().getWidth() / 2 - 7,
                        (int) d.getHeight() / 2 - (int) dialog.getPreferredSize().getHeight() / 2 - 18);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.pack();
                dialog.setVisible(true);
            };
            if (isValidated()) {
                Thread t1 = new Thread(runLogIn);
                Thread t2 = new Thread(runShowDialog);
                t1.start();
                t2.start();
            }
        });
        signUpBtn.addActionListener(e -> {
            dispose();
            new SignUpFrame();
        });
    }

    /**
     * Checks if e-mail is in right format.
     *
     * @return true if it is, false if it is not.
     */
    private boolean isValidated() {
        if (!emailTxt.getText().contains("@")) {
            JOptionPane.showMessageDialog
                    (new Frame(), "Molimo unesite e-mail u ispravnom formatu.", "Prijava", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else return true;
    }
}
