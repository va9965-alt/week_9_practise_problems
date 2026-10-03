import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final double BOOKING_FEE = 50.0;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
        int count = scanner.nextInt();
        Booking[] bookings = new Booking[count];

        for (int i = 0; i < count; i++) {
            String mode = scanner.next().toUpperCase(Locale.ROOT);
            double distanceKm = scanner.nextDouble();
            switch (mode) {
                case "BUS":
                    bookings[i] = new BusBooking(distanceKm);
                    break;
                case "TRAIN":
                    bookings[i] = new TrainBooking(distanceKm);
                    break;
                case "FLIGHT":
                    bookings[i] = new FlightBooking(distanceKm);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown travel mode: " + mode);
            }
        }

        for (Booking booking : bookings) {
            System.out.printf("%s: %.2f%n", booking.mode(), booking.totalFare());
        }
    }
    }

    private abstract static class Booking {
        private final double distanceKm;

        private Booking(double distanceKm) {
            this.distanceKm = distanceKm;
        }

        abstract String mode();

        abstract double baseFare();

        final double distanceKm() {
            return distanceKm;
        }

        final double totalFare() {
            return baseFare() + BOOKING_FEE;
        }
    }

    private static final class BusBooking extends Booking {
        private BusBooking(double distanceKm) {
            super(distanceKm);
        }

        @Override
        String mode() {
            return "BUS";
        }

        @Override
        double baseFare() {
            return distanceKm() * 2;
        }
    }

    private static final class TrainBooking extends Booking {
        private TrainBooking(double distanceKm) {
            super(distanceKm);
        }

        @Override
        String mode() {
            return "TRAIN";
        }

        @Override
        double baseFare() {
            return distanceKm() * 1.5;
        }
    }

    private static final class FlightBooking extends Booking {
        private FlightBooking(double distanceKm) {
            super(distanceKm);
        }

        @Override
        String mode() {
            return "FLIGHT";
        }

        @Override
        double baseFare() {
            return 2500 + distanceKm() * 4;
        }
    }
}