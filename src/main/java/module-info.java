module com.heshanthenura.ballsimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.heshanthenura.ballsimulation to javafx.fxml;
    exports com.heshanthenura.ballsimulation;
}