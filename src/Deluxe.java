public class Deluxe extends Room{
    public Deluxe(String roomName) {
        super(roomName);
        this.price = super.price * 1.20;
    }
}
