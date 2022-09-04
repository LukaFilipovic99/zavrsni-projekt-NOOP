package com.lukafilipovic.AlfaRomeoCarConfigurator.view.configure_car;

import com.lukafilipovic.AlfaRomeoCarConfigurator.controller.Controller;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.User.User;
import com.lukafilipovic.AlfaRomeoCarConfigurator.model.car.*;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.authentification.LogInFrame;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.NavPanel;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.common.PricePanel;
import com.lukafilipovic.AlfaRomeoCarConfigurator.view.home.HomeFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Frame where user can select equipment he would like to add to the car.
 */
@Getter
@Setter
public class EquipmentFrame extends JFrame implements ItemListener {
    private NavPanel navPanel;
    private PricePanel pricePanel;
    private ExteriorPanel exteriorPanel;
    private InteriorPanel interiorPanel;
    private AdditionalEquipmentPanel additionalEquipmentPanel;
    private double equipmentPrice;
    private double addEquipmentPrice;
    private double price;
    private Controller controller;
    private User user;

    private EngineDecoratedCar engineDecoratedCar;
    private List<String> equipmentList = new ArrayList<>();

    private double navPrice = 0.0;
    private double audioPrice = 0.0;
    private double FSDPrice = 0.0;
    private double parkSensorPrice = 0.0;
    private double rainSensorPrice = 0.0;
    private double seatHeatingPrice = 0.0;
    private double cruiseControlPrice = 0.0;
    private double sunroofPrice = 0.0;
    private double headlightWasherPrice = 0.0;

