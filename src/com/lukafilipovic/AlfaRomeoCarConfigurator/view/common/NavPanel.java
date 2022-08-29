package com.lukafilipovic.AlfaRomeoCarConfigurator.view.common;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * Panel which shows on every frame except LogIn and SignUp frames. It is used to navigate through app. Also shows name of the user which is logged in app.
 */
@Getter
@Setter
public class NavPanel extends JPanel{
    private JButton logOutBtn;
    private JButton backToHomepageBtn;
    private JLabel userName;

    public NavPanel(){
        initComps();
        initLayout();
        setVisible(true);
    }

    private void initComps(){
        setBackground(Color.DARK_GRAY);
        Font font=new Font("Arial", Font.PLAIN, 28);
        Font fontBtn=new Font("Arial", Font.BOLD, 28);
        logOutBtn=new JButton("Odjavi se");
        logOutBtn.setFont(fontBtn);
        logOutBtn.setBackground(Color.GRAY);
        logOutBtn.setForeground(Color.WHITE);
        backToHomepageBtn=new JButton("Početna");
        backToHomepageBtn.setFont(fontBtn);
        backToHomepageBtn.setBackground(Color.GRAY);
        backToHomepageBtn.setForeground(Color.WHITE);
        userName=new JLabel();
        userName.setFont(font);
        userName.setForeground(Color.WHITE);
    }

    private void initLayout() {
        setLayout(new FlowLayout());
        add(userName);
        add(backToHomepageBtn);
        add(logOutBtn);
    }
}
