package org.example.proyecto4simulacion;

public class PoissonPractica {
    public static long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public static double calcularPoisson(double lambda, int k) {
        return ((Math.pow(lambda, k) * Math.exp(-lambda)) / factorial(k));
    }

}
