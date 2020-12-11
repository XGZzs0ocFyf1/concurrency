package myhomework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataSystem {

    private final BlockingQueue<Request> REQUESTS = new ArrayBlockingQueue(2);

    public synchronized void sendInvoice(Request request){

        while (REQUESTS.size() == 2){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (REQUESTS.size() < 2){
            REQUESTS.add(request);
        }
    }


    public  Request receive(){
        while (REQUESTS.size() == 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return  REQUESTS.poll();
    }


}
