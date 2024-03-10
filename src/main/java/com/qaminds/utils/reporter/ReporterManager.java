package com.qaminds.utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReporterManager {

    private static ExtentReports extent;  // Objeto principal de ExtentReports
    private static ExtentSparkReporter reporter;  // Reporter para la salida HTML
    private static ExtentTest extentTest;  // Representa una entrada de prueba en el reporte
    private static String reportHTMLPath;  // Ruta del archivo de reporte HTML

    /**
     * Crea el reporte HTML si no ha sido creado previamente.
     */
    public static void createReportHTML(){
        if (extent == null) {
            try {
                createReportName();
                reporter = new ExtentSparkReporter(getReportHTMLPath())
                        .viewConfigurer()
                        .viewOrder()
                        .as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST })
                        .apply();
                configureReporter(reporter);
                extent = new ExtentReports();
                extent.attachReporter(reporter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Crea una nueva entrada de prueba en el reporte.
     * @param nameTest Nombre de la prueba.
     * @return La entrada de prueba creada.
     */
    public static void createTest(String nameTest){
        extentTest = extent.createTest(nameTest);
    }

    /**
     * Devuelve la entrada de prueba actual.
     * @return La entrada de prueba actual.
     */
    public static ExtentTest createLogTest(){
        return extentTest;
    }

    /**
     * Crea el nombre y la ruta del reporte HTML.
     */
    public static void createReportName() {
        // Obtener una marca de tiempo para hacer el nombre del archivo único
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // Crear la ruta del reporte y el nombre del archivo
        String reportPath =  "target/Reports/extentReport_" + timestamp + ".html";
        setReportHTMLPath(reportPath);
    }

    /**
     * Configura el reporter con el archivo de configuración XML.
     * @param spark El reporter a configurar.
     * @throws IOException Si hay un problema al cargar la configuración.
     */
    private static void configureReporter(ExtentSparkReporter spark) throws IOException {
        // Cargar el archivo XML de configuración desde la carpeta de recursos
        URL configURL = ReporterManager.class.getClassLoader().getResource("spark-config.xml");
        if (configURL == null) {
            throw new RuntimeException("No se pudo encontrar spark-config.xml");
        }

        // Convertir la URL a una ruta de archivo y cargar la configuración
        File config = new File(configURL.getPath());
        spark.loadXMLConfig(config);
    }

    /**
     * Realiza un flush en el objeto ExtentReports, escribiendo cualquier dato pendiente al reporte HTML.
     */
    public static void extentFlush(){
        extent.flush();
    }

    /**
     * Establece la ruta del archivo de reporte HTML.
     * @param reportHTML La ruta del archivo de reporte HTML.
     */
    private static void setReportHTMLPath(String reportHTML){
        reportHTMLPath = reportHTML;
    }

    /**
     * Obtiene la ruta del archivo de reporte HTML.
     * @return La ruta del archivo de reporte HTML.
     */
    public static String getReportHTMLPath(){
        return reportHTMLPath;
    }
}
