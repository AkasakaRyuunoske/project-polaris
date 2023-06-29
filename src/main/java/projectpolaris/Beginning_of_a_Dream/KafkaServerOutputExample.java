package projectpolaris.Beginning_of_a_Dream;

import projectpolaris.Beginning_of_a_Dream.Components.CustomWindowListener;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KafkaServerOutputExample extends JFrame {

    public KafkaServerOutputExample(String[] commands) {
        String[] commands_kafka = {"D:\\kafka_2.12-3.4.0\\bin\\windows\\kafka-server-start.bat ", "D:\\kafka_2.12-3.4.0\\config\\server.properties"};

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));


        setTitle("Kafka Server Output");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(2,1));
        addWindowListener(new CustomWindowListener());

        JTextArea screen_kafka = new JTextArea();
        screen_kafka.setBounds(0,0, 600, 200);
        screen_kafka.setEditable(false);

        JTextArea screen_zookeeper = new JTextArea();
        screen_zookeeper.setBounds(300,300, 600, 200);
        screen_zookeeper.setEditable(false);

        JButton button = new JButton("Yey");
        button.setBounds(0,0,100,100);
        button.setVisible(true);

        JScrollPane scrollPane_zookeeper = new JScrollPane(screen_zookeeper);
        scrollPane_zookeeper.setBounds(300,300,600,200);

        JScrollPane scrollPane_kafka = new JScrollPane(screen_kafka);
        scrollPane_kafka.createHorizontalScrollBar();
        scrollPane_kafka.createVerticalScrollBar();
        scrollPane_kafka.setBounds(300,300,600,200);

        panel.add(scrollPane_zookeeper);
        panel.add(scrollPane_kafka);

//        panel.setVisible(true);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.MAGENTA);

        JButton button1 = new JButton("Yey");
        button1.setBounds(0,0,100,100);

        jPanel.add(button1);

        add(jPanel);
        add(panel);
//        add(scrollPane_zookeeper);
//        add(scrollPane_kafka);

//        getContentPane().add(button, BorderLayout.WEST);

        setVisible(true);

        redirectKafkaServerOutput(screen_kafka, commands_kafka);
        redirectKafkaServerOutput(screen_zookeeper, commands);
    }

//    public KafkaServerOutputExample(String[] commands) {
//        String[] commands_kafka = {"D:\\kafka_2.12-3.4.0\\bin\\windows\\kafka-server-start.bat ", "D:\\kafka_2.12-3.4.0\\config\\server.properties"};
//
//        JPanel panel = new JPanel();
//
//        setTitle("Kafka Server Output");
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 600);
//        setLayout(new GridLayout(1,2));
//        addWindowListener(new CustomWindowListener());
//
//        JTextArea screen_kafka = new JTextArea();
//        screen_kafka.setBounds(0,0, 600, 200);
//        screen_kafka.setEditable(false);
//
//        JTextArea screen_zookeeper = new JTextArea();
//        screen_zookeeper.setBounds(300,300, 600, 200);
//        screen_zookeeper.setEditable(false);
//
//        JButton button = new JButton("Yey");
//        button.setBounds(0,0,100,100);
//        button.setVisible(true);
//
//        JScrollPane scrollPane_zookeeper = new JScrollPane(screen_zookeeper);
//        scrollPane_zookeeper.setBounds(300,300,600,200);
//
//        JScrollPane scrollPane_kafka = new JScrollPane(screen_kafka);
//        scrollPane_kafka.createHorizontalScrollBar();
//        scrollPane_kafka.createVerticalScrollBar();
//        scrollPane_kafka.setBounds(300,300,600,200);
//
//        add(scrollPane_zookeeper);
//        add(scrollPane_kafka);
//
////        getContentPane().add(button, BorderLayout.WEST);
//
//        setVisible(true);
//
//        redirectKafkaServerOutput(screen_kafka, commands_kafka);
//        redirectKafkaServerOutput(screen_zookeeper, commands);
//    }


    private void redirectKafkaServerOutput(JTextArea screen, String[] commands) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();

            Thread outputReaderThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        appendText(line, screen);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            outputReaderThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendText(String text, JTextArea screen) {
        SwingUtilities.invokeLater(() -> {
            screen.append(text + "\n");
            screen.setCaretPosition(screen.getDocument().getLength());
        });
    }

//    public KafkaServerOutputExample(String[] commands) {
//        setTitle("Kafka Server Output");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 600);
//
//        JTextArea textArea = new JTextArea();
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//
//        setVisible(true);
//
//        redirectKafkaServerOutput(commands, textArea);
//
//        redirectKafkaServerOutput(commands, textArea);
//    }
//
//    private void redirectKafkaServerOutput(String[] commands, JTextArea screen) {
//        System.out.println("Was called");
//
//        try {
//
//            ProcessBuilder processBuilder = new ProcessBuilder(commands);
//            processBuilder.redirectErrorStream(true);
//
//            Process process = processBuilder.start();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                String finalLine = line;
//                SwingUtilities.invokeLater(() -> {
//                    screen.append(finalLine + "\n");
//                    screen.setCaretPosition(screen.getDocument().getLength());
//                });
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

        String[] commands_kafka = {"D:\\kafka_2.12-3.4.0\\bin\\windows\\kafka-server-start.bat ", "D:\\kafka_2.12-3.4.0\\config\\server.properties"};
        String[] commands_zookeeper = {"D:\\kafka_2.12-3.4.0\\bin\\windows\\zookeeper-server-start.bat ", "D:\\kafka_2.12-3.4.0\\config\\zookeeper.properties"};

        SwingUtilities.invokeLater(() -> new KafkaServerOutputExample(commands_zookeeper));
//        SwingUtilities.invokeLater(() -> new KafkaServerOutputExample(commands_kafka));
    }
}
