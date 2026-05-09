package shopix;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminFrame extends JFrame {
    private ProductManager pm = ProductManager.getInstance();
    private JTable productTable;
    private DefaultTableModel tableModel;

    public AdminFrame() {
        setTitle("Shopix - Admin Paneli");
        setSize(600, 500); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Üst Panel: Giriş Alanları ---
       
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField idField = new JTextField();
        idField.setEditable(false); 
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();

        inputPanel.add(new JLabel("  Ürün ID (Otomatik):"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("  Ürün İsmi:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("  Fiyat:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("  Stok Adedi:"));
        inputPanel.add(stockField);

        // --- Orta Panel: Tablo ---
        String[] columnNames = {"ID", "Ürün İsmi", "Fiyat", "Stok"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // --- Alt Panel: Butonlar ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Ekle");
        JButton deleteButton = new JButton("Sil");
        JButton updateButton = new JButton("Güncelle");
        JButton listButton = new JButton("Listele");
        JButton clearButton = new JButton("Temizle");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(listButton);
        buttonPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // =========================
        // BUTON AKSİYONLARI
        // =========================

        // --- EKLE ---
        addButton.addActionListener(e -> {
            try {
              
                Product p = new Product(
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(stockField.getText()),
                    "Genel"
                );
                pm.addProduct(p);
                JOptionPane.showMessageDialog(this, "Ürün başarıyla eklendi! Listele butonuna basarak görebilirsiniz.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Hata: Fiyat ve Stok sayısal olmalıdır!");
            }
        });

        // --- SİL ---
        deleteButton.addActionListener(e -> {
            try {
                if(idField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lütfen tablodan silinecek ürünü seçin!");
                    return;
                }
                int productId = Integer.parseInt(idField.getText());
                pm.removeProduct(productId);
                JOptionPane.showMessageDialog(this, "Ürün silindi!");
                listButton.doClick(); // Listeyi otomatik yenile
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Silme hatası: " + ex.getMessage());
            }
        });

        // --- GÜNCELLE ---
        updateButton.addActionListener(e -> {
            try {
                if(idField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lütfen güncellenecek ürünü tablodan seçin!");
                    return;
                }
                Product p = new Product(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(stockField.getText()),
                    "Genel"
                );
                pm.updateProduct(p);
                JOptionPane.showMessageDialog(this, "Ürün güncellendi!");
                listButton.doClick();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Güncelleme hatası!");
            }
        });

        // --- LİSTELE ---
        listButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            for (Product p : pm.getAllProducts()) {
                Object[] row = {p.getProductId(), p.getProductName(), p.getPrice(), p.getStockQuantity()};
                tableModel.addRow(row);
            }
        });

        // --- TEMİZLE ---
        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            priceField.setText("");
            stockField.setText("");
        });

        // Tablo seçim dinleyicisi
        productTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                int row = productTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                priceField.setText(tableModel.getValueAt(row, 2).toString());
                stockField.setText(tableModel.getValueAt(row, 3).toString());
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}