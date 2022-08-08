package com.lukafilipovic.AlfaRomeoCarConfigurator.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * Sign up frame.
 */

@Getter
@Setter
public class SignUpFrame extends JFrame {
    private JLabel firstNameLbl;
    private JTextField firstNameTxt;
    private JLabel lastNameLbl;
    private JTextField lastNameTxt;
    private JLabel usernameLbl;
    private JTextField usernameTxt;
    private JLabel emailLbl;
    private JTextField emailTxt;
    private JLabel passwordLbl;
    private JPasswordField passwordTxt;
    private JLabel passwordRepeatLbl;
    private JPasswordField passwordRepeatTxt;
    private JButton signUpBtn;
    private JLabel logInLbl;
    private JButton logInBtn;

    public SignUpFrame() {
        super("Alfa Romeo Konfigurator - Registracija");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initLayout();
        setVisible(true);
    }

    private void initComps() {
        Font font = new Font("Calibri", Font.BOLD, 18);
        Font font1= new Font("Calibri", Font.PLAIN, 18);
        firstNameLbl = new JLabel("Ime: ");
        firstNameLbl.setFont(font);
        firstNameTxt = new JTextField(15);
        firstNameTxt.setFont(font1);
        lastNameLbl = new JLabel("Prezime: ");
        lastNameLbl.setFont(font);
        lastNameTxt = new JTextField(15);
        lastNameTxt.setFont(font1);
        usernameLbl = new JLabel("Korisničko ime: ");
        usernameLbl.setFont(font);
        usernameTxt = new JTextField(15);
        usernameTxt.setFont(font1);
        emailLbl = new JLabel("E-mail: ");
        emailLbl.setFont(font);
        emailTxt = new JTextField(25);
        emailTxt.setFont(font1);
        passwordLbl = new JLabel("Unesite lozinku: ");
        passwordLbl.setFont(font);
        passwordTxt = new JPasswordField(15);
        passwordTxt.setFont(font1);
        passwordRepeatLbl = new JLabel("Potvrdite lozinku: ");
        passwordRepeatLbl.setFont(font);
        passwordRepeatTxt = new JPasswordField(15);
        passwordRepeatTxt.setFont(font1);
        signUpBtn = new JButton("Registriraj se");
        signUpBtn.setFont(font);
        logInLbl=new JLabel("Imate račun? Logirajte se ovdje!");
        logInBtn=new JButton("Logiraj se");
    }

    private void initLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(firstNameLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(firstNameTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lastNameLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lastNameTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(usernameLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(usernameTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(emailLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(emailTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(passwordLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(passwordTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(passwordRepeatLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(passwordRepeatTxt, gbc);

        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(signUpBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logInLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(20, 10, 0, 0);
        add(logInBtn, gbc);
    }

}
