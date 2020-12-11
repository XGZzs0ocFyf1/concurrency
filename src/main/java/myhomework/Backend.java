package myhomework;

/**
 * Imitation of backend system for
 * concurrent toy bank program.
 * This architecture used in study purposes.
 *
 * Class Backend handles requests that generating
 * in the DataSystem by Client instances.
 */
public class Backend {

    private long amount = 0;

    public synchronized void handleRequest(Request request){
        long money = request.getAmount();


        long sum = request.getOperation().equals(RequestType.CREDIT)
                ? amount - Math.abs(money)
                : amount + Math.abs(money);
        if (sum < 0){
             printInfo(request, false, "; cумма больше баланса банка");
        }else {
            amount += Math.abs(money);
            printInfo(request, true, "");
        }

    }

    public void printInfo(Request request, boolean status, String message){
        StringBuilder sb = new StringBuilder();
        sb.append("Бэк система: ");
        sb.append(request);
        sb.append(status ? " УСПЕШНО ВЫПОЛНЕНА;" : " НЕ ВЫПОЛНЕНА;");
        sb.append(" получена от ");
        sb.append(Thread.currentThread().getName());
        sb.append(message);
        sb.append(". Баланс банка: ");
        sb.append(amount);
        System.out.println(sb.toString());


    }

}
