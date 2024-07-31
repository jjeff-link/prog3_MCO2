/**
 * Represents an Executive Room
 * **/
public class Executive extends Room{
    /**
     * Constructor for instantiation
     * @param roomName name/number assigned to the created Room
     * **/
    public Executive(String roomName) {
        super(roomName);
        this.price = super.price * 1.35;
    }
}
