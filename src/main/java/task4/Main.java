package task4;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String someThing = "что угодно";


        ObjectBox objectBox = new ObjectBox(someThing);


        GenericBox<Double> genericBox = new GenericBox<>(5D);

        ListBox<String> lBox = new ListBox<>();
        lBox.addAll(Arrays.asList("qweqwe", "asdasd","zxczxc"));

    }
}
