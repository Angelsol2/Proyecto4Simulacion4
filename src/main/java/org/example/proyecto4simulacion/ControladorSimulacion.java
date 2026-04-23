package org.example.proyecto4simulacion;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class ControladorSimulacion {

    Random formulaRandom = new Random();
    PoissonPractica formula = new PoissonPractica();

    XYChart.Series<String, Number> nuevaSerie = new XYChart.Series<>();
    @FXML
    private ComboBox<String> comboModoEstudio;
    @FXML
    private BarChart<String, Number> graficaBarras;
    @FXML
    private TextField campoProbabilidad;
    @FXML
    private TextField exitosRequeridos;
    @FXML
    private TextField campoLambda;
    @FXML
    private TextField campoCasos;
    @FXML
    private TextField campoResultado;
    @FXML
    private Button calcular;
    @FXML
    private TextField campoEsperado;
    @FXML
    private TextField campoReal;
    @FXML
    private Pane campoAnimacion;
    @FXML
    private ScatterChart<Number, Number> graficaPuntos;
    @FXML
    private TextField Totalnumeros;
    @FXML
    private LineChart<Number, Number> graficaLineas;
    @FXML
    private TextField CampoDeseadp;
    @FXML
    private TextField campoObservado;

    @FXML
    private GridPane panelMegaDashboard; // Tu nuevo contenedor de cuadrícula
    // Las 4 gráficas exclusivas de la fábrica
    @FXML
    private ScatterChart<Number, Number> graficaMontecarloDash;
    @FXML
    private BarChart<String, Number> graficaProbabilidadesDash; // Para Poisson y Binomial
    @FXML
    private BarChart<String, Number> graficaChiDash; // Para los sensores
    @FXML
    private LineChart<Number, Number> graficaKolmogorovDash; // Para el brazo robótico

    public void initialize() {
        comboModoEstudio.setOnAction(actionEvent -> ejecutar());
    }

    private void prenderCamposPoisson() {
        OcultarInformacion();
        campoLambda.setVisible(true);
        campoCasos.setVisible(true);
        campoResultado.setVisible(true);
        graficaBarras.setVisible(true);
        campoResultado.setPromptText("Resultado");
    }

    private void prenderCamposBinomial() {
        OcultarInformacion();
        campoCasos.setVisible(true);
        campoProbabilidad.setVisible(true);
        exitosRequeridos.setVisible(true);
        campoResultado.setVisible(true);
        graficaBarras.setVisible(true);
        campoResultado.setPromptText("Resultado");

    }

    private void prenderMersenner() {
        OcultarInformacion();
        graficaPuntos.setVisible(true);
        Totalnumeros.setVisible(true);
    }

    private void PrenderKolgomorov() {
        OcultarInformacion();
        campoResultado.setVisible(true);
        CampoDeseadp.setVisible(true);
        graficaLineas.setVisible(true);

    }
    private void prenderMontecarlo(){
           OcultarInformacion();
           graficaPuntos.setVisible(true);
           Totalnumeros.setVisible(true);
           campoResultado.setVisible(true);
    }

    private void OcultarInformacion() {

// Apagamos lo demás (tu código original)
        graficaBarras.setVisible(false);
        campoProbabilidad.setVisible(false);
        exitosRequeridos.setVisible(false);
        campoLambda.setVisible(false);
        campoCasos.setVisible(false);
        campoResultado.setVisible(false);
        campoEsperado.setVisible(false);
        campoReal.setVisible(false);
        campoAnimacion.setVisible(false);
        graficaPuntos.setVisible(false);
        Totalnumeros.setVisible(false);
        graficaLineas.setVisible(false);
        campoObservado.setVisible(false);
        campoAnimacion.getChildren().clear();

    }
private void PrenderChicuadrada(){
        OcultarInformacion();
        campoResultado.setVisible(true);
    campoResultado.setPromptText("Diferencia");
    campoEsperado.setVisible(true);
    campoReal.setVisible(true);
    graficaBarras.setVisible(true);
}
    private void ActivarcasoAplicado() {
        OcultarInformacion();
        campoResultado.setVisible(true);
        campoResultado.setPromptText("Chi-cuadrada");
        campoEsperado.setVisible(true);
        campoAnimacion.setVisible(true);

        campoAnimacion.getChildren().clear();

        double anchoPane = campoAnimacion.getWidth();
        if (anchoPane == 0) anchoPane = 700;
        double anchoMesa = 50;

        Rectangle cajaRegistradora = new Rectangle(anchoMesa, 80, Color.web("#8B4513"));
        Rectangle cajaRegistradora2 = new Rectangle(anchoMesa, 80, Color.web("#8B4513"));
        Rectangle cajaRegistradora3 = new Rectangle(anchoMesa, 80, Color.web("#8B4513"));

        Rectangle[] cajas = new Rectangle[]{cajaRegistradora, cajaRegistradora2, cajaRegistradora3};

        double espacioCaja = (anchoPane / 3);
        double tamAcumulado = 0;
        campoAnimacion.setStyle("-fx-background-color: #514f4f");

        for (Rectangle caja : cajas) {
            double centro = espacioCaja / 2;
            tamAcumulado += espacioCaja;
            caja.setX(tamAcumulado - caja.getWidth() - centro);
            caja.setY(50);
        }


        campoAnimacion.getChildren().addAll(cajaRegistradora, cajaRegistradora2, cajaRegistradora3);
    }
    private void ActivarFabrica() {
        OcultarInformacion();
        panelMegaDashboard.setVisible(true);
        Totalnumeros.setVisible(true);
        Totalnumeros.setPromptText("Lote a simular (ej. 1000)");
        campoResultado.setVisible(true);
        campoResultado.setPromptText("Bitácora de Ingeniería");
    }
    public void limpiarPantalla() {
        graficaBarras.getData().clear();
        if (graficaBarras.getXAxis() instanceof CategoryAxis) {
            ((CategoryAxis) graficaBarras.getXAxis()).getCategories().clear();
        }
        graficaBarras.setAnimated(false);
    }

    public void BorradoBotones() {
        limpiarPantalla();
        if (campoLambda != null) campoLambda.setText(null);
        if (campoCasos != null) campoCasos.setText(null);
        if (campoProbabilidad != null) campoProbabilidad.setText(null);
        if (campoResultado != null) campoResultado.setText(null);
    }

    public void ejecutar() {
        switch (comboModoEstudio.getValue()) {
            case "Distribución Poisson":
                BorradoBotones();
                prenderCamposPoisson();

                calcular.setOnAction(actionEvent -> {
                    try {
                        limpiarPantalla();
                        nuevaSerie.getData().clear();

                        double resultado = formula.calcularPoisson(Integer.parseInt(campoLambda.getText()), Integer.parseInt(campoCasos.getText()));
                        campoResultado.setText(String.format("%.2f", resultado) + "%");

                        for (int i = 0; i < 20; i++) {
                            XYChart.Data<String, Number> punto = new XYChart.Data<>(String.valueOf(i), formula.calcularPoisson(Integer.parseInt(campoLambda.getText()), i));

                            if (i == Integer.parseInt(campoCasos.getText())) {
                                punto.nodeProperty().addListener((observable, nodoViejo, nodoNuevo) -> {
                                    if (nodoNuevo != null) {
                                        nodoNuevo.setStyle("-fx-bar-fill: #007bff;");
                                    }
                                });
                            }
                            nuevaSerie.getData().add(punto);
                        }
                        graficaBarras.getData().add(nuevaSerie);
                    } catch (Exception e) {
                    }
                });
                break;

            case "Distribución Binomial":
                BorradoBotones();
                prenderCamposBinomial();
                calcular.setOnAction(actionEvent -> {
                    try {
                        graficaBarras.getData().clear();
                        nuevaSerie.getData().clear();

                        int n = Integer.parseInt(campoCasos.getText());
                        double p = Double.parseDouble(campoProbabilidad.getText());
                        int k = Integer.parseInt(exitosRequeridos.getText());

                        double resultado = formula.calcularBinomial(n, p, k);
                        campoResultado.setText(String.format("%.2f", resultado * 100) + "%");

                        for (int i = 0; i <= n; i++) {
                            double probBarra = formula.calcularBinomial(n, p, i);
                            XYChart.Data<String, Number> punto = new XYChart.Data<>(String.valueOf(i), probBarra * 100);

                            if (i == k) {
                                punto.nodeProperty().addListener((observable, nodoViejo, nodoNuevo) -> {
                                    if (nodoNuevo != null) {
                                        nodoNuevo.setStyle("-fx-bar-fill: #007bff;");
                                    }
                                });
                            }
                            nuevaSerie.getData().add(punto);
                        }
                        graficaBarras.getData().add(nuevaSerie);
                    } catch (Exception e) {
                    }
                });
                break;

            case "Mersenne Twister":
                BorradoBotones();
                prenderMersenner();
                calcular.setOnAction(actionEvent -> {
                    graficaPuntos.getData().clear();
                    graficaPuntos.setAnimated(false);

                    XYChart.Series<Number, Number> seriesPuntos = new XYChart.Series<>();
                    seriesPuntos.setName("Distribucion Uniforme");

                    int n = Integer.parseInt(Totalnumeros.getText());
                    for (int i = 0; i < n; i++) {
                        double x = formulaRandom.nextDouble();
                        double y = formulaRandom.nextDouble();
                        seriesPuntos.getData().add(new XYChart.Data<>(x, y));
                    }
                    graficaPuntos.getData().add(seriesPuntos);
                });
                break;

            case "Kolmogorov":
                BorradoBotones();
                PrenderKolgomorov();

                calcular.setOnAction(actionEvent -> {
                    graficaLineas.getData().clear();

                    String datosEsperados = CampoDeseadp.getText();
                    String[] ArraydatosEsperados = datosEsperados.split(",");




                    double[] datosEsperadosTransoformados = new double[ArraydatosEsperados.length];

                    for (int i = 0; i < ArraydatosEsperados.length; i++) {
                        try {
                            datosEsperadosTransoformados[i] = Double.parseDouble(ArraydatosEsperados[i]);

                        } catch (Exception e) {
                            System.out.println("NO se logro convertir dato");
                        }
                    }

                    java.util.Arrays.sort(datosEsperadosTransoformados);

                    double dMax = 0;
                    XYChart.Series<Number, Number> serieReal = new XYChart.Series<>();
                    serieReal.setName("Valores Reales");

                    XYChart.Series<Number, Number> serieEsperada = new XYChart.Series<>();
                    serieEsperada.setName("Valores Esperados");

                    serieReal.getData().add(new XYChart.Data<>(0, 0));
                    serieEsperada.getData().add(new XYChart.Data<>(0, 0));

                    for (int i = 0; i < ArraydatosEsperados.length; i++) {
                        double esperado = (double) (i + 1) / ArraydatosEsperados.length;
                        double real = datosEsperadosTransoformados[i];



                        serieReal.getData().add(new XYChart.Data<>(real, esperado));
                        serieEsperada.getData().add(new XYChart.Data<>(esperado, esperado));

                        double diferencia = Math.abs(esperado - real);
                        if (diferencia > dMax) {
                            dMax = diferencia;
                        }
                    }

                    double resultadoFormula = formula.calcularKolmogorov(datosEsperadosTransoformados);
                    campoResultado.setText(String.format("Dmax: %.4f", resultadoFormula));

                    graficaLineas.getData().addAll(serieReal, serieEsperada);
                });
                break;

            case "Montecarlo":
                BorradoBotones();
                prenderMontecarlo();


                calcular.setOnAction(actionEvent -> {
                    graficaPuntos.getData().clear();
                    graficaPuntos.setAnimated(false);

                    int N = Integer.parseInt(Totalnumeros.getText());

                    XYChart.Series<Number, Number> seriesadentro = new XYChart.Series<>();
                    seriesadentro.setName("Valores Exitosos (Dentro)");

                    XYChart.Series<Number, Number> seriesafuera = new XYChart.Series<>();
                    seriesafuera.setName("Valores Erróneos (Fuera)");

                    int casosexitosos = 0;

                    for (int i = 0; i < N; i++) {
                        double x = (formulaRandom.nextDouble() * 2) - 1;
                        double y = (formulaRandom.nextDouble() * 2) - 1;
                        double comprobacion = ((x * x) + (y * y));

                        if (comprobacion <= 1.0) {
                            casosexitosos++;
                            seriesadentro.getData().add(new XYChart.Data<>(x, y));
                        } else {
                            seriesafuera.getData().add(new XYChart.Data<>(x, y));
                        }
                    }

                    double piEstimado = 4.0 * ((double) casosexitosos / N);
                    campoResultado.setText(String.format("Pi ≈ %.4f", piEstimado));

                    graficaPuntos.getData().addAll(seriesadentro, seriesafuera);
                });
                break;
            case "Chi-cuadrada":
                BorradoBotones();
                PrenderChicuadrada();

                calcular.setOnAction(actionEvent -> {
                    try {

                        graficaBarras.getData().clear();
                        graficaBarras.setAnimated(false);


                        String[] arrayEsperados = campoEsperado.getText().split(",");
                        String[] arrayObservados = campoReal.getText().split(",");


                        if (arrayEsperados.length != arrayObservados.length) {
                            System.out.println("Error: Debes poner la misma cantidad de esperados y observados");
                            return;
                        }

                        XYChart.Series<String, Number> serieEsperada = new XYChart.Series<>();
                        serieEsperada.setName("Esperado");

                        XYChart.Series<String, Number> serieObservada = new XYChart.Series<>();
                        serieObservada.setName("Observado");

                        double chiCuadradaTotal = 0.0;

                        for (int i = 0; i < arrayEsperados.length; i++) {
                            double esperado = Double.parseDouble(arrayEsperados[i]);
                            double observado = Double.parseDouble(arrayObservados[i]);

                            String categoria = "comparaciones " + (i + 1);

                            serieEsperada.getData().add(new XYChart.Data<>(categoria, esperado));
                            serieObservada.getData().add(new XYChart.Data<>(categoria, observado));

                            double diferencia = observado - esperado;
                            chiCuadradaTotal += (diferencia * diferencia) / esperado;
                        }


                        campoResultado.setText(String.format("%.4f", chiCuadradaTotal ));

                        graficaBarras.getData().addAll(serieEsperada, serieObservada);

                    } catch (Exception e) {
                        System.out.println("Asegúrate de ingresar solo números separados por comas.");
                    }
                });
                break;

            case "CasoAplicado":
                BorradoBotones();
                ActivarcasoAplicado();
                calcular.setOnAction(actionEvent -> {

                        campoAnimacion.getChildren().removeIf(nodo -> nodo instanceof Circle);

                        String[] arrayEsperados = campoEsperado.getText().split(",");

                        double[] esperados = new double[arrayEsperados.length];
                        double[] observados = new double[arrayEsperados.length];


                        for (int i = 0; i < arrayEsperados.length; i++) {
                            esperados[i] = Double.parseDouble(arrayEsperados[i]);
                            int random = formulaRandom.nextInt(10) + 1;
                            observados[i] = random;

                        }


                        double chiCuadradaTotal = formula.calcularChiCuadrada(esperados, observados);
                        campoResultado.setText(String.format("Diferencia: %.4f", chiCuadradaTotal));


                        double anchoPane = campoAnimacion.getWidth();
                        if (anchoPane == 0) anchoPane = 700;
                        double espacioCaja = (anchoPane / 3);

                        double entradaX = anchoPane / 2;
                        double entradaY = 450;

                        double tiempoRetraso = 0.0;


                        int numCajas = Math.min(3, observados.length);

                        for (int i = 0; i < numCajas; i++) {
                            int numClientes = (int) observados[i];

                            double centroCaja = espacioCaja / 2;
                            double destinoX = ((i + 1) * espacioCaja) - 50 - centroCaja + 25;

                            for (int j = 0; j < numClientes; j++) {
                                Circle cliente = new Circle(15, Color.web("#4287f5"));

                                cliente.setTranslateX(entradaX);
                                cliente.setTranslateY(entradaY);
                                campoAnimacion.getChildren().add(cliente);


                                double destinoY = 150 + (j * 35);


                                javafx.animation.TranslateTransition animacion = new javafx.animation.TranslateTransition(javafx.util.Duration.seconds(1), cliente);
                                animacion.setToX(destinoX);
                                animacion.setToY(destinoY);


                                animacion.setDelay(javafx.util.Duration.seconds(tiempoRetraso));
                                animacion.play();

                                tiempoRetraso += 0.3;
                            }
                        }


                });
                break;

            case "Fábrica":
                BorradoBotones();
                ActivarFabrica();

                calcular.setOnAction(actionEvent -> {
                    try {
                        graficaMontecarloDash.getData().clear();
                        graficaProbabilidadesDash.getData().clear();
                        graficaChiDash.getData().clear();
                        graficaKolmogorovDash.getData().clear();

                        int lote = Integer.parseInt(Totalnumeros.getText());

                        XYChart.Series<Number, Number> serieOblea = new XYChart.Series<>();
                        serieOblea.setName("Láser de Corte");
                        int puntosDentro = 0;
                        for (int i = 0; i < lote; i++) {
                            double x = (formulaRandom.nextDouble() * 2) - 1;
                            double y = (formulaRandom.nextDouble() * 2) - 1;
                            if ((x * x + y * y) <= 1.0) puntosDentro++;

                            // Solo graficamos 400 puntos para que la app no se trabe
                            if (i < 400) serieOblea.getData().add(new XYChart.Data<>(x, y));
                        }
                        double areaUtil = 4.0 * ((double) puntosDentro / lote);
                        graficaMontecarloDash.getData().add(serieOblea);


                        // =========================================================
                        // 2. POISSON & BINOMIAL (Defectos y Control de Calidad)
                        // =========================================================
                        XYChart.Series<String, Number> seriePoisson = new XYChart.Series<>();
                        seriePoisson.setName("Polvo (Poisson λ=2)");
                        for (int i = 0; i <= 5; i++) {
                            double prob = formula.calcularPoisson(2, i); // λ=2
                            seriePoisson.getData().add(new XYChart.Data<>(i + " motas", prob));
                        }

                        XYChart.Series<String, Number> serieBinomial = new XYChart.Series<>();
                        serieBinomial.setName("Éxito (Binomial n=5, p=0.8)");
                        for (int i = 0; i <= 5; i++) {
                            double prob = formula.calcularBinomial(5, 0.8, i); // n=5 chips, p=80%
                            serieBinomial.getData().add(new XYChart.Data<>(i + " chips", prob));
                        }
                        graficaProbabilidadesDash.getData().addAll(seriePoisson, serieBinomial);


                        // =========================================================
                        // 3. CHI-CUADRADA (Auditoría de 3 Sensores Láser)
                        // =========================================================
                        double[] esperadosSensor = {50, 50, 50}; // Esperamos que detecten 50 errores
                        // Simulamos lo que detectaron realmente (con Mersenne)
                        double[] observadosSensor = {
                                40 + formulaRandom.nextInt(20),
                                40 + formulaRandom.nextInt(20),
                                40 + formulaRandom.nextInt(20)
                        };
                        double chi2 = formula.calcularChiCuadrada(esperadosSensor, observadosSensor);

                        XYChart.Series<String, Number> serieSensorEsp = new XYChart.Series<>();
                        serieSensorEsp.setName("Esperado");
                        XYChart.Series<String, Number> serieSensorReal = new XYChart.Series<>();
                        serieSensorReal.setName("Real");

                        for(int i=0; i<3; i++) {
                            serieSensorEsp.getData().add(new XYChart.Data<>("S-" + (i+1), esperadosSensor[i]));
                            serieSensorReal.getData().add(new XYChart.Data<>("S-" + (i+1), observadosSensor[i]));
                        }
                        graficaChiDash.getData().addAll(serieSensorEsp, serieSensorReal);


                        // =========================================================
                        // 4. KOLMOGOROV-SMIRNOV (Precisión del Brazo Robótico)
                        // =========================================================
                        double[] erroresBrazo = new double[10];
                        for(int i=0; i<10; i++) erroresBrazo[i] = formulaRandom.nextDouble();
                        double dMax = formula.calcularKolmogorov(erroresBrazo);

                        java.util.Arrays.sort(erroresBrazo);
                        XYChart.Series<Number, Number> serieKS = new XYChart.Series<>();
                        serieKS.setName("Tensión del Brazo");
                        for(int i=0; i<10; i++) {
                            serieKS.getData().add(new XYChart.Data<>(erroresBrazo[i], (double)(i+1)/10));
                        }
                        graficaKolmogorovDash.getData().add(serieKS);

                        // =========================================================
                        // 5. REPORTE FINAL EN LA BITÁCORA
                        // =========================================================
                        String estadoMaquina = (chi2 > 5.99) ? "ALERTA: Sensor Descalibrado" : "Sensores OK";
                        String estadoBrazo = (dMax > 0.4) ? "ALERTA: Falla Mecánica" : "Brazo Robótico OK";

                        campoResultado.setText(String.format("Área: %.2f | %s | %s", areaUtil, estadoMaquina, estadoBrazo));

                    } catch (Exception e) {
                        System.out.println("Ingresa un número en la caja de texto.");
                    }
                });
                break;

        }
    }
}