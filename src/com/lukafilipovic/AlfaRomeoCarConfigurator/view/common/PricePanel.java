package com.lukafilipovic.AlfaRomeoCarConfigurator.view.common;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class PricePanel extends JPanel {
    private JLabel priceLbl;

    public PricePanel(){
        priceLbl=new JLabel();
        Font font=new Font("Arial", Font.PLAIN, 26);
        Font font1=new Font("Arial", Font.BOLD, 32);
        setLayout(new FlowLayout());
        add(new JLabel("Cijena: ")).setFont(font);
        add(priceLbl);
        setBackground(Color.LIGHT_GRAY);
        priceLbl.setFont(font1);
        setVisible(true);
    }
}
