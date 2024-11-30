package com.example.swing.view;

import com.example.swing.utils.ButtonUtils;
import com.example.swing.controller.AdminOrderController;
import com.example.swing.model.Dish;
import com.example.swing.model.Order;
import com.example.swing.model.OrderItem;
import com.example.swing.model.OrderStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class AdminOrderManagementPage {
    private JFrame frame;
    private AdminOrderController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private int editingRow = -1;
    private boolean isEditMode = false;

    public AdminOrderManagementPage(AdminOrderController controller) {
        this.controller = controller;
    }

    public void initialize() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    // create and show the GUI
    private void createAndShowGUI() {
        frame = new JFrame("Admin Order Management");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // initialize the components
        initComponents();

        // load the orders
        loadOrders();

        frame.setVisible(true);
    }

    // initialize the components
    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(createTablePanel(), BorderLayout.CENTER);

        frame.add(panel);
    }

    // create the table panel
    private JScrollPane createTablePanel() {
        String[] columnNames = { "Customer", "Dish", "Order Time", "Status", "Action" };
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 4; // Only allow editing in the Action column
            }
        };

        table.setRowHeight(35);
        table.getColumnModel().getColumn(1).setCellRenderer(new DishButtonRenderer());
        table.getColumnModel().getColumn(1).setCellEditor(new DishButtonEditor(this));
        table.getColumnModel().getColumn(4).setCellRenderer(new ActionCellRenderer());
        table.getColumnModel().getColumn(4).setCellEditor(new ActionCellEditor(this));

        return new JScrollPane(table);
    }

    // load the orders
    private void loadOrders() {
        List<Order> orders = controller.getAllOrders();
        tableModel.setRowCount(0);
        for (Order o : orders) {
            // TODO: Customer name and status are hardcoded for now
            // tableModel.addRow(new Object[] {o.getCustomerName(), o.getOrderItems(),
            // o.getOrderDate(), o.getStatus(), ""});
            tableModel.addRow(new Object[] { "Jack", o.getOrderItems(), o.getOrderDate(), o.getStatus(), "" });
        }
    }

    // update an order's status
    private void updateOrderStatus(int row, String status) {
        Order order = controller.getAllOrders().get(row);
        controller.updateOrderStatus(order.getOrderId(), status);
        loadOrders();
    }

    // action cell renderer
    static class ActionCellRenderer extends JPanel implements TableCellRenderer {
        private final JButton updateButton;

        public ActionCellRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            updateButton = ButtonUtils.createButton("Update Status", new Color(87, 154, 242));
            updateButton.setPreferredSize(new Dimension(150, 30));

            add(updateButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            return this;
        }
    }

    // action cell editor
    static class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton updateButton;
        private int currentRow;
        private AdminOrderManagementPage adminOrderManagementPage;

        public ActionCellEditor(AdminOrderManagementPage adminOrderManagementPage) {
            this.adminOrderManagementPage = adminOrderManagementPage;
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            updateButton = ButtonUtils.createButton("Update Status", new Color(87, 154, 242));
            updateButton.setPreferredSize(new Dimension(150, 30));

            updateButton.addActionListener(e -> {
                // options from order status
                String[] options = Arrays.stream(OrderStatus.values()).map(OrderStatus::getValue)
                        .toArray(String[]::new);
                String status = (String) JOptionPane.showInputDialog(null, "Update Order Status:", "Status Update",
                        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (status != null) {
                    adminOrderManagementPage.updateOrderStatus(currentRow, status);
                }
                fireEditingStopped();
            });

            panel.add(updateButton);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            this.currentRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }

    // dish button renderer
    static class DishButtonRenderer extends JPanel implements TableCellRenderer {
        private final JButton viewButton;

        public DishButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            viewButton = ButtonUtils.createButton("View Order Items", new Color(87, 154, 242));
            viewButton.setPreferredSize(new Dimension(150, 30));
            add(viewButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            return this;
        }
    }

    // dish button editor
    static class DishButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private final JButton viewButton;
        private int currentRow;
        private AdminOrderManagementPage adminOrderManagementPage;

        public DishButtonEditor(AdminOrderManagementPage adminOrderManagementPage) {
            this.adminOrderManagementPage = adminOrderManagementPage;
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            viewButton = ButtonUtils.createButton("View Order Items", new Color(87, 154, 242));
            viewButton.setPreferredSize(new Dimension(150, 30));

            viewButton.addActionListener(e -> {
                Order order = adminOrderManagementPage.controller.getAllOrders().get(currentRow);
                List<OrderItem> orderItems = order.getOrderItems();
                String[] columnNames = { "Name", "Price", "Quantity" };
                Object[][] data = new Object[orderItems.size()][4];

                for (int i = 0; i < orderItems.size(); i++) {
                    OrderItem dish = orderItems.get(i);
                    System.out.println(dish + "-------- dish");
                    data[i][0] = dish.getName();
                    data[i][1] = dish.getPrice();
                    data[i][2] = dish.getQuantity();
                }
                JTable orderItemsTable = new JTable(data, columnNames);
                orderItemsTable.setFillsViewportHeight(true);
                orderItemsTable.setShowGrid(true);
                orderItemsTable.setGridColor(Color.LIGHT_GRAY);
                orderItemsTable.setRowHeight(25);
                orderItemsTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
                JScrollPane scrollPane = new JScrollPane(orderItemsTable);
                scrollPane.setPreferredSize(new Dimension(500, 250));

                JPanel popupPanel = new JPanel(new BorderLayout());
                popupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                popupPanel.add(scrollPane, BorderLayout.CENTER);

                JLabel titleLabel = new JLabel("Order Details", JLabel.CENTER);
                titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                popupPanel.add(titleLabel, BorderLayout.NORTH);

                JPanel buttonPanel = new JPanel();
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(ev -> SwingUtilities.getWindowAncestor(closeButton).dispose());
                buttonPanel.add(closeButton);
                popupPanel.add(buttonPanel, BorderLayout.SOUTH);

                JDialog dialog = new JDialog((Frame) null, "Order Details", true);
                dialog.getContentPane().add(popupPanel);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                fireEditingStopped();
            });

            panel.add(viewButton);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            this.currentRow = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }
}
