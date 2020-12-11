package myhomework;

public class Client extends Process implements Runnable {

    public Client(int id, DataSystem ds) {
        setName("Клиент №"+id);
        this.ds = ds;
    }


    public void createInvoice(int amount, RequestType requestType){
        this.request = new Request(name, amount, requestType);

    }

    @Override
    public void run() {
        printStatus();
        ds.sendInvoice(this.request);
    }

    public void printStatus(){
        String sb = name +
                ": " +
                this.request.toString() +
                " отправлена в банк";
        System.out.println(sb);
    }


}
