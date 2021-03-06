package solver;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Window extends Application{
    public static void main(String[] args){
        launch(args);
    }

    private void color(TilePane tilePane, int startValue){
        for(int i = startValue; i<startValue +27; i= i +9) {
            tilePane.getChildren().get(i).setStyle("-fx-background-color: #F5A9BC;");
            tilePane.getChildren().get(i + 1).setStyle("-fx-background-color: #F5A9BC;");
            tilePane.getChildren().get(i + 2).setStyle("-fx-background-color: #F5A9BC;");
        }
    }

    private void utput(TilePane tilePane, int[] lista){
        for(int i=0; i<81; i++){
            ((TextField) tilePane.getChildren().get(i)).setText(Integer.toString(lista[i]));
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

            t.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                       t.clear();
                    }
                }
            });
            t.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation());
            t.setPrefSize(40,40);
            t.setAlignment(Pos.CENTER);
            t.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
            tilePane.getChildren().add(t);
            TilePane.setMargin(t, new Insets(3));
        }
        button1.setOnAction(event -> {
            Solver solver = new Solver(getValues(tilePane));
            if(solver.solve()){
                utput(tilePane, solver.getSudoku());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No solution found");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
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
        HBox.setMargin(button1, new Insets(6));
        HBox.setMargin(button2, new Insets(6));
        groot.setTop(tilePane);
        groot.setBottom(knappar);

    }
    private int[] getValues(TilePane pane){
        int[] array = new int[81];
        for(int i = 0; i<=80; i++){
            int value = 0;
            TextField f = (TextField)pane.getChildren().get(i);
            try{ value = Integer.parseInt(f.getCharacters().toString());}
            catch(Exception e){
            }
            array[i]=value;
        }
        return array;
    }
    private EventHandler<KeyEvent> numeric_Validation() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= 1) {
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