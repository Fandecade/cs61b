public class LinkedListDeque<T> {
    /** the node class*/
    public class node {    //创建双链表node结点
        public node pre;
        public T item;
        public node next;

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

    private node dummy; //头结点
    private int size ;   //记录双端队列大小

    public LinkedListDeque() {  //创建一个空链表队列（头结点的pre和next都指向自己）
        dummy = new node(null,null);    //初始化头结点
        dummy.next = dummy;
        dummy.pre = dummy;
        size = 0;   //初始化大小为0
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        if(this.isEmpty()) {
            node s = new node(dummy, item, dummy);
            dummy.next = s;
            dummy.pre = s;
        }else{
            node s = new node(dummy, item, dummy.next);
            dummy.next.pre = s;
            dummy.next = s;
        }
        size += 1;
    }

    public void addLast(T item) {
        if(this.isEmpty()) {
            node s = new node(dummy, item, dummy);
            dummy.next = s;
            dummy.pre = s;
        }else {
            node s = new node(dummy.pre, item, dummy);
            dummy.pre.next = s;
            dummy.pre = s;
        }
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        node p = dummy.next;
        while(p != dummy) {
            System.out.print(p.item +" ");
            p = p.next;
        }
    }

    public T removeFirst(){
        if(isEmpty()) {
            return null;
        }
        node p =dummy.next; //待删元素
        dummy.next = p.next;
        p.next.pre = dummy;
        size -= 1;
        if(size == 0){
            dummy.pre = dummy;
        }
        return p.item;
    }

    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        node p = dummy.pre; //最后一个元素
        p.pre.next = dummy;
        dummy.pre = p.pre;
        size -= 1;
        if(size == 0){
            dummy.next = dummy;
        }
        return p.item;
    }

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

    public LinkedListDeque(LinkedListDeque other) {
        dummy =new node(null, null);
        dummy.next = dummy;
        dummy.pre = dummy;
        size = 0;
        for(int i = 0; i < other.size(); i++) {
            addLast((T)other.get(i));
        }
    }
}