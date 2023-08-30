package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.SwingConstants.CENTER;


public class CarInventory {
    public CarInventory() {
        var frame = new JFrame("Car Inventory");
        final var textFieldVin = new JTextField(12);
        final var textFieldMake = new JTextField(12);
        final var textFieldModel = new JTextField(12);
        final var textFieldYear = new JTextField(12);
        final var textFieldColor = new JTextField(12);
        final var fieldPanel = new JPanel(new GridLayout(5, 2, 12, 12));
        final var buttonPanel = new JPanel(new GridLayout(1, 5, 12, 12));
        final var statusPanel = new JPanel(new GridLayout(1, 1, 12, 12));
        final var carArrayList = new ArrayList<Car>();

        statusPanel.setBorder(createTitledBorder("Status"));
        var statusLabel = new JLabel(" ");

        var jLabelList = List.of(new JLabel("Vin"),
                new JLabel("Make"),
                new JLabel("Model"),
                new JLabel("Year"),
                new JLabel("Color"));

        jLabelList.forEach(label -> label.setHorizontalAlignment(CENTER));

        var textFields = List.of(textFieldVin,
                textFieldMake,
                textFieldModel,
                textFieldYear,
                textFieldColor);

        int bound = jLabelList.size();

        for (int index = 0; index < bound; index++) {
            var label = jLabelList.get(index);
            var textField = textFields.get(index);
            label.setLabelFor(textField);
            fieldPanel.add(label);
            fieldPanel.add(textField);
        }

        var addButton = new JButton("Add");
        var deleteButton = new JButton("Delete");
        var displayButton = new JButton("Display");
        var clearButton = new JButton("Clear");
        var saveButton = new JButton("Save");

        buttonPanel.setBorder(createEmptyBorder(12, 0, 12, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);

        addButton.addActionListener(e -> {
            //TODO: Add validation
            var car = new Car(textFieldMake.getText(),
                    textFieldModel.getText(),
                    Integer.parseInt(textFieldYear.getText()),
                    textFieldColor.getText(),
                    textFieldVin.getText());
            carArrayList.add(car);
            statusLabel.setText("Added " + car);
        });

        deleteButton.addActionListener(e -> {
            var textFieldVinText = textFieldVin.getText();
            carArrayList.removeIf(car -> car.vin().equals(textFieldVinText));
            statusLabel.setText("Deleted " + textFieldVinText);
        });

        displayButton.addActionListener(e -> {
            for (Car car : carArrayList) {
                out.println(car);
            }
            var popupFrame = new JFrame("Car Inventory List");
            var carListPanel = new JPanel();
            var carListLabel = new JLabel();


            carListLabel.setText("Car List");
            carListPanel.add(carListLabel);
            carListPanel.setLayout(new BoxLayout(carListPanel, BoxLayout.Y_AXIS));
            carListPanel.setBorder(createEmptyBorder(12, 12, 12, 12));

            for (Car car : carArrayList) {
                var carLabel = new JLabel();
                var formattedCarInfo = """
                        <p><b>Vin: %s</b></p>
                        <p>Make: %s,
                        Model: %s,
                        Year: %d,
                        Color: %s</p>
                        """.formatted(car.vin(), car.make(), car.model(), car.year(), car.color());
                carLabel.setText("<html>" + formattedCarInfo + "</html>");
                carListPanel.add(carLabel);
            }

            popupFrame.add(carListPanel);
            popupFrame.setMinimumSize(new Dimension(500, frame.getPreferredSize().height));
            int x = frame.getLocation().x + frame.getWidth() + 50;
            int y = frame.getLocation().y - 50;
            popupFrame.setLocation(x, y);
            popupFrame.pack();
            popupFrame.setVisible(true);
        });

        clearButton.addActionListener(e -> {
            textFieldMake.setText("");
            textFieldModel.setText("");
            textFieldYear.setText("");
            textFieldColor.setText("");
            statusLabel.setText("Cleared");
        });

        saveButton.addActionListener(e -> {
            var file = new File(String.format("%s/Desktop/car_list.csv", System.getProperty("user.home")));

            try {
                var writer = new FileWriter(file);
                writer.write("Vin,Make,Model,Year,Color\n");
                var stringBuilder = new StringBuilder();
                for (Car car : carArrayList) {
                    stringBuilder
                            .append(car.vin()).append(",")
                            .append(car.make()).append(",")
                            .append(car.model()).append(",")
                            .append(car.year()).append(",")
                            .append(car.color()).append("\n");
                    writer.write(stringBuilder.toString());
                }
                writer.close();

                JOptionPane.showMessageDialog(frame, "Car list saved to " + file.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving car list to CSV file.");
            }
        });


        statusPanel.add(statusLabel);

        var container = new JPanel(new BorderLayout());
        container.add(fieldPanel, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
        container.add(statusPanel, BorderLayout.SOUTH);

        var panel = new JPanel();
        panel.setBorder(createEmptyBorder(10, 10, 10, 10));
        panel.add(container);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(550, frame.getPreferredSize().height));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}