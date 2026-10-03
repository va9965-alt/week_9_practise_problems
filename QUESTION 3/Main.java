import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
        int count = scanner.nextInt();
        LibraryItem[] items = new LibraryItem[count];
        double total = 0;

        for (int i = 0; i < count; i++) {
            String type = scanner.next().toUpperCase(Locale.ROOT);
            String title = scanner.next();
            int daysLate = scanner.nextInt();
            switch (type) {
                case "BOOK":
                    items[i] = new BookItem(title, daysLate);
                    break;
                case "DVD":
                    items[i] = new DvdItem(title, daysLate);
                    break;
                case "MAGAZINE":
                    items[i] = new MagazineItem(title, daysLate);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown item type: " + type);
            }
        }

        for (LibraryItem item : items) {
            double fine = item.calculateFine();
            System.out.printf("%s: %.2f%n", item.title, fine);
            total += fine;
        }
        System.out.printf("Total Fines: %.2f%n", total);
    }
    }

    private abstract static class LibraryItem {
        private final String title;
        protected final int daysLate;

        private LibraryItem(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }

        abstract double calculateFine();
    }

    private static final class BookItem extends LibraryItem {
        private BookItem(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        double calculateFine() {
            return daysLate * 2.0;
        }
    }

    private static final class DvdItem extends LibraryItem {
        private DvdItem(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        double calculateFine() {
            return Math.min(daysLate * 5.0, 50.0);
        }
    }

    private static final class MagazineItem extends LibraryItem {
        private MagazineItem(String title, int daysLate) {
            super(title, daysLate);
        }

        @Override
        double calculateFine() {
            return daysLate;
        }
    }
}