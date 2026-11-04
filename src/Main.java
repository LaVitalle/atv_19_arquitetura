public class Main {

    public static void main(String[] args) {
        ChatMediator room = new ChatRoom();

        ChatUser ana = new ChatUser("Ana", room);
        ChatUser bruno = new ChatUser("Bruno", room);
        ChatUser carla = new ChatUser("Carla", room);

        System.out.println("=== Registering users ===");
        room.register(ana);
        room.register(bruno);
        room.register(carla);
        room.register(bruno);

        System.out.println("\n=== Message from Ana ===");
        ana.send("Hello, everyone!");

        System.out.println("\n=== Diego joins, message from Bruno ===");
        ChatUser diego = new ChatUser("Diego", room);
        room.register(diego);
        bruno.send("Welcome, Diego!");

        System.out.println("\n=== User associated with the room but not registered ===");
        ChatUser eva = new ChatUser("Eva", room);
        eva.send("Is anyone here?");

        System.out.println("\n=== Another user with an existing name ===");
        ChatUser otherAna = new ChatUser("Ana", room);
        room.register(otherAna);

        System.out.println("\n=== Private messages ===");
        ana.sendPrivate("Carla", "Can we talk later?");
        ana.sendPrivate("Fernando", "Hi!");
        ana.sendPrivate("Ana", "Note to self");
    }
}
