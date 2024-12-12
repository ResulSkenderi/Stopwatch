import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchGUI {
    private JFrame frame;
    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;

    private boolean running = false;
    private long startTime = 0;
    private long elapsedTime = 0;
    private Timer timer;

    public StopwatchGUI() {
        // Fenster erstellen
        frame = new JFrame("Stoppuhr");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Zeit-Label
        timeLabel = new JLabel("00:00:000", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(timeLabel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button-Listener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        // Timer für die Echtzeitanzeige
        timer = new Timer(10, new ActionListener() { // Aktualisierung alle 10 ms
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });

        // Fenster sichtbar machen
        frame.setVisible(true);
    }

    private void start() {
        if (!running) {
            running = true;
            startTime = System.currentTimeMillis() - elapsedTime;
            timer.start();
        }
    }

    private void stop() {
        if (running) {
            running = false;
            elapsedTime = System.currentTimeMillis() - startTime;
            timer.stop();
        }
    }

    private void reset() {
        running = false;
        startTime = 0;
        elapsedTime = 0;
        timer.stop();
        timeLabel.setText("00:00:000");
    }

    private void updateDisplay() {
        if (running) {
            long currentTime = System.currentTimeMillis() - startTime;
            long minutes = (currentTime / (1000 * 60)) % 60;
            long seconds = (currentTime / 1000) % 60;
            long milliseconds = currentTime % 1000;
            timeLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
        }
    }

    public static void main(String[] args) {
        new StopwatchGUI();
    }
}