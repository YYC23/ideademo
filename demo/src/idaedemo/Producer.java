package idaedemo;

import java.util.Queue;

public class Producer extends Thread{

    private int max;
    private Queue<String> buffer;

    public Producer(int max, Queue<String> buffer) {
        this.max = max;
        this.buffer = buffer;
    }

    @Override
    public void run() {

        while(true){
            while (buffer.size()==max){

                try{
                    System.out.println("Producer waiting for consumer to pick up an element");
                    synchronized (buffer){
                        buffer.wait();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            synchronized (buffer){
                String value = "add";
                System.out.println("Producing: " + value);
                buffer.add(value);
                buffer.notifyAll();
            }

            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();

            }

        }

    }
}
