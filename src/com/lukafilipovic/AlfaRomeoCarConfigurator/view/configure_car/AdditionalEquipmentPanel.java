package com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

@Getter
@Setter
public class AdditionalEquipmentPanel extends JPanel {
    private JCheckBox audioCheckBox;
    private final double audioPrice = 10260.0;
    private JLabel audioPriceLbl;
    private JCheckBox navCheckBox;
    private final double navPrice = 17328.0;
    private JLabel navPriceLbl;
    private JCheckBox FSDCheckBox;
    private final double FSDPrice = 3420.0;
    private JLabel FSDPriceLbl;
    private JCheckBox parkSensorsCheckBox;
    private final double parkSensorsPrice = 2280.0;
    private JLabel parkSensorsPriceLbl;
    private JCheckBox seatHeatingCheckBox;
    private final double seatHeatingPrice = 1140.0;
    private JLabel seatHeatingPriceLbl;
    private JCheckBox headlightWashersCheckBox;
    private final double headlightWashersPrice = 1710.0;
    private JLabel headlightWashersPriceLbl;
    private JCheckBox rainSensorCheckBox;
    private final double rainSensorPrice = 2210.0;
    private JLabel rainSensorPriceLbl;
    private JCheckBox cruiseControlCheckBox;
    private final double cruiseControlPrice = 9120.0;
    private JLabel cruiseControlPriceLbl;
    private JCheckBox sunroofCheckbox;
    private final double sunroofPrice = 13680.0;
    private JLabel sunroofPriceLbl;

    public AdditionalEquipmentPanel() {
        initComps();
        initLayout();
        addBorder();
        setVisible(true);
    }

    private void initComps() {
        Font font1 = new Font("Arial", Font.BOLD, 14);
        Font font2 = new Font("Arial", Font.PLAIN, 12);
        navCheckBox = new JCheckBox("AlfaTM Connect navigacija s 8.8-inčnim zaslonom");
        navCheckBox.setFont(font1);
        navPriceLbl = new JLabel(String.valueOf(navPrice) + " kn");
        navPriceLbl.setFont(font2);
        audioCheckBox = new JCheckBox("Hi fi audio sustav Harman/Kardon");
        audioCheckBox.setFont(font1);
        audioPriceLbl = new JLabel(String.valueOf(audioPrice) + " kn");
        audioPriceLbl.setFont(font2);
        FSDCheckBox = new JCheckBox("Sportske postavke - sportski ovjes FSD");
        FSDCheckBox.setFont(font1);
        FSDPriceLbl = new JLabel(String.valueOf(FSDPrice) + " kn");
        FSDPriceLbl.setFont(font2);
        cruiseControlCheckBox = new JCheckBox("Prilagodljivi tempomat");
        cruiseControlCheckBox.setFont(font1);
        cruiseControlPriceLbl = new JLabel(String.valueOf(cruiseControlPrice) + " kn");
        cruiseControlPriceLbl.setFont(font2);
        parkSensorsCheckBox = new JCheckBox("Parking senzori - prednji + stražnji");
        parkSensorsCheckBox.setFont(font1);
        parkSensorsPriceLbl = new JLabel(String.valueOf(parkSensorsPrice) + " kn");
        parkSensorsPriceLbl.setFont(font2);
        rainSensorCheckBox = new JCheckBox("Senzor kiše");
        rainSensorCheckBox.setFont(font1);
        rainSensorPriceLbl = new JLabel(String.valueOf(rainSensorPrice) + " kn");
        rainSensorPriceLbl.setFont(font2);
        seatHeatingCheckBox = new JCheckBox("Grijanje sjedala");
        seatHeatingCheckBox.setFont(font1);
        seatHeatingPriceLbl = new JLabel(String.valueOf(seatHeatingPrice) + " kn");
        seatHeatingPriceLbl.setFont(font2);
        sunroofCheckbox = new JCheckBox("Električni krovni otvor (stakleni)");
        sunroofCheckbox.setFont(font1);
        sunroofPriceLbl = new JLabel(String.valueOf(sunroofPrice) + " kn");
        sunroofPriceLbl.setFont(font2);
        headlightWashersCheckBox = new JCheckBox("Perači prednjih farova");
        headlightWashersCheckBox.setFont(font1);
        headlightWashersPriceLbl = new JLabel(String.valueOf(headlightWashersPrice) + " kn");
        headlightWashersPriceLbl.setFont(font2);
    }

    private void initLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 30, 0, 30);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(navCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 1;
        add(navPriceLbl, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(audioCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 3;
        add(audioPriceLbl, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(FSDCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 5;
        add(FSDPriceLbl, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(cruiseControlCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 7;
        add(cruiseControlPriceLbl, gbc);

        gbc.gridy = 8;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(parkSensorsCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 9;
        add(parkSensorsPriceLbl, gbc);

        gbc.gridy = 10;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(rainSensorCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 11;
        add(rainSensorPriceLbl, gbc);

        gbc.gridy = 12;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(seatHeatingCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 13;
        add(seatHeatingPriceLbl, gbc);

        gbc.gridy = 14;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(sunroofCheckbox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 15;
        add(sunroofPriceLbl, gbc);

        gbc.gridy = 16;
        gbc.insets = new Insets(20, 30, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(headlightWashersCheckBox, gbc);
        gbc.insets = new Insets(5, 50, 0, 0);
        gbc.gridy = 17;
        add(headlightWashersPriceLbl, gbc);
    }

    private void addBorder() {
        Border outer = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border inner = BorderFactory.createTitledBorder("DODATNA OPREMA");
        Border border = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(border);
    }

}
