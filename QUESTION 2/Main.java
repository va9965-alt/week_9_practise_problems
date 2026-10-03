import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
        int count = scanner.nextInt();
        StaffMember[] staff = new StaffMember[count];
        double total = 0;

        for (int i = 0; i < count; i++) {
            String type = scanner.next().toUpperCase(Locale.ROOT);
            String name = scanner.next();
            switch (type) {
                case "FULLTIME":
                    staff[i] = new FullTimeStaff(name, scanner.nextDouble());
                    break;
                case "HOURLY":
                    staff[i] = new HourlyStaff(name, scanner.nextDouble(), scanner.nextDouble());
                    break;
                case "INTERN":
                    staff[i] = new InternStaff(name, scanner.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown staff type: " + type);
            }
        }

        for (StaffMember member : staff) {
            double pay = member.calculatePay();
            System.out.printf("%s: %.2f%n", member.name, pay);
            total += pay;
        }
        System.out.printf("Total Payroll: %.2f%n", total);
    }
    }

    private abstract static class StaffMember {
        private final String name;

        private StaffMember(String name) {
            this.name = name;
        }

        abstract double calculatePay();
    }

    private static final class FullTimeStaff extends StaffMember {
        private final double weeklySalary;

        private FullTimeStaff(String name, double weeklySalary) {
            super(name);
            this.weeklySalary = weeklySalary;
        }

        @Override
        double calculatePay() {
            return weeklySalary;
        }
    }

    private static final class HourlyStaff extends StaffMember {
        private final double hours;
        private final double rate;

        private HourlyStaff(String name, double hours, double rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        double calculatePay() {
            return Math.min(hours, 40) * rate + Math.max(hours - 40, 0) * rate * 1.5;
        }
    }

    private static final class InternStaff extends StaffMember {
        private final double stipend;

        private InternStaff(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }

        @Override
        double calculatePay() {
            return stipend;
        }
    }
}