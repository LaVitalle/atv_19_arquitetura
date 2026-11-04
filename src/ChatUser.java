public class ChatUser {

    private final String name;
    private final ChatMediator mediator;

    public ChatUser(String name, ChatMediator mediator) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (mediator == null) {
            throw new IllegalArgumentException("Mediator cannot be null");
        }
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    ChatMediator getMediator() {
        return mediator;
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void sendPrivate(String recipientName, String message) {
        mediator.sendPrivate(message, this, recipientName);
    }

    public void receive(String senderName, String message) {
        System.out.println(name + " received from " + senderName + ": " + message);
    }
}
