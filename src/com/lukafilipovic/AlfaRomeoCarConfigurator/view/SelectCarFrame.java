package com.lukafilipovic.AlfaRomeoCarConfigurator.view;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.UserController;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class SelectCarFrame extends JFrame implements ItemListener {
    private final Map<String, Double> models = new LinkedHashMap<>(){{
        put("GIULIA", 255550.00);
        put("STELVIO", 318250.00);
    }};
    private JPanel carModelPanel;
    private JLabel selectCarModelLbl;
    private JLabel selectEngineLbl;
    private JComboBox selectCarModelCBox;
    private JComboBox selectEngineCBox;
    private final Map<String, Double> engines =new LinkedHashMap<>() {{
        put("2.0 GME 200ks", 0.00);
        put("2.0 GME 280ks", 19000.00);
        put("2.9 V6 BI-TURBO 510ks", 200250.00);
        put("2.2 JTDm 180ks", 0.00);
        put("2.2 JTDm 210ks", 9000.00);
    }};
    private JTextArea engineTxtArea;
    private NavPanel navPanel;
    private JLabel pictureLbl;
    private BufferedImage imageGiulia;
    private BufferedImage imageStelvio;
    private JButton submitButton;
    private UserController userController;
    private User user;
    private PricePanel pricePanel;
    private double price;
    private double priceModel;
    private double priceEngine;

    public SelectCarFrame(){
        super("Alfa Romeo Konfigurator");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        initCarModelPanelLayout();
        setLayout(new BorderLayout());
        add(navPanel,BorderLayout.NORTH);
        add(carModelPanel, BorderLayout.CENTER);
        add(pricePanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initComps() {
        Font font = new Font("Arial", Font.BOLD, 30);
        Font font1 = new Font("Arial", Font.PLAIN, 24);
        Font fontTxtArea=new Font("Arial", Font.BOLD, 20);
        Font fontBtn=new Font("Arial", Font.BOLD, 35);
        navPanel = new NavPanel();
        selectCarModelCBox = new JComboBox(models.keySet().toArray());
        selectCarModelCBox.setFont(font);
        selectCarModelCBox.addItemListener(this);
        selectCarModelLbl = new JLabel("Odaberite model vozila: ");
        selectCarModelLbl.setFont(font1);
        carModelPanel = new JPanel();
        selectEngineLbl = new JLabel("Motor: ");
        selectEngineLbl.setFont(font1);
        selectEngineCBox = new JComboBox(engines.keySet().toArray());
        selectEngineCBox.addItemListener(this);
        selectEngineCBox.setFont(font);
        engineTxtArea = new JTextArea(" \n   Tip goriva: BENZIN   \n   Emisija CO2 (g/km): 204   \n   Potrošnja (l/100km): 9   \n ");
        engineTxtArea.setBackground(Color.GRAY);
        engineTxtArea.setForeground(Color.WHITE);
        engineTxtArea.setEditable(false);
        engineTxtArea.setFont(fontTxtArea);
        try {
            imageGiulia = ImageIO.read(new File("giulia.png"));
            imageStelvio = ImageIO.read(new File("stelvio.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pictureLbl = new JLabel(new ImageIcon(imageGiulia));
        userController=new UserController();
        submitButton=new JButton("Dalje");
        submitButton.setFont(fontBtn);
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(Color.WHITE);
        pricePanel=new PricePanel();
        priceModel=255550.00;
        priceEngine=0.00;
        price=priceModel+priceEngine;
        pricePanel.getPriceLbl().setText(String.valueOf(price)+" kn");
    }

    private void initCarModelPanelLayout() {
        carModelPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        carModelPanel.add(selectCarModelLbl, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        carModelPanel.add(selectCarModelCBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40, 0, 0, 0);
        carModelPanel.add(selectEngineLbl, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(70, 0, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        carModelPanel.add(selectEngineCBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0);
        carModelPanel.add(pictureLbl, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        carModelPanel.add(engineTxtArea, gbc);

        gbc.gridx=3;
        gbc.gridy=2;
        gbc.insets = new Insets(0, 0, 0, 0);
        carModelPanel.add(submitButton, gbc);

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        price=0.0;

        if (e.getSource() == selectCarModelCBox) {
            switch (selectCarModelCBox.getSelectedIndex()) {
                case 0 -> {
                    pictureLbl.setIcon(new ImageIcon(imageGiulia));
                    priceModel=models.get("GIULIA");
                }
                case 1 -> {
                    pictureLbl.setIcon(new ImageIcon(imageStelvio));
                    priceModel=models.get("STELVIO");
                }
            }
        }
        if (e.getSource() == selectEngineCBox) {
            switch (selectEngineCBox.getSelectedIndex()) {
                case 0 -> {
                    engineTxtArea.setText(" \n   Tip goriva: BENZIN   \n   Emisija CO2 (g/km): 204   \n   Potrošnja (l/100km): 9   \n ");
                    priceEngine=engines.get("2.0 GME 200ks");
                }
                case 1 -> {
                    engineTxtArea.setText(" \n   Tip goriva: BENZIN   \n   Emisija CO2 (g/km): 208   \n   Potrošnja (l/100km): 9.2   \n ");
                    priceEngine=engines.get("2.0 GME 280ks");
                }
                case 2 -> {
                    engineTxtArea.setText(" \n   Tip goriva: BENZIN   \n   Emisija CO2 (g/km): 261   \n   Potrošnja (l/100km): 11.5   \n ");
                    priceEngine=engines.get("2.9 V6 BI-TURBO 510ks");
                }
                case 3 -> {
                    engineTxtArea.setText(" \n   Tip goriva: DIZEL   \n   Emisija CO2 (g/km): 159   \n   Potrošnja (l/100km): 6.1   \n ");
                    priceEngine=engines.get("2.2 JTDm 180ks");
                }
                case 4 -> {
                    engineTxtArea.setText(" \n   Tip goriva: DIZEL  \n   Emisija CO2 (g/km): 169   \n   Potrošnja (l/100km): 6.4   \n ");
                    priceEngine=engines.get("2.2 JTDm 210ks");
                }
            }
        }
        price=priceEngine+priceModel;
        pricePanel.getPriceLbl().setText(String.valueOf(price)+" kn");
    }

    private void activatePanel(){

    }
}
