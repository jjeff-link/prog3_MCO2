/**
 * Represents a Deluxe Room
 * **/
public class Deluxe extends Room{
    /**
     * Constructor for instantiation
     * @param roomName name/number assigned to the created Room
     * **/
    public Deluxe(String roomName) {
        super(roomName);
        this.price = super.price * 1.20;
    }
}
