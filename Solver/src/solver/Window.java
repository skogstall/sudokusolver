package solver;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Window extends Application{
    public static void main(String[] args){
        launch(args);
    }

    public void color(TilePane tilePane, int startValue){
        for(int i = startValue; i<startValue +27; i= i +9) {
            tilePane.getChildren().get(i).setStyle("-fx-background-color: #F5A9BC;");
            tilePane.getChildren().get(i + 1).setStyle("-fx-background-color: #F5A9BC;");
            tilePane.getChildren().get(i + 2).setStyle("-fx-background-color: #F5A9BC;");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane groot = new BorderPane();
        TilePane tilePane = new TilePane();
        Scene scene = new Scene(groot, 40*9 + 3*15, 40*9 + 8*3 + 30*2);
        primaryStage.setTitle("Skogstall och Hackspetts Sudoku");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        HBox knappar = new HBox();
        Button button1 = new Button("Solve");
        Button button2 = new Button("Clear");
        button1.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 14));
        button2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 14));
        button1.setPadding(Insets.EMPTY);
        button2.setPadding(Insets.EMPTY);
        button1.setPrefSize(50,30);
        button2.setPrefSize(50,30);
        button1.setStyle("-fx-background-color: #FBEFF2;");
        button2.setStyle("-fx-background-color: #FBEFF2;");
        for(int i = 1; i<=81; i++){
            TextField t = new TextField(){
                @Override
                public void paste() { }
            };
            t.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(1));
            t.setPrefSize(40,40);
            t.setAlignment(Pos.CENTER);
            t.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
            tilePane.getChildren().add(t);
            tilePane.setMargin(t, new Insets(3));
        }
        button2.setOnAction(event ->{
            for(int i= 0; i<81;i++){
                ((TextField)tilePane.getChildren().get(i)).clear();
            }
        });
        color(tilePane, 0);
        color(tilePane, 6);
        color(tilePane, 30);
        color(tilePane, 54);
        color(tilePane, 60);
        tilePane.setStyle("-fx-background-color: #B4045F;");
        knappar.setStyle("-fx-background-color: #424242;");
        knappar.getChildren().addAll(button1,button2);
        knappar.setMargin(button1, new Insets(6));
        knappar.setMargin(button2, new Insets(6));
        groot.setTop(tilePane);
        groot.setBottom(knappar);
    }



    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if(e.getCharacter().matches("[1-9.]")){
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume();
                    }
                }else{
                    e.consume();
                }
            }
        };
    }
}

//#FBEFF2