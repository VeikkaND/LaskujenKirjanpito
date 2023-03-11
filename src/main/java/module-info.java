module com.example.laskujen_kirjanpito {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.laskujen_kirjanpito to javafx.fxml;
    exports com.example.laskujen_kirjanpito;
}