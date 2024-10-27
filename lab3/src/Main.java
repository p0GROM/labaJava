import java.util.Scanner;

// Класс для обработки массива
class ArrayProcessor {
    int[] array; // Массив целых чисел

    // Конструктор для инициализации массива заданного размера
    ArrayProcessor(int n) {
        this.array = new int[n];
    }

    // Вывод элементов массива
    void outputArray(int n) {
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", array[i]);
        }
        System.out.println(); // Переход на новую строку после вывода
    }

    // Сортировка массива методом пузырька
    void sortArray() {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) { // * Упрощено выражение
                // Сравнение соседних элементов
                if (array[j] > array[j + 1]) {
                    // Обмен значениями
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Двоичный поиск элемента в отсортированном массиве
    int binarySearch(int target) { //Упрощён параметр
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2; // * Объявление переменной в цикле
            if (target < array[middle]) {
                high = middle - 1;
            } else if (target > array[middle]) {
                low = middle + 1;
            } else {
                return middle; // Элемент найден
            }
        }
        return -1; // Элемент не найден
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод размера массива
        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();

        ArrayProcessor process = new ArrayProcessor(n); // * Изменено название класса
        System.out.print("Введите элементы массива: ");

        // Ввод элементов массива
        for (int i = 0; i < n; i++) {
            process.array[i] = scanner.nextInt();
        }

        process.sortArray(); // Сортировка массива

        // Ввод числа для поиска
        System.out.print("Введите число для поиска в массиве: ");
        int target = scanner.nextInt(); // * Переименован параметр для ясности

        // Поиск элемента в массиве
        int foundIndex = process.binarySearch(target);
        if (foundIndex != -1) {
            System.out.printf("Индекс искомого числа в массиве: %d\n", foundIndex);
        } else {
            System.out.print("Данного числа нет в массиве\n");
        }

        // Вывод отсортированного массива
        System.out.print("Вывод массива с сортировкой: ");
        process.outputArray(n);

        scanner.close(); // Закрытие сканера
    }
}