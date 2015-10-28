
package solitaire;

import java.util.ArrayList;
import solitaire.cards.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Adam
 */
public class Solitaire extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        itemPane = createItemPane();
        Deck deck = new Deck(itemPane);
        
        GridPane content = new GridPane();
        content.add(deck.getDeckPane(), 0, 0);
        content.add(itemPane, 0, 1);
        content.setPadding(new Insets(10));
        
        
        VBox root = new VBox();
        root.getChildren().addAll(content);
        Scene scene = new Scene(root, 1000, 900);
        scene.getStylesheets().add(Solitaire.class.getResource("Layout.css").toExternalForm());
        primaryStage.setTitle("Solitaire");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static FlowPane itemPane = null;
    public static ArrayList<Card> board = new ArrayList<Card>();
            
    public FlowPane createItemPane() {
        if (!(itemPane == null)) {
            return itemPane;
        }
        
        itemPane = new FlowPane();
        itemPane.setPadding(new Insets(10));
        itemPane.setMinWidth(900);
        itemPane.setHgap(10);
        itemPane.setVgap(10);

        return itemPane;
    }
    
    public static void updateBoard() {
        itemPane.getChildren().clear();
        for (Card c : board) {
            itemPane.getChildren().add(c.getNode());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
