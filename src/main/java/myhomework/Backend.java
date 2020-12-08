package myhomework;

public class Backend {

    private long amount = 0;

    public synchronized void handleInvoce(Request request){
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
