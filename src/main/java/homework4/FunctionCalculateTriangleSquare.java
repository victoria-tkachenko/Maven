package homework4;

public class FunctionCalculateTriangleSquare {

    public static double calculateTriangleSquare(int a, int b, int c) throws NotTriangleException {
        double square = 0;
        if ((a <= 0 || b <= 0 || c <= 0) || (a + b <= c) || (a + c <= b) || (b + c <= a))
            throw new NotTriangleException("Треугольник не существует");
        else {
            double p = (a + b + c) / 2.0;
            square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        return square;
    }
}

