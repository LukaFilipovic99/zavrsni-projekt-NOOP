package com.lukafilipovic.AlfaRomeoCarConfigurator.view;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.UserController;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class NavPanel extends JPanel{
    private JButton logOutBtn;
    private JButton backToHomepageBtn;
    private JLabel userName;
    private User user;

    public NavPanel(){
        initComps();
        initLayout();
        setVisible(true);
    }

    private void initComps(){
        setBackground(Color.DARK_GRAY);
        Font font=new Font("Arial", Font.PLAIN, 20);
        Font fontBtn=new Font("Arial", Font.BOLD, 20);
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

    private void activatePanel(){

    }
}
