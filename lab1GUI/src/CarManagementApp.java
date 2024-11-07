import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarManagementApp {
    static class Car {
        String carBrand;
        String carClass;
        float weight;
        Driver driver;
        Engine engine;

        static class Driver {
            String name;
            int driverExp;
        }

        static class Engine {
            String maker;
            int power;
        }

        public Car() {
            this.driver = new Driver();
            this.engine = new Engine();
        }
    }

    static class CarRepository {
        private List<Car> cars;

        public CarRepository() {
            this.cars = new ArrayList<>();
        }

        public void addCar(Car car) {
            cars.add(car);
        }

        public void removeCar(int index) {
            if (index >= 0 && index < cars.size()) {
                cars.remove(index);
            } else {
                JOptionPane.showMessageDialog(null, "Некорректный индекс.");
            }
        }

        public void updateCar(int index, Car car) {
            if (index >= 0 && index < cars.size()) {
                cars.set(index, car);
            } else {
                JOptionPane.showMessageDialog(null, "Некорректный индекс.");
            }
        }

        public List<Car> getCars() {
            return cars;
        }
    }

    private CarRepository carRepository = new CarRepository();

    public CarManagementApp() {
        JFrame frame = new JFrame("Управление Автомобилями");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Добавить запись");
        JButton viewAllButton = new JButton("Просмотреть все записи");
        JButton updateButton = new JButton("Обновить запись");
        JButton removeButton = new JButton("Удалить запись");

        panel.add(addButton);
        panel.add(viewAllButton);
        panel.add(updateButton);
        panel.add(removeButton);

        addButton.addActionListener(e -> addCar());
        viewAllButton.addActionListener(e -> viewAllCars(displayArea));
        updateButton.addActionListener(e -> updateCar(displayArea));
        removeButton.addActionListener(e -> removeCar(displayArea));

        frame.setVisible(true);
    }

    private void addCar() {
        Car car = createCarFromInput();
        if (car != null) {
            carRepository.addCar(car);
        }
    }

    private void updateCar(JTextArea displayArea) {
        try {
            int indexToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Введите индекс для обновления:"));
            Car updatedCar = createCarFromInput();
            if (updatedCar != null) {
                carRepository.updateCar(indexToUpdate - 1, updatedCar);
                viewAllCars(displayArea);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Пожалуйста, введите корректный индекс.");
        }
    }

    private void removeCar(JTextArea displayArea) {
        try {
            int indexToRemove = Integer.parseInt(JOptionPane.showInputDialog("Введите индекс для удаления:"));
            carRepository.removeCar(indexToRemove - 1);
            viewAllCars(displayArea);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Пожалуйста, введите корректный индекс.");
        }
    }

    private void viewAllCars(JTextArea displayArea) {
        StringBuilder sb = new StringBuilder();
        List<Car> cars = carRepository.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            sb.append("Индекс: ").append(i + 1).append("\n")
                    .append("ФИО водителя: ").append(car.driver.name).append("\n")
                    .append("Водительский стаж: ").append(car.driver.driverExp).append("\n")
                    .append("Марка машины: ").append(car.carBrand).append("\n")
                    .append("Класс машины: ").append(car.carClass).append("\n")
                    .append("Вес машины: ").append(car.weight).append("\n")
                    .append("Производитель двигателя: ").append(car.engine.maker).append("\n")
                    .append("Мощность двигателя: ").append(car.engine.power).append("\n\n");
        }
        displayArea.setText(sb.toString());
    }

    private Car createCarFromInput() {
        try {
            Car car = new Car();
            car.driver.name = JOptionPane.showInputDialog("Введите ФИО водителя:");
            car.driver.driverExp = Integer.parseInt(JOptionPane.showInputDialog("Введите стаж водителя:"));
            car.carBrand = JOptionPane.showInputDialog("Введите марку машины:");
            car.carClass = JOptionPane.showInputDialog("Введите класс машины:");
            car.weight = Float.parseFloat(JOptionPane.showInputDialog("Введите вес машины:"));
            car.engine.power = Integer.parseInt(JOptionPane.showInputDialog("Введите мощность двигателя:"));
            car.engine.maker = JOptionPane.showInputDialog("Введите производителя двигателя:");
            return car;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка ввода. Пожалуйста, введите корректные данные.");
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarManagementApp::new);
    }
}