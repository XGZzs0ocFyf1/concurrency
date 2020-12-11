package myhomework;

import java.util.ArrayDeque;

public class DataSystem {

    private final ArrayDeque<Request> REQUESTS = new ArrayDeque<>();

    public synchronized void sendInvoice(Request request){
        while (REQUESTS.size() >= 2){
            try{
                wait();
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
                System.err.println("Thread interrupted "+e);
            }
        }

        REQUESTS.add(request);
        notifyAll();
       }


    public synchronized Request receive(){
        while( REQUESTS.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted");
                e.printStackTrace();
            }
        }

        Request output = REQUESTS.removeFirst();
        notifyAll();
        return output;
    }


}
