package task4;

import java.util.ArrayList;
import java.util.List;

public class ListBox<T> {

    List<T> someList;

    /**
     * @param list список, значения которого надо добавить во вложенный список обьекта
     */
    void addAll(List<? extends T> list) {
        for (T elt : list) {
            someList.add(elt);
        }
    }



    public static void main(String[] args) {
        ListBox<Number> box = new ListBox<>();
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(1);
        box.addAll(list);
    }
}
