module com.heshanthenura.ballsimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.heshanthenura.ballsimulation to javafx.fxml;
    exports com.heshanthenura.ballsimulation;
}