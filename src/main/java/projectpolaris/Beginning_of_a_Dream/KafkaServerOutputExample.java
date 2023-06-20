package projectpolaris.Beginning_of_a_Dream;

import javax.swing.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;

public class KafkaServerOutputExample extends JFrame {

    public KafkaServerOutputExample(String[] commands) {
        setTitle("Kafka Server Output");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JTextArea screen = new JTextArea();
        screen.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(screen);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        redirectKafkaServerOutput(screen, commands);
    }


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
        SwingUtilities.invokeLater(() -> new KafkaServerOutputExample(commands_kafka));
    }
}
