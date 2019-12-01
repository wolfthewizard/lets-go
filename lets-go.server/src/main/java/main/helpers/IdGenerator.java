package main.helpers;

import java.util.ArrayList;
import java.util.Random;

public class IdGenerator implements IIdGenerator{

    private final int MAX_ID = 10000;
    ArrayList<Integer> existingIds = new ArrayList<>();
    Random randomGenerator = new Random();

    public int generateId() {

        int id = randomGenerator.nextInt(MAX_ID);

        while (existingIds.contains(id)) {
            id = randomGenerator.nextInt(MAX_ID);
        }

        return id;
    }
}
