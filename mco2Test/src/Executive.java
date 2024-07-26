public class Executive extends Room{
    public Executive(String roomName) {
        super(roomName);
        this.price = super.price * 1.35;
    }
}
