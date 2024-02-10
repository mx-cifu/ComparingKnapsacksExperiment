module com.example.strassen {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens com.example.strassen to javafx.fxml;
    exports com.example.strassen;
}