package task1;

public class ObjectBox {
    private Object value;

    public ObjectBox(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void print() {
        //method(value);
        System.out.println(value);
    }
}
