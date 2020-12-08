package myhomework;

import java.util.ArrayDeque;

public class DataSystem {


    private ArrayDeque<Request> requests = new ArrayDeque<>();

   /* true if reciever should wait
    false if sender should wait
     */


    public synchronized void sendInvoice(Request request){
        while (requests.size() >= 2){
            try{
                wait();
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
                System.err.println("Thread interrupted "+e);
            }
        }

        requests.add(request);
        notifyAll();
       }


    public synchronized Request receive(){
        while( requests.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted");
                e.printStackTrace();
            }
        }

        Request output = requests.removeFirst();
        notifyAll();
        return output;
    }


}
