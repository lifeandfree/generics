package task3;


public class ClassAndMethod {

    public static void main(String[] args) {
        /* Обобщенный класс */
        GenericClass<Integer, String> intString = new GenericClass<>(5, "пять");
        System.out.println(intString);

        GenericClass<Character, Double> charDouble = new GenericClass<>((char) 53, 5.008);
        System.out.println(charDouble);

        /* обобщенный метод */
        Integer[] a = {1};
        Integer[] b = {2};
//        Long[] b = {2L};
        swap(a, b);
        System.out.println(a[0] + "--" + b[0]);

        String[] dog = {"Dog"};
        String[] cat = {"Cat"};
        swap(dog, cat);
        System.out.println(dog[0] + "--" + cat[0]);
    }

    /* обобщенный метод - одна функция применяется к разным типам*/
    static <T> void swap(T[] a, T[] b) {
        T tmp = a[0];
        a[0] = b[0];
        b[0] = tmp;
    }
}

/* Обобщенный класс */
class GenericClass<T, W> { // T и W переменные для хранения Типа
    public T field1;
    public W field2;

    public GenericClass(T wedth, W height) {
        this.field1 = wedth;
        this.field2 = height;
    }

    @Override
    public String toString() {
        return String.format("wedth = %s; height = %s", field1, field2);
    }

}