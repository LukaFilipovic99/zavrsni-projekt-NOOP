package com.lukafilipovic.AlfaRomeoCarConfigurator.view.common;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.home.HomeFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
