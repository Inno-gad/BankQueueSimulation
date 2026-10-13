import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SimulationFrame extends JFrame {

    private final JTextField minInterArrivalField = new JTextField("1", 6);
    private final JTextField maxInterArrivalField = new JTextField("7", 6);
    private final JTextField minServiceField = new JTextField("1", 6);
    private final JTextField maxServiceField = new JTextField("6", 6);
    private final JTextField customersField = new JTextField("10", 6);

    private final DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"Customer", "Inter-arrival", "Arrival", "Service",
                    "Start", "Departure", "Waiting", "Time in System"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JLabel summaryLabel = new JLabel("Run a simulation to see results.");

    public SimulationFrame() {
        super("Bank Queue Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(buildInputPanel(), BorderLayout.NORTH);
        add(new JScrollPane(new JTable(tableModel)), BorderLayout.CENTER);

        summaryLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        add(summaryLabel, BorderLayout.SOUTH);

        setSize(900, 500);
        setLocationRelativeTo(null);
    }

    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));

        panel.add(new JLabel("Min inter-arrival:"));
        panel.add(minInterArrivalField);
        panel.add(new JLabel("Max inter-arrival:"));
        panel.add(maxInterArrivalField);
        panel.add(new JLabel("Min service:"));
        panel.add(minServiceField);
        panel.add(new JLabel("Max service:"));
        panel.add(maxServiceField);
        panel.add(new JLabel("Customers:"));
        panel.add(customersField);

        JButton runButton = new JButton("Run Simulation");
        runButton.addActionListener(e -> runSimulation());
        panel.add(runButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            summaryLabel.setText("Run a simulation to see results.");
        });
        panel.add(clearButton);

        return panel;
    }

    private void runSimulation() {
        try {
            double minInter = Double.parseDouble(minInterArrivalField.getText().trim());
            double maxInter = Double.parseDouble(maxInterArrivalField.getText().trim());
            double minServ = Double.parseDouble(minServiceField.getText().trim());
            double maxServ = Double.parseDouble(maxServiceField.getText().trim());
            int count = Integer.parseInt(customersField.getText().trim());

            BankQueueSimulation simulation =
                    new BankQueueSimulation(minInter, maxInter, minServ, maxServ, count);

            List<Customer> customers = simulation.simulate();
            showResults(customers);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers in all fields.\n"
                            + "Number of customers must be a whole number.",
                    "Invalid input", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Invalid input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showResults(List<Customer> customers) {
        tableModel.setRowCount(0);

        double totalWait = 0, maxWait = 0, totalService = 0, totalSystem = 0;
        int waited = 0;

        for (Customer c : customers) {
            tableModel.addRow(new Object[]{
                    c.getCustomerNumber(),
                    String.format("%.2f", c.getInterArrivalTime()),
                    String.format("%.2f", c.getArrivalTime()),
                    String.format("%.2f", c.getServiceTime()),
                    String.format("%.2f", c.getServiceStartTime()),
                    String.format("%.2f", c.getDepartureTime()),
                    String.format("%.2f", c.getWaitingTime()),
                    String.format("%.2f", c.getTimeInSystem())
            });

            totalWait += c.getWaitingTime();
            maxWait = Math.max(maxWait, c.getWaitingTime());
            totalService += c.getServiceTime();
            totalSystem += c.getTimeInSystem();
            if (c.getWaitingTime() > 0) {
                waited++;
            }
        }

        // TODO: once Member 4's QueueStatistics is ready, replace this block
        // with calls to it so the numbers come from one place.
        int n = customers.size();
        summaryLabel.setText(String.format(
                "Avg wait: %.2f | Max wait: %.2f | Avg service: %.2f | "
                        + "Avg time in system: %.2f | Waited: %.0f%%",
                totalWait / n, maxWait, totalService / n,
                totalSystem / n, 100.0 * waited / n));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimulationFrame().setVisible(true));
    }
}