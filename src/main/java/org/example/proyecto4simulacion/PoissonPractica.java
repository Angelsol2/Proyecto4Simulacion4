package org.example.proyecto4simulacion;

public class PoissonPractica {


    public  double factorial(int n) {
        double fact = 1.0;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public double calcularPoisson(double lambda, int k) {
        return ((Math.pow(lambda, k) * Math.exp(-lambda)) / factorial(k)) * 100;
    }

    public double combinatoria(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public double calcularBinomial(int n, double p, int k) {
        double combinaciones = combinatoria(n, k);
        return combinaciones * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }

    public double calcularKolmogorov(double[] numerosAleatorios) {
        java.util.Arrays.sort(numerosAleatorios);

        double dMax = 0.0;
        int n = numerosAleatorios.length;
        for (int i = 0; i < n; i++) {
            double probEsperada = (double) (i + 1) / n;

            double diferencia = Math.abs(probEsperada - numerosAleatorios[i]);

            if (diferencia > dMax) {
                dMax = diferencia;
            }

        }

        return dMax;
    }
    public double calcularChiCuadrada(double[] esperados, double[] observados) {
        double chiCuadradaTotal = 0.0;

        if (esperados.length != observados.length) {
            System.out.println("Error matemático: Los datos esperados y observados no coinciden en cantidad.");
            return -1.0; // Retornamos un error visualizable
        }

        for (int i = 0; i < esperados.length; i++) {
            double E = esperados[i];
            double O = observados[i];
            double diferencia = O - E;
            chiCuadradaTotal += (diferencia * diferencia) / E;
        }

        return chiCuadradaTotal;
    }
}