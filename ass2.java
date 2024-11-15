import javax.swing.*; 
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ass2 extends JFrame {
    private JTextField idField, nameField, addressField, contactField;
    private JRadioButton maleButton, femaleButton;
    private JTable table;
    private DefaultTableModel model;

    public ass2() {
        setTitle("Registration Form");
        setSize(800, 400); // Increased size for better space
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Light blue background for the entire frame
        getContentPane().setBackground(new Color(173, 216, 230));

        // Left Panel with Registration Form (no background, just form fields)
        JPanel leftPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        leftPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 120, 215), 1, true), "Registration Form"));
        leftPanel.setBackground(Color.WHITE);  // White background for the left panel

        // Add fields to the form
        leftPanel.add(new JLabel("ID"));
        idField = new JTextField();
        leftPanel.add(idField);

        leftPanel.add(new JLabel("Name"));
        nameField = new JTextField();
        leftPanel.add(nameField);

        leftPanel.add(new JLabel("Gender"));
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        maleButton.setBackground(Color.WHITE);
        femaleButton.setBackground(Color.WHITE);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        JPanel genderPanel = new JPanel();
        genderPanel.setBackground(Color.WHITE);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        leftPanel.add(genderPanel);

        leftPanel.add(new JLabel("Address"));
        addressField = new JTextField();
        leftPanel.add(addressField);

        leftPanel.add(new JLabel("Contact"));
        contactField = new JTextField();
        leftPanel.add(contactField);

        // Combo box (Dropdown Menu) on the left side
        leftPanel.add(new JLabel("Select Option"));
        JComboBox<String> dropdownMenu = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        leftPanel.add(dropdownMenu);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonListener());
        leftPanel.add(registerButton);

        add(leftPanel, BorderLayout.WEST);

        // Right Panel (Table with Scrollbars)
        model = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Address", "Contact"}, 0);
        table = new JTable(model);
        table.setGridColor(new Color(0, 120, 215)); // Blue grid lines in table
        table.setBorder(new LineBorder(new Color(0, 120, 215), 2, true)); // Dark blue border around the table
        table.setBackground(Color.WHITE); // White background for the table cells

        // Table Scroll Pane (with vertical and horizontal scroll)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(new Color(0, 120, 215), 2, true)); // Blue border around the scroll pane
        add(scrollPane, BorderLayout.CENTER);

        // Horizontal and Vertical Scrollbars
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);  // Scroll step for vertical scrollbar
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUnitIncrement(16);  // Scroll step for horizontal scrollbar
    }

    // Register Button Action Listener
    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String name = nameField.getText();
            String gender = maleButton.isSelected() ? "Male" : "Female";
            String address = addressField.getText();
            String contact = contactField.getText();

            // Database Insertion (Mocked for this example)
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "");
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO registrations (ID, Name, Gender, Address, Contact) VALUES (?, ?, ?, ?, ?)")) {

                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, gender);
                stmt.setString(4, address);
                stmt.setString(5, contact);
                stmt.executeUpdate();

                // Display in table
                model.addRow(new Object[]{id, name, gender, address, contact});
                JOptionPane.showMessageDialog(null, "Registration Successful!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error connecting to database.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ass2().setVisible(true);
        });
    }
}
