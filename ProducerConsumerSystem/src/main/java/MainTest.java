public class MainTest {

    public static void main(String[] args){
        SharedBuffer sharedBuffer = new SharedBuffer(5);

        Thread producer = new Thread(new Producer(sharedBuffer), "Producer Thread");
        Thread consumer = new Thread(new Consumer(sharedBuffer), "Consumer Thread");

        producer.start();
        consumer.start();

    }
}
