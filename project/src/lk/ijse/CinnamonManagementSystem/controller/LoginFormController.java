package lk.ijse.CinnamonManagementSystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.CinnamonManagementSystem.util.CrudUtil;
import lk.ijse.CinnamonManagementSystem.util.Navigation;
import lk.ijse.CinnamonManagementSystem.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginFormController implements Initializable {

    public AnchorPane pane;
    public TextField txtUserName;
    public TextField txtPassword;
    public Label txtTime;
    public Label txtDate;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
       // Navigation.navigate(Routes.MANAGE_DETAILS,pane);
       String userName = txtUserName.getText();
        String password = txtPassword.getText();

if(userName.equals("GAYANI")&& password.equals("1234")){
    //new Alert(Alert.AlertType.CONFIRMATION,"Login successful").show();
    //Navigation.navigate(Routes.MANAGE,pane);
    URL resource = this.getClass().getResource("/lk/ijse/CinnamonManagementSystem/view/ManageDetailsForm.fxml");
    Parent window = FXMLLoader.load(resource);
    Scene scene = new Scene(window);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();

}else if(userName.equals("gayani")&& password.equals("12345")){
   // new Alert(Alert.AlertType.CONFIRMATION,"Login successful").show();
   // Navigation.navigate(Routes.RECEPTION,pane);
    URL resource = this.getClass().getResource("/lk/ijse/CinnamonManagementSystem/view/ReceptionManageFrom.fxml");
    Parent window = FXMLLoader.load(resource);
    Scene scene = new Scene(window);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();

}
      //  public void setPaten(){
          //  Pattern idPattern = Pattern.compile("^(C0)([0-9]{1})([0-9]{1})$");
          //  idMatcher = idPattern.matcher(txtId.getText());

          //  Pattern namePattern = Pattern.compile("^[a-zA-Z\\s]+");
          //  nameMatcher = namePattern.matcher(txtName.getText());
        }

      /* String sql = "SELECT * FROM user WHERE userName = ? and userPassword = ?";
        ResultSet result = CrudUtil.execute(sql, userName, password);

        if (!result.next()) {
            System.out.println("Error");
        } else {
            URL resource = this.getClass().getResource("/lk/ijse/CinnamonManagementSystem/view/ManageDetailsForm.fxml");
            Parent window = FXMLLoader.load(resource);
            Scene scene = new Scene(window);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }*/


    private void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(format.format(new Date()));
    }
    private void timeNow() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        txtTime.setText(format.format(new Date()));

    }

    public void exitOnMouse(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        timeNow();
    }
}


