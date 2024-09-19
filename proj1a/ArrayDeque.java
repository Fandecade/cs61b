public class ArrayDeque<T> {

    private T[] array;
    private int size;
    private int length;
    private int front;
    private int rear;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        front = 0;
        rear = 0;
        size = 0;
        length = 8;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, front);
        array = newArray;
    }

    public void addFirst(T item) {
        if(size == array.length) { //数组满时，扩充数组
            resize(array.length * 2);
        }
        array[front] = item;
        front = (front + 1) % array.length;
        size += 1;
    }

    public void addLast(T item) {
        if(size == array.length) {
            resize(array.length * 2);
        }
        array[rear] = item;
        rear = (rear + 1) % array.length;
        size += 1;
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T item = array[front];
        front = (front + 1) % array.length;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T item = array[rear];
        rear = (rear + 1) % array.length;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        return array[(front + index) % array.length];
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if(size == 0) {
            return;
        }
        while(front < rear) {
            System.out.print(array[front] + " ");
            front = (front + 1) % array.length;
        }
        System.out.println();
    }

    public ArrayDeque(ArrayDeque<T> other) {
        array = (T[]) new Object[8];
        front = 0;
        rear = 0;
        size = other.size;
        length = other.size;
        resize(length);
        while(front < rear) {
            array[front] = other.array[front];
            front = (front + 1) % array.length;
        }
    }
}
