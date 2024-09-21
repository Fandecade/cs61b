public class LinkedListDeque<T> implements Deque<T> {
    /** the node class*/
    public class node {    //创建双链表node结点
        private node pre;
        private T item;
        private node next;

        public  node(node pre, T item, node next) {  //node构造函数
            this.pre = pre;
            this.item = item;
            this.next = next;
        }

        public  node(node pre, node next) {   //创建头结点
            this.pre = pre;
            this.next = next;
        }
    }

    private node dummy; //头结点 设为final表示为常量
    private int size ;   //记录双端队列大小

    public LinkedListDeque() {  //创建一个空链表队列（头结点的pre和next都指向自己）
        dummy = new node(null,null);    //初始化头结点
        dummy.next = dummy;
        dummy.pre = dummy;
        size = 0;   //初始化大小为0
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        node s = new node(dummy, item, dummy.next);
        dummy.next.pre = s;
        dummy.next = s;
        size ++;
    }

    @Override
    public void addLast(T item) {
        node s = new node(dummy.pre, item, dummy);
        dummy.pre.next = s;
        dummy.pre = s;
        size ++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        node p = dummy.next;
        while(p != dummy) {
            System.out.print(p.item +" ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if(size == 0) {
            return null;
        }
        T item = dummy.next.item;
        dummy.next.next.pre = dummy;
        dummy.next =dummy.next.next;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T item = dummy.pre.item;
        dummy.pre.pre.next = dummy;
        dummy.pre = dummy.pre.pre;
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        if(isEmpty()) {
            return null;
        }
        int i = 0;
        node p = dummy.next;
        while(i != index && p != dummy){
            p = p.next;
        }
        return p == dummy ? null : p.item;
    }

    private T getReverseHelper(node start, int index) {
        if(index == 0) {
            return start.item;
        }
        return getReverseHelper(start.next, index - 1);
    }

    public T getReverse(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        return getReverseHelper(dummy.next, index);
    }

}