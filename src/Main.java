import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows, cols;

        do {
            System.out.print("Lütfen matris için satır sayısını giriniz (min 2): ");
            rows = scanner.nextInt();
            System.out.print("Lütfen matris için sütun sayısını giriniz (min 2): ");
            cols = scanner.nextInt();

            if (rows < 2 || cols < 2) {
                System.out.println("Satır ve sütun sayısı 2x2 veya daha büyük olmalıdır. Lütfen tekrar giriniz.");
            }
        } while (rows < 2 || cols < 2);

        MineSweeper game = new MineSweeper(rows, cols);
        game.play();
    }
}
