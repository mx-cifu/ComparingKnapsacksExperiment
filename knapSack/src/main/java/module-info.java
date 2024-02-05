module com.ksack.knapsack {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens com.ksack.knapsack to javafx.fxml;
    exports com.ksack.knapsack;
}