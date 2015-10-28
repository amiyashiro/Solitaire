
package solitaire.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import solitaire.Solitaire;
import solitaire.images.ImageManager;
/**
 *
 * @author Adam
 */
public class Deck {
    
    private List<Card> deck;
    private Pane deckPane;
    private ImageView deckImage;
    
    private final String []suits = {
      "spades", "clubs", "hearts", "diamonds"  
    };
    
    public Pane getDeckPane() {
        return deckPane;
    }
    
    public List<Card> getDeck() {
        if (deck == null) {
            buildDeck();
        }
        return deck;
    }
    
    private void buildDeck() {
        deck = new ArrayList<>();
        for (String suit : suits) {
            for (int value = 1; value < 14; value++) {
                Card c = new Card(suit, value);
                deck.add(c);
            }
        }
        shuffle();
    }
    
    private void shuffle() {
        Random randomGen = new Random();
        Card c;
        Card temp;
        int n, size;
        size = deck.size();
        
        for (int i = 0; i < size - 1; i++) {
            n = randomGen.nextInt(size - i) + i;
            temp = deck.get(i);
            deck.set(i, deck.get(n));
            deck.set(n, temp);
            
        }
    }
    
    public Deck(FlowPane itemPane) {
        deckPane = new Pane();
        deckImage = new ImageView(ImageManager.getResource("backbluepattern.gif"));
        deckPane.getChildren().add(deckImage);
        buildDeck();
        
        deckPane.setOnMouseClicked((MouseEvent event) -> {
                        
            Card c = deck.remove(0);
            if (deck.isEmpty()) {
                deckPane.setVisible(false);
            }
            
            Solitaire.board.add(c);
            Solitaire.updateBoard();
        });
    }
    
}
