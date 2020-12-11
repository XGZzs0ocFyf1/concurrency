package myhomework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private final static ExecutorService  clients = Executors.newFixedThreadPool(5);
    private final static ExecutorService handlers = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        DataSystem ds = new DataSystem();
        Backend storage = new Backend();


        for (int i = 0; i < 5; i++) {
            Client c = new Client(i, ds);
            c.createInvoice(ThreadLocalRandom.current().nextInt(20000), RequestType.PAYMENT);
            clients.execute(c);
        }

        for (int i = 0; i < 2; i++) {
            RequestHandler handler = new RequestHandler(i, ds, storage);
            handlers.execute(handler);
        }

        clients.shutdown();


    }


    /***
     * updated output log
     *
     Клиент №1: Заявка{clientTreadName ='Клиент №1', amount=6131, operation =PAYMENT} отправлена в банк
     Клиент №2: Заявка{clientTreadName ='Клиент №2', amount=9568, operation =PAYMENT} отправлена в банк
     Клиент №4: Заявка{clientTreadName ='Клиент №4', amount=6871, operation =PAYMENT} отправлена в банк
     Клиент №0: Заявка{clientTreadName ='Клиент №0', amount=5205, operation =PAYMENT} отправлена в банк
     Клиент №3: Заявка{clientTreadName ='Клиент №3', amount=17618, operation =PAYMENT} отправлена в банк
     Обработчик заявок №0:  получена заявка на обработку по клиентуКлиент №1
     Бэк система: Заявка{clientTreadName ='Клиент №1', amount=6131, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от pool-2-thread-1. Баланс банка: 6131
     Обработчик заявок №1:  получена заявка на обработку по клиентуКлиент №0
     Бэк система: Заявка{clientTreadName ='Клиент №0', amount=5205, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от pool-2-thread-2. Баланс банка: 11336
     Обработчик заявок №0:  получена заявка на обработку по клиентуКлиент №3
     Бэк система: Заявка{clientTreadName ='Клиент №3', amount=17618, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от pool-2-thread-1. Баланс банка: 28954
     Обработчик заявок №0:  получена заявка на обработку по клиентуКлиент №2
     Бэк система: Заявка{clientTreadName ='Клиент №2', amount=9568, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от pool-2-thread-1. Баланс банка: 38522
     Обработчик заявок №0:  получена заявка на обработку по клиентуКлиент №4
     Бэк система: Заявка{clientTreadName ='Клиент №4', amount=6871, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от pool-2-thread-1. Баланс банка: 45393
     */

}
