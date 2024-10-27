import java.util.Scanner;

class arrayProcessor {
    int[] array;

    arrayProcessor(int n) {
        this.array = new int[n];
    }

    void outputArray(int n) {
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", array[i]);
        }
    }

    void sortArray() {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    int binarySearch(int i, int n) {
        int low, high, middle;
        low = 0;
        high = n - 1;
        while (low <= high) {
            middle = (low + high) / 2;
            if (i < array[middle]) {
                high = middle - 1;
            } else if (i > array[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();

        arrayProcessor process = new arrayProcessor(n);
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            process.array[i] = scanner.nextInt();
        }
        process.sortArray();
        System.out.print("Введите число для поиска в массиве: ");
        int i = scanner.nextInt();
        int foundInd = process.binarySearch(i, n);
        if (foundInd != -1) {
            System.out.printf("Индекс искомого числа в массиве: %d\n", foundInd);
        } else {
            System.out.print("Данного числа нет в массиве\n");
        }
        System.out.print("Вывод самого массива с сортировкой: ");
        process.outputArray(n);
    }
}