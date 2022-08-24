package com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
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
public class ExteriorPanel extends JPanel {
    private JLabel colorLbl;
    private JLabel colorPriceLbl;
    private JLabel colorPictureLbl;
    private final Map<String, Double> colors = new LinkedHashMap<>() {{
        put("Bijela - PASTELNA", 0.0);
        put("Crvena - PASTELNA", 3648.0);
        put("Bijela - BISERNA", 19950.0);
        put("Crvena - BISERNA", 19950.0);
        put("Siva Silverstone - METALLIC", 7524.0);
        put("Crna Vulcano - METALLIC", 7524.0);
        put("Zelena Racing - METALLIC", 7524.0);
        put("Plava Montecarlo - METALLIC", 7524.0);
        put("Plava Misano - METALLIC", 7524.0);
    }};
    private JComboBox colorCBox;
    private double colorPrice;
    private BufferedImage[] colorImages;
    private JComboBox wheelsCBox;
    private JLabel wheelslbl;
    private JLabel wheelsPriceLbl;
    private JLabel wheelsPictureLbl;
    private double wheelsPrice;
    private final Map<String, Double> wheels = new LinkedHashMap<>() {{
        put("R19 Aluminijski naplatci", 0.0);
        put("R18 Aluminijski naplatci (Lusso)", 7524.0);
        put("R20 Aluminijski naplatci", 21660.0);
    }};
    private BufferedImage[] wheelsImages;
    private JLabel brakesLbl;
    private JLabel brakesPriceLbl;
    private JLabel brakesPictureLbl;
    private double brakePrice;
    private final Map<String, Double> brakes = new LinkedHashMap<>() {{
        put("Kočiona kliješta - SIVA", 0.0);
        put("Kočiona kliješta - CRVENA", 2850.0);
        put("Kočiona kliješta - CRNA", 2850.0);
        put("Kočiona kliješta - ŽUTA", 2850.0);
    }};
    private JComboBox brakesCBox;
    private BufferedImage[] brakesImages;
    private double price;

    public ExteriorPanel(){
        initColorImages();
        initWheelsImages();
        initBrakesImages();
        initComps();
        initLayout();
        addBorder();
        setVisible(true);
    }

    private void initComps() {
        Font font1=new Font("Arial", Font.BOLD, 20);
        Font font2=new Font("Arial", Font.PLAIN, 18);
        colorCBox = new JComboBox(colors.keySet().toArray());
        colorCBox.setFont(font2);
        colorLbl = new JLabel("Boja: ");
        colorLbl.setFont(font1);
        colorPriceLbl = new JLabel("0.0 kn");
        colorPriceLbl.setFont(font2);
        colorPictureLbl=new JLabel(new ImageIcon(colorImages[1]));
        wheelslbl=new JLabel("Kotači: ");
        wheelslbl.setFont(font1);
        wheelsCBox=new JComboBox(wheels.keySet().toArray());
        wheelsCBox.setFont(font2);
        wheelsPriceLbl=new JLabel(String.valueOf(wheels.get("R19 Aluminijski naplatci") + " kn"));
        wheelsPriceLbl.setFont(font2);
        wheelsPictureLbl=new JLabel(new ImageIcon(wheelsImages[1]));
        brakesCBox=new JComboBox(brakes.keySet().toArray());
        brakesCBox.setFont(font2);
        brakesLbl=new JLabel("Kočiona kliješta");
        brakesLbl.setFont(font1);
        brakesPictureLbl=new JLabel(new ImageIcon(brakesImages[2]));
        brakesPriceLbl=new JLabel(String.valueOf(brakes.get("Kočiona kliješta - SIVA") + " kn"));
        brakesPriceLbl.setFont(font2);
    }

    private void initLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 0, 10);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(colorLbl, gbc);
        gbc.gridx = 1;
        add(colorPriceLbl, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        add(colorCBox, gbc);
        gbc.gridx=1;
        add(colorPictureLbl,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.insets = new Insets(50, 10, 0, 10);
        add(wheelslbl, gbc);
        gbc.gridx=1;
        gbc.insets = new Insets(20, 10, 0, 30);
        add(wheelsPriceLbl,gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        add(wheelsCBox, gbc);
        gbc.gridx=1;
        add(wheelsPictureLbl,gbc);

        gbc.gridx=0;
        gbc.gridy=4;
        gbc.insets = new Insets(50, 10, 0, 10);
        add(brakesLbl, gbc);
        gbc.gridx=1;
        gbc.insets = new Insets(20, 10, 0, 10);
        add(brakesPriceLbl,gbc);

        gbc.gridx=0;
        gbc.gridy=5;
        add(brakesCBox, gbc);
        gbc.gridx=1;
        add(brakesPictureLbl,gbc);

    }

    /**
     * Navigate through files in colorImages directory. Creates BufferedImage from every file in the directory and adds it to the colorImage array.
     */
    private void initColorImages() {
        int k = 0;
        File dir = new File("colorImages");
        File[] files = dir.listFiles();
        colorImages = new BufferedImage[9];
        for (File file : files) {
            if (!file.isDirectory()) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    colorImages[k] = image;
                    k++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Navigate through files in wheelsImages directory. Creates BufferedImage from every file in the directory and adds it to the wheelsImage array.
     */
    private void initWheelsImages() {
        int k = 0;
        File dir = new File("wheelsImages");
        File[] files = dir.listFiles();

        wheelsImages = new BufferedImage[3];
        for (File file : files) {
            if (!file.isDirectory()) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    wheelsImages[k] = image;
                    k++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Navigate through files in brakesImages directory. Creates BufferedImage from every file in the directory and adds it to the brakesImage array.
     */

    private void initBrakesImages() {
        int k = 0;
        File dir = new File("brakesImages");
        File[] files = dir.listFiles();
        brakesImages = new BufferedImage[4];
        for (File file : files) {
            if (!file.isDirectory()) {
                try {
                    BufferedImage image = ImageIO.read(file);
                    brakesImages[k] = image;
                    k++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addBorder(){
        Border outer = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border inner = BorderFactory.createTitledBorder("EKSTERIJER");
        Border border = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(border);
    }

}
