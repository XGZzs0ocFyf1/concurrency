package myhomework;


/**
 * Daemon type runnable.
 * Work instantly for handling Requests from
 * DataSystem.REQUESTS field which is ArrayBlockingQueue
 */
public class RequestHandler extends Process implements Runnable {


    private final Backend storage;

    public RequestHandler(int id, DataSystem ds, Backend storage) {
        setName("Обработчик заявок №" + id);
        this.ds = ds;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            Request request = ds.receiveRequest();
            printStatus(request);
            storage.handleRequest(request);
        }
    }

    public void printStatus(Request request) {
        String clientName = "Заявки нет!";
        if (request != null){
            clientName = request.getClientName();
        }
        String sb = name +
                ": " +
                " получена заявка на обработку по клиенту" +
                clientName;
        System.out.println(sb);
    }
}
