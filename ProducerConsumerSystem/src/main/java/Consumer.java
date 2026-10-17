public class Consumer implements Runnable{

    private final SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer){
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        while(true){
            try{
                Order order = sharedBuffer.consume();
                System.out.println("Delivered order: " + order);
                Thread.sleep(2000L);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
