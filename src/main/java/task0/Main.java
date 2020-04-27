package task0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Main.
 *
 * @author Ilya_Sukhachev
 */
public class Main {

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
//        List list = new LinkedList();
        list.add("First");
//        list.add(17);
        list.add("Second");
        List<String> list2 = list;
        for (Iterator<String> itemItr = list2.iterator(); itemItr.hasNext(); )
            System.out.println( itemItr.next() );

//        List<Integer> ints = Arrays.asList(1,2,3);
//        List<Number> nums = ints; // compile-time error
//        nums.set(2, 3.14);
//        assert ints.toString().equals("[1, 2, 3.14]");

//        List<String>[] lsa = new List<String>[10]; // не верно
//        List<String>[] lsa = new List[]{new ArrayList<>()};
//        Object[] oa = lsa; //ОK, List<String> - подтип Object
//        List<Integer> li = new ArrayList<Integer>();
//        li.add(new Integer(3));
//        oa[0] = li;
//        String s = lsa[0].get(0);

        //        List<Integer> ints = new ArrayList<Integer>();
//        List<? extends Number> nums = ints;
    }
}
