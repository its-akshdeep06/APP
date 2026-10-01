import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ================= MODEL =================
class ServiceModel {

    private String vehicleNumber;
    private String vehicleType;
    private int totalCost;

    public void calculateCost(String number, String type,
                              boolean general, boolean oil,
                              boolean brake, boolean battery) {

        vehicleNumber = number;
        vehicleType = type;
        totalCost = 0;

        if (general)
            totalCost += 1000;

        if (oil)
            totalCost += 800;

        if (brake)
            totalCost += 1200;

        if (battery)
            totalCost += 500;
    }

    public int getTotalCost() {
        return totalCost;
    }
}


// ================= VIEW =================
class ServiceView extends JFrame {

    JTextField vehicleNumber;

    JRadioButton twoWheeler, car;

    JCheckBox generalService;
    JCheckBox oilChange;
    JCheckBox brakeService;
    JCheckBox batteryCheck;

    JButton calculateButton;

    JLabel resultLabel;

    ServiceView() {

        setTitle("Vehicle Service Cost Estimator");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        // Vehicle Registration Number
        add(new JLabel("Registration Number:"));
        vehicleNumber = new JTextField();
        add(vehicleNumber);

        // Vehicle Type
        add(new JLabel("Vehicle Type:"));

        JPanel typePanel = new JPanel();

        twoWheeler = new JRadioButton("Two Wheeler");
        car = new JRadioButton("Car");

        ButtonGroup group = new ButtonGroup();
        group.add(twoWheeler);
        group.add(car);

        typePanel.add(twoWheeler);
        typePanel.add(car);

        add(typePanel);

        // General Service
        add(new JLabel("Service:"));
        generalService = new JCheckBox("General Service - Rs.1000");
        add(generalService);

        // Oil Change
        add(new JLabel(""));
        oilChange = new JCheckBox("Oil Change - Rs.800");
        add(oilChange);

        // Brake Service
        add(new JLabel(""));
        brakeService = new JCheckBox("Brake Service - Rs.1200");
        add(brakeService);

        // Battery Check
        add(new JLabel(""));
        batteryCheck = new JCheckBox("Battery Check - Rs.500");
        add(batteryCheck);

        // Calculate Button
        add(new JLabel(""));
        calculateButton = new JButton("Calculate Cost");
        add(calculateButton);

        // Result
        add(new JLabel("Total Service Cost:"));
        resultLabel = new JLabel("Rs. 0");
        add(resultLabel);

        setVisible(true);
    }
}


// ================= CONTROLLER =================
class ServiceController {

    ServiceModel model;
    ServiceView view;

    ServiceController(ServiceModel model, ServiceView view) {

        this.model = model;
        this.view = view;

        view.calculateButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                calculateCost();
            }
        });
    }

    void calculateCost() {

        String number = view.vehicleNumber.getText();

        if (number.isEmpty()) {
            JOptionPane.showMessageDialog(
                view,
                "Please enter the vehicle registration number."
            );
            return;
        }

        String type = "";

        if (view.twoWheeler.isSelected())
            type = "Two Wheeler";
        else if (view.car.isSelected())
            type = "Car";
        else {
            JOptionPane.showMessageDialog(
                view,
                "Please select the vehicle type."
            );
            return;
        }

        // Send selections to Model
        model.calculateCost(
            number,
            type,
            view.generalService.isSelected(),
            view.oilChange.isSelected(),
            view.brakeService.isSelected(),
            view.batteryCheck.isSelected()
        );

        // Display result
        view.resultLabel.setText(
            "Rs. " + model.getTotalCost()
        );
    }
}


// ================= MAIN =================
public class VehicleServiceEstimator {

    public static void main(String[] args) {

        ServiceModel model = new ServiceModel();

        ServiceView view = new ServiceView();

        new ServiceController(model, view);
    }
}
