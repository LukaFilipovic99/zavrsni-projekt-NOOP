package com.lukafilipovic.AlfaRomeoCarConfigurator.view.authentification;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

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
    private JLabel emailLbl;
    private JTextField emailTxt;
    private JLabel passwordLbl;
    private JPasswordField passwordTxt;
    private JLabel passwordRepeatLbl;
    private JPasswordField passwordRepeatTxt;
    private JButton signUpBtn;
    private JLabel logInLbl;
    private JButton logInBtn;
    private Controller controller;
    private User user;

    public SignUpFrame() {
        super("Alfa Romeo Konfigurator - Registracija");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initLayout();
        setVisible(true);
        activatePanel();
    }

    private void initComps() {
        Font font = new Font("Arial", Font.BOLD, 18);
        Font font1 = new Font("Arial", Font.PLAIN, 18);
        firstNameLbl = new JLabel("Ime: ");
        firstNameLbl.setFont(font);
        firstNameTxt = new JTextField(15);
        firstNameTxt.setFont(font1);
        lastNameLbl = new JLabel("Prezime: ");
        lastNameLbl.setFont(font);
        lastNameTxt = new JTextField(15);
        lastNameTxt.setFont(font1);
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
        logInLbl = new JLabel("Imate račun? Prijavite se ovdje!");
        logInBtn = new JButton("Prijavi se");

        controller = new Controller();
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
        add(emailLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(emailTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(passwordLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(passwordTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
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

    private void activatePanel() {
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = false;
                if (isValidated()) {
                    user = createUser();
                    try {
                        String message = controller.saveUser(user);
                        if (message == "Registracija uspješna!") success = true;
                        JOptionPane.showMessageDialog(new Frame(), message, "Registracija", JOptionPane.PLAIN_MESSAGE);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if (success) {
                    dispose();
                    new LogInFrame();
                }
            }
        });
        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogInFrame();
            }
        });
    }

    /**
     * Creates user object.
     *
     * @return user object.
     */
    private User createUser() {
        User user = new User();
        user.setFirstName(firstNameTxt.getText());
        user.setLastName(lastNameTxt.getText());
        user.setEmail(emailTxt.getText());
        user.setPassword(String.valueOf(passwordTxt.getPassword()));
        return user;
    }

    /**
     * Checks if all arrays in registration form are correctly filled.
     *
     * @return false if they are not and true if they are.
     */
    private boolean isValidated() {
        if (firstNameTxt.getText().isBlank()) {
            JOptionPane.showMessageDialog
                    (new Frame(), "Ime ne smije biti prazno.", "Registracija", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        if (lastNameTxt.getText().isBlank()) {
            JOptionPane.showMessageDialog
                    (new Frame(), "Prezime ne smije biti prazno.", "Registracija", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else if (!emailTxt.getText().contains("@")) {
            JOptionPane.showMessageDialog
                    (new Frame(), "Molimo unesite e-mail u ispravnom formatu.", "Registracija", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else if (passwordTxt.getPassword().length < 6) {
            JOptionPane.showMessageDialog
                    (new Frame(), "Morate unijeti lozinku koja sadrži minimalno 6 znakova.", "Registracija", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else if (!Arrays.equals(passwordRepeatTxt.getPassword(), passwordTxt.getPassword())) {
            JOptionPane.showMessageDialog
                    (new Frame(), "Lozinke se ne podudaraju.", "Registracija", JOptionPane.PLAIN_MESSAGE);
            return false;
        } else return true;
    }

}
