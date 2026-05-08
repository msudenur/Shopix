package shopix;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminFrame extends JFrame {
    private ProductManager pm = ProductManager.getInstance();
    private JTable productTable;
    private DefaultTableModel tableModel;

    public AdminFrame() {
        setTitle("Admin Panel");
        setSize(600, 500); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout()); // Daha düzenli bir yerleşim için BorderLayout

        // --- Üst Panel: Giriş Alanları ---
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField stockField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("İsim:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Fiyat:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Stok:"));
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

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(listButton);

        // Panelleri Pencereye Ekle
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- BUTON AKSİYONLARI ---

        // EKLE
        addButton.addActionListener(e -> {
            try {
                Product p = new Product(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(stockField.getText()),
                    "Genel"
                );
                pm.addProduct(p);
                JOptionPane.showMessageDialog(this, "Ürün başarıyla eklendi!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Hata: Lütfen geçerli sayısal değerler girin!");
            }
        });

        // SİL
        deleteButton.addActionListener(e -> {
            try {
                pm.removeProduct(Integer.parseInt(idField.getText()));
                JOptionPane.showMessageDialog(this, "Ürün silindi!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Hata: Geçerli bir ID girin!");
            }
        });

        // GÜNCELLE
        updateButton.addActionListener(e -> {
            try {
                Product p = new Product(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(stockField.getText()),
                    "Genel"
                );
                pm.updateProduct(p);
                JOptionPane.showMessageDialog(this, "Ürün güncellendi!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Güncelleme hatası!");
            }
        });

        // LİSTELE (Tabloyu Güncelle)
        listButton.addActionListener(e -> {
            tableModel.setRowCount(0); // Tabloyu temizle
            for (Product p : pm.getAllProducts()) {
                Object[] row = {
                    p.getProductId(),
                    p.getProductName(),
                    p.getPrice(),
                    p.getStockQuantity()
                };
                tableModel.addRow(row);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}