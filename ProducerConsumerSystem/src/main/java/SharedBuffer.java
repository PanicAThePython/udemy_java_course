import java.util.LinkedList;
import java.util.List;

public class SharedBuffer {

    private final List<Order> buffer = new LinkedList<>();
    private final int capacity;

    public SharedBuffer(int capacity){
        this.capacity = capacity;
    }

    public synchronized void produce(Order order) throws InterruptedException {
        while (buffer.size() == capacity){
            System.out.println("Buffer is full, Producer is waiting...");
            wait();
        }
        buffer.add(order);
        System.out.println("New order added: " + order);
        notifyAll();
    }

    public synchronized Order consume() throws InterruptedException {
        while (buffer.isEmpty()){
            System.out.println("Buffer is empty, Consumer is waiting...");
            wait();
        }
        Order order = buffer.removeFirst();
        System.out.println("Processing order: " + order);
        notifyAll();
        return order;
    }
}
