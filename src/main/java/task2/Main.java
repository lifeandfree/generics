package task2;

import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        MyStore<Long, Animal> animalStore = new MyStore<>();

        Map<Long, Cat> catMap = new HashMap<>();
//        catMap.put(1L, new Animal("Barsik"));
        catMap.put(2L, new Cat("Pushok"));
        catMap.put(3L, new Cat("Tisha"));

        animalStore.putAll(catMap);
    }
}
