package com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class InteriorPanel extends JPanel {
    private JPanel buttonPanel;
    private JPanel interiorEqPanel;
    private JLabel seatsLbl;
    private JLabel seatsPriceLbl;
    private JLabel seatsPictureLbl;
    private JComboBox seatsCBox;
    private final Map<String, Double> seats = new LinkedHashMap<>() {{
        put("Tkanina", 0.0);
        put("Koža", 14820.0);
    }};
    private BufferedImage[] seatsImages;
    double seatsPrice;
    private JButton submitBtn;

    public InteriorPanel() {
        initSeatsImages();
        initComps();
        initInteriorEqPanelLayout();
        initButtonLayout();
        addBorder();
        setLayout(new BorderLayout());
        add(interiorEqPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initComps() {
        buttonPanel = new JPanel();
        interiorEqPanel = new JPanel();
        Font font1 = new Font("Arial", Font.BOLD, 20);
        Font font2 = new Font("Arial", Font.PLAIN, 18);
        Font fontBtn = new Font("Arial", Font.BOLD, 35);
        seatsLbl = new JLabel("Sjedala:");
        seatsLbl.setFont(font1);
        seatsCBox = new JComboBox(seats.keySet().toArray());
        seatsCBox.setFont(font2);
        seatsPrice = seats.get("Tkanina");
        seatsPriceLbl = new JLabel(String.valueOf(seatsPrice) + " kn");
        seatsPriceLbl.setFont(font2);
        seatsPictureLbl = new JLabel(new ImageIcon(seatsImages[1]));
        submitBtn = new JButton("Konfiguriraj");
        submitBtn.setFont(fontBtn);
        submitBtn.setBackground(Color.DARK_GRAY);
        submitBtn.setForeground(Color.WHITE);
    }

    private void initInteriorEqPanelLayout() {
        interiorEqPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 5, 0, 0);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        interiorEqPanel.add(seatsLbl, gbc);
        gbc.gridx = 1;
        interiorEqPanel.add(seatsPriceLbl, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        interiorEqPanel.add(seatsCBox, gbc);
        gbc.gridy = 2;
        interiorEqPanel.add(seatsPictureLbl, gbc);
    }

    private void initButtonLayout() {
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(40, 40, 40, 40);
        buttonPanel.add(submitBtn, gbc);

    }

    /**
     * Navigate through files in seatsImages directory. Creates BufferedImage from every file in the directory and adds it to the seatsImage array.
     */
    private void initSeatsImages() {
        int k = 0;
        File dir = new File("images/seatsImages");
        File[] files = dir.listFiles();
        seatsImages = new BufferedImage[2];
        for (File file : files) {
            if (!file.isDirectory()) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    seatsImages[k] = image;
                    k++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addBorder() {
        Border outer = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border inner = BorderFactory.createTitledBorder("INTERIJER");
        Border border = BorderFactory.createCompoundBorder(outer, inner);
        interiorEqPanel.setBorder(border);
    }

}
