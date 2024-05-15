module com.yearendproject.trashbash {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.yearendproject.trashbash to javafx.fxml;
    exports com.yearendproject.trashbash;
}