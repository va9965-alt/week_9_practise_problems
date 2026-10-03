import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
        int count = scanner.nextInt();
        Connection[] connections = new Connection[count];
        double total = 0;

        for (int i = 0; i < count; i++) {
            String type = scanner.next().toUpperCase(Locale.ROOT);
            double units = scanner.nextDouble();
            switch (type) {
                case "HOME":
                    connections[i] = new HomeConnection(units);
                    break;
                case "SHOP":
                    connections[i] = new ShopConnection(units);
                    break;
                case "FACTORY":
                    connections[i] = new FactoryConnection(units);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown connection type: " + type);
            }
        }

        for (Connection connection : connections) {
            double bill = connection.calculateBill();
            System.out.printf("%s: %.2f%n", connection.type(), bill);
            total += bill;
        }
        System.out.printf("Total: %.2f%n", total);
    }
    }

    private abstract static class Connection {
        protected final double units;

        private Connection(double units) {
            this.units = units;
        }

        abstract String type();

        abstract double calculateBill();
    }

    private static final class HomeConnection extends Connection {
        private HomeConnection(double units) {
            super(units);
        }

        @Override
        String type() {
            return "HOME";
        }

        @Override
        double calculateBill() {
            return Math.min(units, 100) * 5 + Math.max(units - 100, 0) * 7;
        }
    }

    private static final class ShopConnection extends Connection {
        private ShopConnection(double units) {
            super(units);
        }

        @Override
        String type() {
            return "SHOP";
        }

        @Override
        double calculateBill() {
            return units * 8 + 100;
        }
    }

    private static final class FactoryConnection extends Connection {
        private FactoryConnection(double units) {
            super(units);
        }

        @Override
        String type() {
            return "FACTORY";
        }

        @Override
        double calculateBill() {
            return Math.max(units * 6, 1000);
        }
    }
}