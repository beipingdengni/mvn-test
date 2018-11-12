package com.tian.spring.jvm;

/**
 * @author tianbeiping
 * @Title: VolatileDemo
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/16下午6:08
 */
public class VolatileDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();
        NewThread runnable = new NewThread(bank);
//        new NewThread(bank).start();
//        new NewThread(bank).start();
//        new NewThread(bank).start();
//        new NewThread.start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    static class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ","+i +"账余额为1===>：" + bank.account);
                bank.save(10);
                System.out.println(Thread.currentThread().getName() + ","+i +"账余额为2：" + bank.account);
            }

        }
    }

    static class Bank {
        public int account = 100;

//        public synchronized int getAccount() {
//            return account;
//        }

        public synchronized void save(int money) {
            account += money;
//            System.out.println(Thread.currentThread().getName());
        }

    }
}
