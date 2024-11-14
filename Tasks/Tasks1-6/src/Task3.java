class Numbers {
    protected int value;

    public Numbers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numbers numbers = (Numbers) o;
        return value == numbers.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

class Fractionn extends Numbers {
    private int numerator;
    private int denominator;

    public Fractionn(int numerator, int denominator) {
        super(numerator);
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    private void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public void print() {
        System.out.println(this);
    }

    public Fractionn add(Fractionn other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fractionn(newNumerator, newDenominator);
    }

    public Fractionn subtract(Fractionn other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fractionn(newNumerator, newDenominator);
    }

    public Fractionn multiply(Fractionn other) {
        return new Fractionn(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public Fractionn divide(Fractionn other) {
        return new Fractionn(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fractionn fraction = (Fractionn) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

public class Task3 {
    public static void main(String[] args) {
        Fractionn f1 = new Fractionn(1, 2);
        Fractionn f2 = new Fractionn(1, 3);

        System.out.print("f1: ");
        f1.print();
        System.out.print("f2: ");
        f2.print();

        System.out.println("f1 + f2 = " + f1.add(f2));
        System.out.println("f1 - f2 = " + f1.subtract(f2));
        System.out.println("f1 * f2 = " + f1.multiply(f2));
        System.out.println("f1 / f2 = " + f1.divide(f2));
    }
}
