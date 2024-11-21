package com.example.swing.view;

import com.example.swing.controller.AdminMenuController;
import com.example.swing.model.Dish;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class AdminMenuPage {
    private JFrame frame;
    private AdminMenuController controller;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField detailsField;
    private JTextField imageURLField;
    private JTable table;
    private DefaultTableModel tableModel;
    private int editingRow = -1;
    private boolean isEditMode = false;
    private JButton addButton;

    public AdminMenuPage(AdminMenuController controller) {
        this.controller = controller;
    }

    public void initialize() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    // create and show the GUI  
    private void createAndShowGUI() {
        frame = new JFrame("Admin Menu");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize the components
        initComponents();

        // load the dishes
        loadDishes();

        frame.setVisible(true);
    }

    // initialize the components    
    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(createInputPanel(), BorderLayout.NORTH);
        panel.add(createTablePanel(), BorderLayout.CENTER);

        frame.add(panel);
    }

    // create the input panel
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        nameField = new JTextField();
        priceField = new JTextField();
        detailsField = new JTextField();
        imageURLField = new JTextField();

        inputPanel.add(new JLabel("Dish Name"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Details"));
        inputPanel.add(detailsField);
        inputPanel.add(new JLabel("Image URL"));
        inputPanel.add(imageURLField);

        addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            if (isEditMode) {
                updateDish();
            } else {
                addDish();
            }
        });

        inputPanel.add(new JLabel()); // Placeholder
        inputPanel.add(addButton);

        return inputPanel;
    }

    private JScrollPane createTablePanel() {
        String[] columnNames = {"Dish", "Price", "Details", "Image", "Action"};
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        table.setRowHeight(35);
        table.getColumnModel().getColumn(4).setCellRenderer(new ActionCellRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new ActionCellEditor(this));

        return new JScrollPane(table);
    }

    // load the dishes
    private void loadDishes() {
        List<Dish> dishes = controller.getAllDishes();
        tableModel.setRowCount(0);
        for (Dish d : dishes) {
            tableModel.addRow(new Object[]{d.getName(), d.getPrice(), d.getDescription(), d.getImageUrl(), ""});
        }
    }

    // add a dish   
    private void addDish() {
        controller.addDish(nameField.getText(), Double.parseDouble(priceField.getText()), detailsField.getText(), imageURLField.getText());
        clearInputFields();
        loadDishes();
    }

    // edit a dish  
    private void editDish(int row) {
        Dish dish = controller.getAllDishes().get(row);
        nameField.setText(dish.getName());
        priceField.setText(String.valueOf(dish.getPrice()));
        detailsField.setText(dish.getDescription());
        imageURLField.setText(dish.getImageUrl());

        isEditMode = true;
        editingRow = row;
        addButton.setText("Update");
    }

    // delete a dish    
    private void deleteDish(int row) {
        Dish dish = controller.getAllDishes().get(row);
        controller.deleteDish(dish.getItemId());
        loadDishes();
    }

    // update a dish
    private void updateDish() {
        if (editingRow >= 0) {
            Dish dish = controller.getAllDishes().get(editingRow);
            controller.updateDish(dish.getItemId(), nameField.getText(), Double.parseDouble(priceField.getText()), detailsField.getText(), imageURLField.getText());

            isEditMode = false;
            editingRow = -1;
            addButton.setText("Add");
            clearInputFields();
            loadDishes();
        }
    }

    private void clearInputFields() {
        nameField.setText("");
        priceField.setText("");
        detailsField.setText("");
        imageURLField.setText("");
    }

    // action cell renderer
    static class ActionCellRenderer extends JPanel implements TableCellRenderer {
        private final JButton deleteButton;
        private final JButton editButton;

        public ActionCellRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            deleteButton = new JButton("Delete");
            editButton = new JButton("Edit");

            deleteButton.setPreferredSize(new Dimension(65, 25));
            editButton.setPreferredSize(new Dimension(65, 25));

            deleteButton.setForeground(new Color(242, 88, 53));
            editButton.setForeground(new Color(87, 154, 242));

            add(deleteButton);
            add(editButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // action cell editor   
    static class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton deleteButton;
        private final JButton editButton;
        private int currentRow;
        private AdminMenuPage adminMenuPage;

        public ActionCellEditor(AdminMenuPage adminMenuPage) {
            this.adminMenuPage = adminMenuPage;
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            deleteButton = new JButton("Delete");
            editButton = new JButton("Edit");

            deleteButton.setPreferredSize(new Dimension(65, 25));
            editButton.setPreferredSize(new Dimension(65, 25));

            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete this dish?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    adminMenuPage.deleteDish(currentRow);
                }
                fireEditingStopped();
            });

            editButton.addActionListener(e -> {
                adminMenuPage.editDish(currentRow);
                fireEditingStopped();
            });

            panel.add(deleteButton);
            panel.add(editButton);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.currentRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }
}