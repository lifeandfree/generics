package task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Символ подстановки <?> и накладываемые ограничения
 */
public class Wildcard {


    public static void main(String[] args) {
        List<Number> list = Arrays.asList(10, 100, 1000);
        list.forEach(x -> System.out.println(x));

//        List<Number> spoiledList = new ArrayList<Integer>(); // Дженерики Инвариантны!
        List<?> intList = new ArrayList<Integer>(); // Но <?> позволяет обойти это ограничение
//
//        intList.add(10); // но компилятор все равно не доверяет нам! Безопасность типов НЕ ОБЕСПЕЧЕНА
//        intList = new ArrayList<String>(); // Мы же можем сделать "грязный хак" и провернуть такое
//        intList.add("text");


        List<? extends Number> numList = new ArrayList<Integer>(); // прикрутили ковариантность
        numList = new ArrayList<Double>();
//        numList.add(23.4); // ОЙ!

        // погодите, но ведь так работало!:
        System.out.println(sum(list));

        // стоп. а если...
        numList = Arrays.asList(6.6, 5.5, 4.4);
//        numList.add(null);
        numList.forEach(System.out::println);
//
        // и так работает:
        List<? super Integer> superList = new ArrayList<Number>(); // прикрутили контравариантность
        superList.add(54);
        System.out.println(superList.get(0));
//        System.out.println(getFirst(superList));
//

    }

    public static Double sum(List<? extends Number> numList) {
        return numList
                .stream()
                .map(Number::doubleValue)
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0D);
    }

    public static <T> T getFirst(List<? super T> list) {
//        return list.get(0); // что-то пошло не так...
        return null;
    }



    public static void changeList(List<?> list) {
        Object tmp = list.get(0);
//        list.set(1, tmp); // <?> == <? extends Object>

    }
}


//        принцип PECS (Producer Extends Consumer Super)
//        <? extends ...> позволяет только ПРОЧИТАТЬ значения (Producer) / но можно set(null)
//        <? super ...> можно только ЗАПИСАТЬ значения (Consumer) / но get вернет Object

/* Если нас спросят есть ли Дженерики в рантаме - мы ответим НЕТ!
 * Но мы то знаем, что это не совсем так )))
 * List<?> и Collection<?> (неограниченные символы подстановки) - Reifiable типы
 * механизм затирания типов их не трогает и они доступны в рантайме через Reflection */
