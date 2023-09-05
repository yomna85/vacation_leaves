package com.example.BackendTask.utils;

import com.example.BackendTask.support.ReportName;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
@AllArgsConstructor
@Log4j2
public class JasperReportUtil {

    private final DataSource dataSource;

    public void generatePdfReport(HttpServletResponse response, ReportName reportName, Map<String, Object> parameters) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            File jasper = new File(new File(reportName + ".jasper").getAbsolutePath());
            if (!jasper.exists()) {
                ClassPathResource classPathResource = new ClassPathResource("jasperReports/" + reportName + ".jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(classPathResource.getInputStream());
                JRSaver.saveObject(jasperReport, reportName + ".jasper");
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getAbsolutePath(),parameters, connection);
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            response.setHeader("Content-Disposition", "attachment;filename=" + reportName + ".pdf");
            response.setContentType("application/octet-stream");
            exporter.exportReport();
            connection.close();
        } catch (IOException ioException){
            log.error("Report Exception", ioException);
        }catch (JRException jrException){
            log.error("Report Exception", jrException);
        }catch (Exception e) {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                log.error("Report Exception", ex);
            }
            log.error("Report Exception", e);
        }
    }
}
