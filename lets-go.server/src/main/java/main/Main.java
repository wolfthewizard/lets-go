package main;

public class Main {

    public static void main(String[] args) {
        IJsonParser jsonParser = new JsonParser();
        ServerListener serverCommunicator = new ServerListener(jsonParser, new ActionProcesser(jsonParser));
    }
}
