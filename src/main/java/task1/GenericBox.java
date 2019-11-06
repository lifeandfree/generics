package task1;

public class GenericBox<T extends Comparable<T>> {
    private T value;

    public GenericBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void print(){
        //method(value);
        System.out.println(value);
    }

    public int compareTo(T obj) {
        return this.value.compareTo(obj);
    }
}
