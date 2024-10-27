import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
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

        public static void outputDriverInfo(int count, Car[] cars) {
            for (int i = 0; i < count; i++) {
                System.out.println();
                System.out.println("Водитель #" + (i + 1));
                System.out.println("ФИО водителя: " + cars[i].driver.name);
                System.out.println("Водительский стаж: " + cars[i].driver.driverExp);
                System.out.println("Марка машины: " + cars[i].carBrand);
                System.out.println("Класс машины: " + cars[i].carClass);
                System.out.println("Вес машины: " + cars[i].weight);
                System.out.println("Производитель двигателя: " + cars[i].engine.maker);
                System.out.println("Мощность двигателя: " + cars[i].engine.power);
                System.out.println();
            }
        }

        public static void outputDriverThan5Years(int count, Car[] cars) {
            for (int i = 0; i < count; i++) {
                if (cars[i].driver.driverExp >= 5) {
                    outputDriverInfo(1, new Car[]{cars[i]});
                }
            }
        }

        public static void outputDriverSovietCar(int count, Car[] cars) {
            for (int i = 0; i < count; i++) {
                if (cars[i].engine.maker.equals("СССР")) {
                    outputDriverInfo(1, new Car[]{cars[i]});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Car[] cars = new Car[10];

        int choice = 0;
        int i = 0;
        do {
            System.out.println();
            System.out.println("------------Меню------------");
            System.out.println("1. Добавить запись");
            System.out.println("2. Просмотреть все записи");
            System.out.println("3. Просмотреть записи водителей со стажем более 5 лет");
            System.out.println("4. Просмотреть записи водителей с советскими автомобилями");
            System.out.println("5. Выход");
            System.out.print("Выберите действие от 1 до 5: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        if (i < cars.length) {
                            cars[i] = new Car();
                            System.out.println();
                            System.out.print("Введите ФИО водителя " + (i + 1) + ": ");
                            cars[i].driver.name = scanner.nextLine();
                            System.out.print("Введите стаж водителя " + (i + 1) + ": ");
                            cars[i].driver.driverExp = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Введите марку машины: ");
                            cars[i].carBrand = scanner.nextLine();
                            System.out.print("Введите класс машины: ");
                            cars[i].carClass = scanner.nextLine();
                            System.out.print("Введите вес машины: ");
                            cars[i].weight = scanner.nextFloat();
                            System.out.print("Введите мощность двигателя: ");
                            cars[i].engine.power = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Введите производителя двигателя: ");
                            cars[i].engine.maker = scanner.nextLine();
                            i += 1;
                        } else {
                            System.out.println("Достигнут лимит машин. Необходимо удалить одну, прежде чем добавлять новую.");
                        }
                        break;
                    case 2:
                        Car.outputDriverInfo(i, cars);
                        break;
                    case 3:
                        Car.outputDriverThan5Years(i, cars);
                        break;
                    case 4:
                        Car.outputDriverSovietCar(i, cars);
                        break;
                    case 5:
                        System.out.println("Выход...");
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите от 1 до 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите число.");
                scanner.nextLine();
            }
        } while (choice != 5);

        scanner.close();
    }
}