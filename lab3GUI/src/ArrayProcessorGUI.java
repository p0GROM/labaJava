import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ArrayProcessor {
    int[] array;

    ArrayProcessor(int n) {
        this.array = new int[n];
    }

    void sortArray() {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    int binarySearch(int target) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (target < array[middle]) {
                high = middle - 1;
            } else if (target > array[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}

public class ArrayProcessorGUI extends JFrame {
    private JTextField sizeField, elementsField, searchField;
    private JTextArea outputArea;

    public ArrayProcessorGUI() {
        setTitle("Двоичный поиск");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Введите размер массива:"));
        sizeField = new JTextField();
        panel.add(sizeField);

        panel.add(new JLabel("Введите элементы:"));
        elementsField = new JTextField();
        panel.add(elementsField);

        panel.add(new JLabel("Введите число для поиска:"));
        searchField = new JTextField();
        panel.add(searchField);

        JButton processButton = new JButton("Обработать");
        panel.add(processButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        panel.add(new JScrollPane(outputArea));

        add(panel);

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processArray();
            }
        });
    }

    private void processArray() {
        try {
            int n = Integer.parseInt(sizeField.getText());
            String[] elements = elementsField.getText().split("\\s+");
            if (elements.length != n) {
                outputArea.setText("Количество элементов не соответствует размеру.");
                return;
            }

            ArrayProcessor process = new ArrayProcessor(n);
            for (int i = 0; i < n; i++) {
                process.array[i] = Integer.parseInt(elements[i]);
            }

            process.sortArray();

            int target = Integer.parseInt(searchField.getText());
            int foundIndex = process.binarySearch(target);

            StringBuilder result = new StringBuilder();
            result.append("Отсортированный массив: ");
            for (int num : process.array) {
                result.append(num).append(" ");
            }
            result.append("\n");

            if (foundIndex != -1) {
                result.append("Число найдено на индексе: ").append(foundIndex);
            } else {
                result.append("Число не найдено в массиве.");
            }

            outputArea.setText(result.toString());
        } catch (NumberFormatException ex) {
            outputArea.setText("Некорректный ввод. Пожалуйста, введите числа.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayProcessorGUI gui = new ArrayProcessorGUI();
            gui.setVisible(true);
        });
    }
}