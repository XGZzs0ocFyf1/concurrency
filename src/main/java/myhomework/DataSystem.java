package myhomework;

import java.util.ArrayDeque;

public class DataSystem {


    private ArrayDeque<Invoice> invoices = new ArrayDeque<>();

   /* true if reciever should wait
    false if sender should wait
     */


    public synchronized void sendInvoice(Invoice invoice){
    //    System.out.printf("DS:send:приступил к работе (%s) В очереди %s заявок \n", invoice.getClientName(),invoices.size());
        while (invoices.size() >= 2){
            try{
                wait();
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
                System.err.println("Thread interrupted "+e);
            }
        }

        invoices.add(invoice);
        ((Client)Thread.currentThread()).printStatus();
        notifyAll();
       // System.out.printf("DS:send:завершил работу(%s) В очереди %s заявок \n", invoice.getClientName(),invoices.size());


    }


    public synchronized Invoice receive(){
      //  System.out.printf("DS:receive: начал работу: в очереди %s заявок \n", invoices.size());
        while( invoices.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted");
                e.printStackTrace();
            }
        }

        //here we go!
        Invoice output = invoices.removeFirst();
        ((Worker)Thread.currentThread()).printStatus(output);
        //System.out.printf("DS:receive: закончил работу (%s) В очереди %s заявок \n", output.getClientName(),invoices.size());
        notifyAll();

        return output;
    }
    

}
