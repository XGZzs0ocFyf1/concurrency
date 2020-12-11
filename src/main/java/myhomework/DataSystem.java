package myhomework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataSystem {

    private final BlockingQueue<Request> REQUESTS = new ArrayBlockingQueue(2);

    public synchronized void sendRequest(Request request){
        try {
            REQUESTS.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    //get one element from REQUEST
    public  Request receiveRequest(){
        Request output = null;
        try {
            output = REQUESTS.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  output;
    }


}
