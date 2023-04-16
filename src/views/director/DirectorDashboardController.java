/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorDashboardController implements Initializable {
    
    private Director director;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
//        System.out.println("What's up doc");
//        doctorIDLabel.setText(String.valueOf(this.doc.ID));
//        doctorNameLabel.setText(this.doc.name);
    }
    
    @FXML
    private void directorEmployeeListOnClick(ActionEvent event) {
    try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorEmployeeList.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void directorSchedulesOnClick(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorSchedules.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void directorPromoteTransferOnClick(ActionEvent event) {
            try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorPromotionOrTransfer.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void directorFinanceHRReportsOnClick(ActionEvent event) {
            try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorHRAndFinanceReports.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void directorSalaryOnClick(ActionEvent event) {
            try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorSalaries.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void directorBranchReportsOnClick(ActionEvent event) {
            try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorBranchReports.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }


    @FXML
    private void directorPastRecordsOnClick(ActionEvent event) {
            try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorPastRecords.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void directorPoliciesOnClick(ActionEvent event) {
            try{
        Parent root = FXMLLoader.load(getClass().getResource("DirectorPolicies.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
    
}
