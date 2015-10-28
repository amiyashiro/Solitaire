
package solitaire.cards;

import java.util.ArrayList;
import javafx.scene.Node;
import solitaire.images.ImageManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.input.*;
import solitaire.Solitaire;

/**
 *
 * @author Adam
 */
public class Card {
    
    private final Image face;
    private final ImageView currentImage;
    
    public final String suit;
    public final int value;
    public final String ID;
    
    public Node getNode() {
        return currentImage;
    }
    
    public Image getImage() {
        return face;
    }
    
    public int findCard(ArrayList<Card> p, String ider) {
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).ID.equals(ider)) { 
                return i;
            }
        }
        return -1;
    }
    
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
        this.ID = suit + Integer.toString(value);
        this.face = ImageManager.getImage(suit + Integer.toString(value) + ".gif");
        
        currentImage = new ImageView();
        currentImage.setImage(face);
        currentImage.setId(this.getClass().getSimpleName());
        
        currentImage.setOnDragDetected((MouseEvent event) -> {
            Dragboard db = currentImage.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(ID);
            content.putImage(currentImage.getImage());
            db.setContent(content);
            db.setDragView(currentImage.getImage());
            event.consume();
        });
        
        currentImage.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            ArrayList<Card> b = Solitaire.board;
            
            if (db.hasString()) {
                String ider = db.getString();
                
                int indexDrag = findCard(b, ider);
                Card card = b.get(indexDrag);
                int indexDrop = findCard(b, this.ID);
                Card targetCard = b.get(indexDrop);
                
                if (card != null) {
                    if (indexDrop == indexDrag - 1 ||
                            indexDrop == indexDrag - 3) {
                        if (targetCard.suit.equals(card.suit) ||
                                targetCard.value == card.value) {
                            b.remove(card);
                            b.set(indexDrop, card);
                            Solitaire.updateBoard();
                        }
                    }
                    
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
        
        currentImage.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != currentImage && 
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    }
}
