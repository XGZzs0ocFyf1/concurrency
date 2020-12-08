package myhomework;

public class Backend {

    private long amount = 0;

    public synchronized void handleInvoce(Invoice invoice){
        long money = invoice.getAmount();


        long sum = invoice.getOperation().equals(Operation.CREDIT)
                ? amount - Math.abs(money)
                : amount + Math.abs(money);
        if (sum < 0){
             printInfo(invoice, false, "; cумма больше баланса банка");
        }else {
            amount += Math.abs(money);
            printInfo(invoice, true, "");
        }

    }

    public void printInfo(Invoice invoice, boolean status, String message){
        StringBuilder sb = new StringBuilder();
        sb.append("Бэк система: ");
        sb.append(invoice);
        sb.append(status ? " УСПЕШНО ВЫПОЛНЕНА;" : " НЕ ВЫПОЛНЕНА;");
        sb.append(" получена от ");
        sb.append(Thread.currentThread().getName());
        sb.append(message);
        sb.append(". Баланс банка: ");
        sb.append(amount);
        System.out.println(sb.toString());


    }

}
