import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryPage extends JFrame {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

    private JPanel contentPane;
    private JTextField txtcat;
    private JComboBox<String> txtstatus;
    private JTable table;

    public Connection connection() {
        try {
        	String url="jdbc:sqlserver://localhost:1433;databaseName=library;encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2;trustStore=C:/Program Files/Java/jdk1.8.0_251/jre/lib/security/cacerts;trustStorePassword=changeit;trustStoreType=jks";

            //String url = "jdbc:sqlserver://localhost:1433;databaseName=library";
            String user = "sa";
            String password = "sql321";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CategoryPage frame = new CategoryPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CategoryPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setTitle("Category");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCategory.setBounds(10, 23, 79, 20);
        contentPane.add(lblCategory);

        txtcat = new JTextField();
        txtcat.setBounds(99, 23, 182, 20);
        contentPane.add(txtcat);
        txtcat.setColumns(10);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStatus.setBounds(10, 54, 79, 20);
        contentPane.add(lblStatus);

        txtstatus = new JComboBox<String>();
        txtstatus.setBounds(99, 54, 182, 20);
        txtstatus.addItem("Active");
        txtstatus.addItem("Deactive");
        contentPane.add(txtstatus);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(10, 91, 89, 23);
        contentPane.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String category = txtcat.getText();
                    String status = txtstatus.getSelectedItem().toString();
                    
                   
                    stmt = conn.prepareStatement("INSERT INTO categories (category, status) VALUES (?, ?)");
                    stmt.setString(1, category);
                    stmt.setString(2, status);
                    int result = stmt.executeUpdate();
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Category added successfully.");
                        refreshTable();
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error adding category.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

			private void clearFields() {
				
				    txtcat.setText("");
				    txtstatus.setSelectedIndex(0);
				

				
			}

			private void refreshTable() {
				try {
	                // Clear the table data
	                DefaultTableModel model = (DefaultTableModel) table.getModel();
	                model.setRowCount(0);

	                // Retrieve data from the database and add it to the table
	                stmt = conn.prepareStatement("SELECT id, category, status FROM categories");
	                ResultSet rs = stmt.executeQuery();
	                while (rs.next()) {
	                    int id = rs.getInt("id");
	                    String category = rs.getString("category");
	                    String status = rs.getString("status");

	                    Object[] row = { id, category, status };
	                    model.addRow(row);
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
				
			}
        });
       


        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(109, 91, 89, 23);
        contentPane.add(btnEdit);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(208, 91, 89, 23);
        contentPane.add(btnDelete);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(307, 91, 89, 23);
        contentPane.add(btnCancel);

        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 136, 564, 214);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Id", "Category", "Status"
            }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        scrollPane.setViewportView(table);

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(485, 361, 89, 23);
        contentPane.add(btnClose);
    }
}
