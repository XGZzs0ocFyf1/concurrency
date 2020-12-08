package myhomework;

public class RequestHandler extends Thread {

    private final DataSystem ds;
    private final Backend storage;

    public RequestHandler(int id, DataSystem ds, Backend storage) {
        setName("Обработчик заявок №" + id);
        this.ds = ds;
        this.storage = storage;
        //setDaemon(true); optional for 5 client purposes
    }

    @Override
    public void run() {
       while (true){
           Request request = ds.receive();
           printStatus(request);
           storage.handleInvoce(request);
       }
    }

    public void printStatus(Request request){
        String sb = currentThread().getName() +
                ": " +
                " получена заявка на обработку по клиенту" +
                request.getClientName();
        System.out.println(sb);
    }
}
