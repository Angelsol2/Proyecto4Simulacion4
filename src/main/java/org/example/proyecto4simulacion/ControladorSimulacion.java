package org.example.proyecto4simulacion;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
public class ControladorSimulacion {
    @FXML private ComboBox<String>comboModoEstudio;
    @FXML private BarChart<String, Number> graficaBarras;




    public void limpiarPantalla(String dato){
        graficaBarras.getData().clear();
        XYChart.Series<String, Number> nuevaSerie = new XYChart.Series<>();

        switch (dato){
            case "Distribución Poisson":



            case"Distribución Binomial":
            case"Mersenne Twister":
            case"Kolmogorov":
            case"Montecarlo":
            case"Chi-cuadrada":

        }
    }
}
