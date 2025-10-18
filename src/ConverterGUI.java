import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterGUI extends JFrame {

    private JTextField lbsField;
    private JTextField kgField;
    private JLabel lbsLabel;
    private JLabel kgLabel;
    private JButton toKgButton;
    private JButton toLbsButton;
    private JButton exitButton;

    public ConverterGUI() {
        setTitle("Pounds ↔ Kilograms Converter");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ✅ Load Kidney Awareness image from src/images folder
        java.net.URL imageUrl = getClass().getResource("/images/Kidney-Disease-Awareness-Green-Ribbon-Heart-Art-Painting-1024x683.jpg");
        if (imageUrl == null) {
            JOptionPane.showMessageDialog(this,
                    "Background image not found. Make sure it's in src/images/",
                    "Image Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        ImageIcon backgroundIcon = new ImageIcon(imageUrl);
        // Scale image to window size
        Image scaledImage = backgroundIcon.getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel); // Set background

        // ✅ Transparent overlay panel for content
        JPanel overlayPanel = new JPanel(new BorderLayout());
        overlayPanel.setOpaque(false);
        backgroundLabel.add(overlayPanel);

        // ✅ Center Panel: labels and text fields (semi-transparent)
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 100)); // semi-transparent black
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        centerPanel.setOpaque(false);

        // ✅ Button Panel (semi-transparent)
        JPanel buttonPanel = new JPanel(new FlowLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setOpaque(false);

        // ✅ Labels and Text Fields
        lbsLabel = new JLabel("Pounds (lbs):");
        kgLabel = new JLabel("Kilograms (kg):");
        lbsField = new JTextField();
        kgField = new JTextField();

        lbsLabel.setForeground(Color.WHITE);
        kgLabel.setForeground(Color.WHITE);
        lbsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        kgLabel.setFont(new Font("Arial", Font.BOLD, 16));

        lbsField.setBackground(new Color(255, 255, 255, 180));
        kgField.setBackground(new Color(255, 255, 255, 180));
        lbsField.setFont(new Font("Arial", Font.PLAIN, 14));
        kgField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add components to center panel
        centerPanel.add(lbsLabel);
        centerPanel.add(lbsField);
        centerPanel.add(kgLabel);
        centerPanel.add(kgField);
        overlayPanel.add(centerPanel, BorderLayout.CENTER);

        // ✅ Buttons
        toKgButton = new JButton("Convert lbs → kg");
        toLbsButton = new JButton("Convert kg → lbs");
        exitButton = new JButton("Exit Program");

        // ✅ Button styles
        toKgButton.setBackground(new Color(255, 255, 255, 200));
        toLbsButton.setBackground(new Color(255, 255, 255, 200));
        exitButton.setBackground(new Color(255, 0, 0, 180));
        toKgButton.setFont(new Font("Arial", Font.BOLD, 13));
        toLbsButton.setFont(new Font("Arial", Font.BOLD, 13));
        exitButton.setFont(new Font("Arial", Font.BOLD, 13));

        // Add buttons to panel
        buttonPanel.add(toKgButton);
        buttonPanel.add(toLbsButton);
        buttonPanel.add(exitButton);
        overlayPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ✅ Button Actions
        toKgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double lbs = Double.parseDouble(lbsField.getText());
                    double kg = lbs * 0.45359237;
                    kgField.setText(String.format("%.4f", kg));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ConverterGUI.this, "Please enter a valid number for pounds.");
                }
            }
        });

        toLbsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double kg = Double.parseDouble(kgField.getText());
                    double lbs = kg / 0.45359237;
                    lbsField.setText(String.format("%.4f", lbs));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ConverterGUI.this, "Please enter a valid number for kilograms.");
                }
            }
        });

        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    ConverterGUI.this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // ✅ Display setup
        setLocationRelativeTo(null);
        setVisible(true);
    }
}