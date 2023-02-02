package lk.ijse.CinnamonManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CinnamonManagementSystem.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class ReportController {
    public AnchorPane pane;

    public void btnSupplierdReport(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/CinnamonManagementSystem/view/report/supplier.jrxml");


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());

            //                  JasperPrintManager.printReport(jasperPrint,true);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCustomerReport(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/CinnamonManagementSystem/view/report/Blank_A4.jrxml");


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());

            //                  JasperPrintManager.printReport(jasperPrint,true);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItemReport(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/CinnamonManagementSystem/view/report/Item.jrxml");


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());

            //                  JasperPrintManager.printReport(jasperPrint,true);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnEmplyoeesReport(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/CinnamonManagementSystem/view/report/gayani.jrxml");


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getDbConnection().getConnection());

            //                  JasperPrintManager.printReport(jasperPrint,true);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
