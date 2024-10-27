import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Класс, представляющий автомобиль
    static class Car {
        String carBrand; // Марка автомобиля
        String carClass; // Класс автомобиля
        float weight; // Вес автомобиля
        Driver driver; // Водитель, связанный с автомобилем
        Engine engine; // Двигатель, связанный с автомобилем

        // Вложенный класс, представляющий водителя
        static class Driver {
            String name; // Имя водителя
            int driverExp; // Стаж водителя в годах
        }

        // Вложенный класс, представляющий двигатель
        static class Engine {
            String maker; // Производитель двигателя
            int power; // Мощность двигателя
        }

        // Конструктор инициализирует водителя и двигатель
        public Car() {
            this.driver = new Driver();
            this.engine = new Engine();
        }

        // Метод для вывода информации о водителе и деталях автомобиля
        public static void outputDriverInfo(Car car) {
            System.out.println();
            System.out.println("ФИО водителя: " + car.driver.name);
            System.out.println("Водительский стаж: " + car.driver.driverExp);
            System.out.println("Марка машины: " + car.carBrand);
            System.out.println("Класс машины: " + car.carClass);
            System.out.println("Вес машины: " + car.weight);
            System.out.println("Производитель двигателя: " + car.engine.maker);
            System.out.println("Мощность двигателя: " + car.engine.power);
            System.out.println();
        }

        // Метод для вывода информации о всех водителях
        public static void outputAllDrivers(List<Car> cars) {
            for (Car car : cars) {
                outputDriverInfo(car);
            }
        }

        // Метод для вывода информации о водителях со стажем более 5 лет
        public static void outputDriverThan5Years(List<Car> cars) {
            for (Car car : cars) {
                if (car.driver.driverExp >= 5) {
                    outputDriverInfo(car);
                }
            }
        }

        // Метод для вывода информации о водителях с советскими автомобилями
        public static void outputDriverSovietCar(List<Car> cars) {
            for (Car car : cars) {
                if (car.engine.maker.equals("СССР")) {
                    outputDriverInfo(car);
                }
            }
        }
    }

    // Класс для управления репозиторием автомобилей
    static class CarRepository {
        private List<Car> cars; // Список для хранения автомобилей

        // Конструктор инициализирует список автомобилей
        public CarRepository() {
            this.cars = new ArrayList<>();
        }

        // Метод для добавления автомобиля в репозиторий
        public void addCar(Car car) {
            cars.add(car);
        }

        // Метод для удаления автомобиля по индексу
        public void removeCar(int index) {
            if (index >= 0 && index < cars.size()) {
                cars.remove(index);
            } else {
                System.out.println("Некорректный индекс.");
            }
        }

        // Метод для обновления автомобиля по индексу
        public void updateCar(int index, Car car) {
            if (index >= 0 && index < cars.size()) {
                cars.set(index, car);
            } else {
                System.out.println("Некорректный индекс.");
            }
        }

        // Метод для получения списка автомобилей
        public List<Car> getCars() {
            return cars;
        }
    }

    // Основной метод для запуска приложения
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Сканер для ввода пользователя
        CarRepository carRepository = new CarRepository(); // Создание репозитория автомобилей

        int choice = 0; // Переменная для хранения выбора меню пользователя
        do {
            System.out.println();
            System.out.println("------------Меню------------");
            System.out.println("1. Добавить запись");
            System.out.println("2. Просмотреть все записи");
            System.out.println("3. Просмотреть записи водителей со стажем более 5 лет");
            System.out.println("4. Просмотреть записи водителей с советскими автомобилями");
            System.out.println("5. Удалить запись");
            System.out.println("6. Выход");
            System.out.print("Выберите действие от 1 до 6: ");
            try {
                choice = scanner.nextInt(); // Получение выбора пользователя
                scanner.nextLine(); // Потребление символа новой строки
                switch (choice) {
                    case 1: // Добавить новую запись о автомобиле
                        Car newCar = new Car(); // Создание нового объекта Car
                        System.out.print("Введите ФИО водителя: ");
                        newCar.driver.name = scanner.nextLine(); // Ввод имени водителя
                        System.out.print("Введите стаж водителя: ");
                        newCar.driver.driverExp = scanner.nextInt(); // Ввод стажа водителя
                        scanner.nextLine(); // Потребление символа новой строки
                        System.out.print("Введите марку машины: ");
                        newCar.carBrand = scanner.nextLine(); // Ввод марки автомобиля
                        System.out.print("Введите класс машины: ");
                        newCar.carClass = scanner.nextLine(); // Ввод класса автомобиля
                        System.out.print("Введите вес машины: ");
                        newCar.weight = scanner.nextFloat(); // Ввод веса автомобиля
                        System.out.print("Введите мощность двигателя: ");
                        newCar.engine.power = scanner.nextInt(); // Ввод мощности двигателя
                        scanner.nextLine(); // Потребление символа новой строки
                        System.out.print("Введите производителя двигателя: ");
                        newCar.engine.maker = scanner.nextLine(); // Ввод производителя двигателя
                        carRepository.addCar(newCar); // Добавление нового автомобиля в репозиторий
                        break;
                    case 2: // Просмотр всех записей
                        Car.outputAllDrivers(carRepository.getCars());
                        break;
                    case 3: // Просмотр водителей со стажем более 5 лет
                        Car.outputDriverThan5Years(carRepository.getCars());
                        break;
                    case 4: // Просмотр водителей с советскими автомобилями
                        Car.outputDriverSovietCar(carRepository.getCars());
                        break;
                    case 5: // Удаление записи о автомобиле
                        System.out.print("Введите индекс записи для удаления: ");
                        int indexToRemove = scanner.nextInt(); // Ввод индекса для удаления
                        carRepository.removeCar(indexToRemove); // Удаление автомобиля из репозитория
                        break;
                    case 6: // Выход из программы
                        System.out.println("Выход...");
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите от 1 до 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите число."); // Обработка неверного ввода
                scanner.nextLine(); // Очистка неверного ввода
            }
        } while (choice != 6); // Цикл продолжается, пока пользователь не выберет выход

        scanner.close(); // Закрытие сканера
    }
}