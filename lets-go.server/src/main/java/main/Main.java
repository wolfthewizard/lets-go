package main;

public class Main {

    public static void main(String[] args) {
        IJsonParser jsonParser = new JsonParser();
        ServerCommunicator serverCommunicator = new ServerCommunicator(jsonParser, new ActionProcesser(jsonParser));
    }
}
