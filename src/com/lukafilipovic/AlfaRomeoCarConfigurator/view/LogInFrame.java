package com.lukafilipovic.AlfaRomeoCarConfigurator.view;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.UserController;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Log in frame.
 */

@Getter
@Setter
public class LogInFrame extends JFrame {
    private JLabel emailLbl;
    private JTextField usernameTxt;
    private JLabel passwordLbl;
    private JPasswordField passwordTxt;
    private JButton logInBtn;
    private JLabel signUpLbl;
    private JButton signUpBtn;
    private UserController userController;

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
        Font font=new Font("Arial", Font.BOLD, 24);
        emailLbl = new JLabel("Korisničko ime: ");
        emailLbl.setFont(font);
        usernameTxt = new JTextField(15);
        usernameTxt.setFont(font);
        passwordLbl = new JLabel("Lozinka: ");
        passwordLbl.setFont(font);
        passwordTxt = new JPasswordField(15);
        passwordTxt.setFont(font);
        logInBtn = new JButton("Logirajte se");
        logInBtn.setFont(font);
        signUpLbl = new JLabel("Nemate korisnički račun? Registrirajte se ovdje.");
        signUpBtn = new JButton("Registracija");
        userController=new UserController();
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
        add(usernameTxt, gbc);

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

    private void activateFrame(){
        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignUpFrame();
            }
        });
    }

}
