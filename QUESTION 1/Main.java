import java.util.*;

public class Main {
    static abstract class Plot {
        String owner;
        Plot(String o) { owner = o; }
        abstract String shape();
        abstract double area();
    }

    static class CirclePlot extends Plot {
        double r;
        CirclePlot(String o, double r) { super(o); this.r = r; }
        @Override String shape() { return "CIRCLE"; }
        @Override double area() { return Math.PI * r * r; }
    }

    static class RectanglePlot extends Plot {
        double l, w;
        RectanglePlot(String o, double l, double w) {
            super(o); this.l = l; this.w = w;
        }
        @Override String shape() { return "RECTANGLE"; }
        @Override double area() { return l * w; }
    }

    static class TrianglePlot extends Plot {
        double b, h;
        TrianglePlot(String o, double b, double h) {
            super(o); this.b = b; this.h = h;
        }
        @Override String shape() { return "TRIANGLE"; }
        @Override double area() { return 0.5 * b * h; }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
        int n = sc.nextInt();
        double total = 0;
        Plot[] p = new Plot[n];

        for (int i = 0; i < n; i++) {
            String s = sc.next().toUpperCase(Locale.ROOT);
            String o = sc.next();

            switch (s) {
                case "CIRCLE":
                    p[i] = new CirclePlot(o, sc.nextDouble());
                    break;
                case "RECTANGLE":
                    p[i] = new RectanglePlot(o, sc.nextDouble(), sc.nextDouble());
                    break;
                case "TRIANGLE":
                    p[i] = new TrianglePlot(o, sc.nextDouble(), sc.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown plot shape: " + s);
            }
        }

        for (Plot x : p) {
            double a = x.area();
            System.out.printf(Locale.US, "%s (%s): %.2f%n", x.owner, x.shape(), a);
            total += a;
        }

        System.out.printf(Locale.US, "Total Area: %.2f%n", total);
        }
    }
}