    public EquipmentFrame() {
        super("Alfa Romeo Konfigurator");
        setSize(1300, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComps();
        setLayout(new BorderLayout());
        add(navPanel, BorderLayout.NORTH);
        add(exteriorPanel, BorderLayout.WEST);
        add(interiorPanel, BorderLayout.CENTER);
        add(pricePanel, BorderLayout.SOUTH);
        add(additionalEquipmentPanel, BorderLayout.EAST);
        setVisible(true);
        configureCar();
        activateNavPanel();
    }


    private void initComps() {
        navPanel = new NavPanel();
        pricePanel = new PricePanel();
        exteriorPanel = new ExteriorPanel();
        interiorPanel = new InteriorPanel();
        additionalEquipmentPanel = new AdditionalEquipmentPanel();
        pricePanel.getPriceLbl().setText((price) + " kn");

        exteriorPanel.getColorCBox().addItemListener(this);
        exteriorPanel.getWheelsCBox().addItemListener(this);
        exteriorPanel.getBrakesCBox().addItemListener(this);
        interiorPanel.getSeatsCBox().addItemListener(this);
        additionalEquipmentPanel.getNavCheckBox().addItemListener(this);
        additionalEquipmentPanel.getAudioCheckBox().addItemListener(this);
        additionalEquipmentPanel.getCruiseControlCheckBox().addItemListener(this);
        additionalEquipmentPanel.getRainSensorCheckBox().addItemListener(this);
        additionalEquipmentPanel.getSunroofCheckbox().addItemListener(this);
        additionalEquipmentPanel.getHeadlightWashersCheckBox().addItemListener(this);
        additionalEquipmentPanel.getParkSensorsCheckBox().addItemListener(this);
        additionalEquipmentPanel.getFSDCheckBox().addItemListener(this);
        additionalEquipmentPanel.getSeatHeatingCheckBox().addItemListener(this);

        controller = new Controller();
    }

    /**
     * Decorate car with selected equipment using Decorator pattern and calls controller method which saves decorated car to the database.
     * Also sends user to the FinishConfigurationFrame.
     */
    private void configureCar() {
        interiorPanel.getSubmitBtn().addActionListener(e -> {
            ColorDecoratedCar colorDecoratedCar;
            WheelsDecoratedCar wheelsDecoratedCar;
            BrakesDecoratedCar brakesDecoratedCar;
            SeatsDecoratedCar seatsDecoratedCar;
            AdditionalEquipmentDecoratedCar additionalEquipmentDecoratedCar;
            double additionalEquipmentPrice = 0.0;
            System.out.println(engineDecoratedCar);
            switch (exteriorPanel.getColorCBox().getSelectedIndex()) {
                case 0 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Bijela - PASTELNA", exteriorPanel.getColors().get("Bijela - PASTELNA"));
                case 1 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Crvena - PASTELNA", exteriorPanel.getColors().get("Crvena - PASTELNA"));
                case 2 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Bijela - BISERNA", exteriorPanel.getColors().get("Bijela - BISERNA"));
                case 3 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Crvena - BISERNA", exteriorPanel.getColors().get("Crvena - BISERNA"));
                case 4 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Siva Silverstone - METALLIC", exteriorPanel.getColors().get("Siva Silverstone - METALLIC"));
                case 5 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Crna Vulcano - METALLIC", exteriorPanel.getColors().get("Crna Vulcano - METALLIC"));
                case 6 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Zelena Racing - METALLIC", exteriorPanel.getColors().get("Zelena Racing - METALLIC"));
                case 7 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Plava Montecarlo - METALLIC", exteriorPanel.getColors().get("Plava Montecarlo - METALLIC"));
                case 8 -> colorDecoratedCar = controller.addColorToCar(engineDecoratedCar, "Plava Misano - METALLIC", exteriorPanel.getColors().get("Plava Misano - METALLIC"));
                default -> throw new IllegalStateException("Unexpected value: " + exteriorPanel.getColorCBox().getSelectedIndex());
            }
            switch (exteriorPanel.getWheelsCBox().getSelectedIndex()) {
                case 0 -> wheelsDecoratedCar = controller.addWheelsToCar(colorDecoratedCar, "R19 Aluminijski naplatci", exteriorPanel.getWheels().get("R19 Aluminijski naplatci"));
                case 1 -> wheelsDecoratedCar = controller.addWheelsToCar(colorDecoratedCar, "R18 Aluminijski naplatci (Lusso)", exteriorPanel.getWheels().get("R18 Aluminijski naplatci (Lusso)"));
                case 2 -> wheelsDecoratedCar = controller.addWheelsToCar(colorDecoratedCar, "R20 Aluminijski naplatci", exteriorPanel.getWheels().get("R20 Aluminijski naplatci"));
                default -> throw new IllegalStateException("Unexpected value: " + exteriorPanel.getWheelsCBox().getSelectedIndex());
            }
            switch (exteriorPanel.getBrakesCBox().getSelectedIndex()) {
                case 0 -> brakesDecoratedCar = controller.addBrakesToCar(wheelsDecoratedCar, "Kočiona kliješta - SIVA", exteriorPanel.getBrakes().get("Kočiona kliješta - SIVA"));
                case 1 -> brakesDecoratedCar = controller.addBrakesToCar(wheelsDecoratedCar, "Kočiona kliješta - CRVENA", exteriorPanel.getBrakes().get("Kočiona kliješta - CRVENA"));
                case 2 -> brakesDecoratedCar = controller.addBrakesToCar(wheelsDecoratedCar, "Kočiona kliješta - CRNA", exteriorPanel.getBrakes().get("Kočiona kliješta - CRNA"));
                case 3 -> brakesDecoratedCar = controller.addBrakesToCar(wheelsDecoratedCar, "Kočiona kliješta - ŽUTA", exteriorPanel.getBrakes().get("Kočiona kliješta - ŽUTA"));
                default -> throw new IllegalStateException("Unexpected value: " + exteriorPanel.getBrakesCBox().getSelectedIndex());
            }
            switch (interiorPanel.getSeatsCBox().getSelectedIndex()) {
                case 0 -> seatsDecoratedCar = controller.addSeatsToCar(brakesDecoratedCar, "Tkanina", interiorPanel.getSeats().get("Tkanina"));
                case 1 -> seatsDecoratedCar = controller.addSeatsToCar(brakesDecoratedCar, "Koža", interiorPanel.getSeats().get("Koža"));
                default -> throw new IllegalStateException("Unexpected value: " + interiorPanel.getSeatsCBox().getSelectedIndex());
            }
            if (additionalEquipmentPanel.getNavCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getNavCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getNavPrice();
            }
            if (additionalEquipmentPanel.getAudioCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getAudioCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getAudioPrice();
            }
            if (additionalEquipmentPanel.getFSDCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getFSDCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getFSDPrice();
            }
            if (additionalEquipmentPanel.getCruiseControlCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getCruiseControlCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getCruiseControlPrice();
            }
            if (additionalEquipmentPanel.getParkSensorsCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getParkSensorsCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getParkSensorsPrice();
            }
            if (additionalEquipmentPanel.getRainSensorCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getRainSensorCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getRainSensorPrice();
            }
            if (additionalEquipmentPanel.getSeatHeatingCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getSeatHeatingCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getSeatHeatingPrice();
            }
            if (additionalEquipmentPanel.getSunroofCheckbox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getSunroofCheckbox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getSunroofPrice();
            }
            if (additionalEquipmentPanel.getHeadlightWashersCheckBox().isSelected()) {
                equipmentList.add(additionalEquipmentPanel.getHeadlightWashersCheckBox().getText());
                additionalEquipmentPrice += additionalEquipmentPanel.getHeadlightWashersPrice();
            }
            additionalEquipmentDecoratedCar = controller.addEquipmentToCar(seatsDecoratedCar, equipmentList, additionalEquipmentPrice);
            JDialog dialog = new JDialog();
            Runnable runConfiguration = () -> {
                try {
                    String carId = controller.saveCar(user, additionalEquipmentDecoratedCar);
                    dialog.dispose();
                    FinishConfigurationFrame finishConfigurationFrame = new FinishConfigurationFrame();
                    finishConfigurationFrame.setUser(user);
                    controller.setAlfaCodeToFinishConfigurationFrame(finishConfigurationFrame, carId);
                    controller.setUserNameOnNavPanel(finishConfigurationFrame.getNavPanel(), user);
                    dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
            Runnable runShowDialog = () -> {
                JOptionPane optionPane = new JOptionPane("Vaš automobil se konfigurira...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
                dialog.setTitle("Konfiguracija automobila");
                dialog.setModal(true);
                dialog.setContentPane(optionPane);
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                dialog.setLocation((int) d.getWidth() / 2 - (int) dialog.getPreferredSize().getWidth() / 2 - 7,
                        (int) d.getHeight() / 2 - (int) dialog.getPreferredSize().getHeight() / 2 - 18);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.pack();
                dialog.setVisible(true);
            };
            Thread t1 = new Thread(runConfiguration);
            Thread t2 = new Thread(runShowDialog);
            t1.start();
            t2.start();
        });
    }

    /**
     * Sums total price depends on which ComboBox items and CheckBoxes are selected and show total price on the pricePanel.
     *
     * @param e ItemEvent
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        colorCBoxStateChanged(e);
        wheelsCBoxStateChanged(e);
        brakesCBoxStateChanged(e);
        seatsCBoxStateChanged(e);
        additionalEquipmentPanelCheckBoxStateChanged(e);

        equipmentPrice = exteriorPanel.getColorPrice() + exteriorPanel.getWheelsPrice() + exteriorPanel.getBrakePrice() + interiorPanel.getSeatsPrice();
        pricePanel.getPriceLbl().setText((equipmentPrice + price + addEquipmentPrice) + " kn");
    }

    /**
     * Change colorPrice and colorPictureLbl depends on which ColorComboBox Item is selected.
     *
     * @param e ItemEvent
     */
    private void colorCBoxStateChanged(ItemEvent e) {
        if (e.getSource() == exteriorPanel.getColorCBox()) {
            switch (exteriorPanel.getColorCBox().getSelectedIndex()) {
                case 0 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[1]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Bijela - PASTELNA"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 1 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[4]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Crvena - PASTELNA"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 2 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[0]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Bijela - BISERNA"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 3 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[3]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Crvena - BISERNA"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 4 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[7]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Siva Silverstone - METALLIC"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 5 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[2]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Crna Vulcano - METALLIC"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 6 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[8]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Zelena Racing - METALLIC"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 7 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[6]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Plava Montecarlo - METALLIC"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
                case 8 -> {
                    exteriorPanel.getColorPictureLbl().setIcon(new ImageIcon(exteriorPanel.getColorImages()[5]));
                    exteriorPanel.setColorPrice(exteriorPanel.getColors().get("Plava Misano - METALLIC"));
                    exteriorPanel.getColorPriceLbl().setText((exteriorPanel.getColorPrice()) + " kn");
                }
            }
        }
    }

    /**
     * Change wheelsPrice and wheelsPictureLbl depends on which WheelsComboBox Item is selected.
     *
     * @param e ItemEvent
     */
    private void wheelsCBoxStateChanged(ItemEvent e) {
        if (e.getSource() == exteriorPanel.getWheelsCBox()) {
            switch (exteriorPanel.getWheelsCBox().getSelectedIndex()) {
                case 0 -> {
                    exteriorPanel.getWheelsPictureLbl().setIcon(new ImageIcon(exteriorPanel.getWheelsImages()[1]));
                    exteriorPanel.setWheelsPrice(exteriorPanel.getWheels().get("R19 Aluminijski naplatci"));
                    exteriorPanel.getWheelsPriceLbl().setText((exteriorPanel.getWheelsPrice()) + " kn");
                }
                case 1 -> {
                    exteriorPanel.getWheelsPictureLbl().setIcon(new ImageIcon(exteriorPanel.getWheelsImages()[0]));
                    exteriorPanel.setWheelsPrice(exteriorPanel.getWheels().get("R18 Aluminijski naplatci (Lusso)"));
                    exteriorPanel.getWheelsPriceLbl().setText((exteriorPanel.getWheelsPrice()) + " kn");
                }
                case 2 -> {
                    exteriorPanel.getWheelsPictureLbl().setIcon(new ImageIcon(exteriorPanel.getWheelsImages()[2]));
                    exteriorPanel.setWheelsPrice(exteriorPanel.getWheels().get("R20 Aluminijski naplatci"));
                    exteriorPanel.getWheelsPriceLbl().setText((exteriorPanel.getWheelsPrice()) + " kn");
                }
            }
        }
    }

    /**
     * Change brakesPrice and brakesPictureLbl depends on which BrakesComboBox Item is selected.
     *
     * @param e ItemEvent
     */
    private void brakesCBoxStateChanged(ItemEvent e) {
        if (e.getSource() == exteriorPanel.getBrakesCBox()) {
            switch (exteriorPanel.getBrakesCBox().getSelectedIndex()) {
                case 0 -> {
                    exteriorPanel.getBrakesPictureLbl().setIcon(new ImageIcon(exteriorPanel.getBrakesImages()[2]));
                    exteriorPanel.setBrakePrice(exteriorPanel.getBrakes().get("Kočiona kliješta - SIVA"));
                    exteriorPanel.getBrakesPriceLbl().setText((exteriorPanel.getBrakePrice()) + " kn");
                }
                case 1 -> {
                    exteriorPanel.getBrakesPictureLbl().setIcon(new ImageIcon(exteriorPanel.getBrakesImages()[1]));
                    exteriorPanel.setBrakePrice(exteriorPanel.getBrakes().get("Kočiona kliješta - CRVENA"));
                    exteriorPanel.getBrakesPriceLbl().setText((exteriorPanel.getBrakePrice()) + " kn");
                }
                case 2 -> {
                    exteriorPanel.getBrakesPictureLbl().setIcon(new ImageIcon(exteriorPanel.getBrakesImages()[0]));
                    exteriorPanel.setBrakePrice(exteriorPanel.getBrakes().get("Kočiona kliješta - CRNA"));
                    exteriorPanel.getBrakesPriceLbl().setText((exteriorPanel.getBrakePrice()) + " kn");
                }
                case 3 -> {
                    exteriorPanel.getBrakesPictureLbl().setIcon(new ImageIcon(exteriorPanel.getBrakesImages()[3]));
                    exteriorPanel.setBrakePrice(exteriorPanel.getBrakes().get("Kočiona kliješta - ŽUTA"));
                    exteriorPanel.getBrakesPriceLbl().setText((exteriorPanel.getBrakePrice()) + " kn");
                }
            }
        }
    }

    /**
     * Change seatsPrice and seatsPictureLbl depends on which SeatsComboBox Item is selected.
     *
     * @param e ItemEvent
     */
    private void seatsCBoxStateChanged(ItemEvent e) {
        if (e.getSource() == interiorPanel.getSeatsCBox()) {
            switch (interiorPanel.getSeatsCBox().getSelectedIndex()) {
                case 0 -> {
                    interiorPanel.getSeatsPictureLbl().setIcon(new ImageIcon(interiorPanel.getSeatsImages()[1]));
                    interiorPanel.setSeatsPrice(interiorPanel.getSeats().get("Tkanina"));
                    interiorPanel.getSeatsPriceLbl().setText((interiorPanel.getSeatsPrice()) + " kn");
                }
                case 1 -> {
                    interiorPanel.getSeatsPictureLbl().setIcon(new ImageIcon(interiorPanel.getSeatsImages()[0]));
                    interiorPanel.setSeatsPrice(interiorPanel.getSeats().get("Koža"));
                    interiorPanel.getSeatsPriceLbl().setText((interiorPanel.getSeatsPrice()) + " kn");
                }
            }
        }
    }

    /**
     * Change addEquipmentPrice depends on which CheckBoxes are selected.
     *
     * @param e ItemEvent
     */
    private void additionalEquipmentPanelCheckBoxStateChanged(ItemEvent e) {
        if (e.getSource() == additionalEquipmentPanel.getNavCheckBox()) {
            if (additionalEquipmentPanel.getNavCheckBox().isSelected())
                navPrice = additionalEquipmentPanel.getNavPrice();
            else navPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getAudioCheckBox()) {
            if (additionalEquipmentPanel.getAudioCheckBox().isSelected())
                audioPrice = additionalEquipmentPanel.getAudioPrice();
            else audioPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getFSDCheckBox()) {
            if (additionalEquipmentPanel.getFSDCheckBox().isSelected())
                FSDPrice = additionalEquipmentPanel.getFSDPrice();
            else FSDPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getParkSensorsCheckBox()) {
            if (additionalEquipmentPanel.getParkSensorsCheckBox().isSelected())
                parkSensorPrice = additionalEquipmentPanel.getParkSensorsPrice();
            else parkSensorPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getRainSensorCheckBox()) {
            if (additionalEquipmentPanel.getRainSensorCheckBox().isSelected())
                rainSensorPrice = additionalEquipmentPanel.getRainSensorPrice();
            else rainSensorPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getCruiseControlCheckBox()) {
            if (additionalEquipmentPanel.getCruiseControlCheckBox().isSelected())
                cruiseControlPrice = additionalEquipmentPanel.getCruiseControlPrice();
            else cruiseControlPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getSeatHeatingCheckBox()) {
            if (additionalEquipmentPanel.getSeatHeatingCheckBox().isSelected())
                seatHeatingPrice = additionalEquipmentPanel.getSeatHeatingPrice();
            else seatHeatingPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getSunroofCheckbox()) {
            if (additionalEquipmentPanel.getSunroofCheckbox().isSelected())
                sunroofPrice = additionalEquipmentPanel.getSunroofPrice();
            else sunroofPrice = 0.0;
        }
        if (e.getSource() == additionalEquipmentPanel.getHeadlightWashersCheckBox()) {
            if (additionalEquipmentPanel.getHeadlightWashersCheckBox().isSelected())
                headlightWasherPrice = additionalEquipmentPanel.getHeadlightWashersPrice();
            else headlightWasherPrice = 0.0;
        }
        addEquipmentPrice = navPrice + audioPrice + FSDPrice + parkSensorPrice + rainSensorPrice + seatHeatingPrice + cruiseControlPrice + sunroofPrice + headlightWasherPrice;
    }

    /**
     * Activate buttons on NavPanel which can log out user (logOutBtn) or send user to the HomeFrame (getBackToHomepageBtn).
     */
    private void activateNavPanel() {
        navPanel.getBackToHomepageBtn().addActionListener(e -> {
            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setUser(user);
            controller.setUserNameOnNavPanel(homeFrame.getNavPanel(), user);
            dispose();
        });
        navPanel.getLogOutBtn().addActionListener(e -> {
            LogInFrame logInFrame = new LogInFrame();
            dispose();
        });
    }
}
