import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ass2 {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Registration Form");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        
        JLabel titleLabel = new JLabel("Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(20, 10, 200, 30);
        frame.add(titleLabel);

     
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(20, 50, 100, 20);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 50, 150, 25);
        frame.add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 90, 100, 20);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 90, 150, 25);
        frame.add(nameField);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(20, 130, 100, 20);
        frame.add(genderLabel);

        JRadioButton maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(120, 130, 70, 25);
        maleRadio.setBackground(new Color(240, 240, 240));
        frame.add(maleRadio);

        JRadioButton femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(190, 130, 80, 25);
        femaleRadio.setBackground(new Color(240, 240, 240)); 
        frame.add(femaleRadio);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(20, 170, 100, 20);
        frame.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(120, 170, 150, 25);
        frame.add(addressField);

        JLabel contactLabel = new JLabel("Contact");
        contactLabel.setBounds(20, 210, 100, 20);
        frame.add(contactLabel);

        JTextField contactField = new JTextField();
        contactField.setBounds(120, 210, 150, 25);
        frame.add(contactField);

        
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(40, 270, 100, 30);
        exitButton.setBackground(new Color(200, 220, 240)); 
        frame.add(exitButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(170, 270, 100, 30);
        registerButton.setBackground(new Color(200, 220, 240));
        frame.add(registerButton);

        
        String[] columns = {"ID", "Name", "Gender", "Address", "Contact"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 50, 370, 250);
        frame.add(scrollPane);

        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
            }
        });

        registerButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");
                String address = addressField.getText();
                String contact = contactField.getText();

                
                tableModel.addRow(new Object[]{id, name, gender, address, contact});

               
                idField.setText("");
                nameField.setText("");
                genderGroup.clearSelection();
                addressField.setText("");
                contactField.setText("");
            }
        });

       
        frame.setVisible(true);
    }
}
