import java.util.LinkedList;

public class ArrayDeque<T> implements Deque<T> {

    private T[] array;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];

        System.arraycopy(array, 0, newArray, 0, front);
        array = newArray;
    }

    @Override
    public void addFirst(T item) {
        if(size == array.length){ //数组满时，扩充数组
            resize(array.length * 2);
        }
        array[front] = item;
        front = (front + 1) % array.length;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if(size == array.length){
            resize(array.length * 2);
        }
        array[rear] = item;
        rear = (rear + 1) % array.length;
        size += 1;
    }

    @Override
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T item = array[front];
        front = (front + 1) % array.length;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        T item = array[rear];
        rear = (rear + 1) % array.length;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            return null;
        }
        return array[(front + index) % array.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for(int i = 0; i < size; i++){
            System.out.print(array[front] + " ");
            front = (front + 1) % array.length;
        }
        System.out.println();
    }
/*
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
 */
}
