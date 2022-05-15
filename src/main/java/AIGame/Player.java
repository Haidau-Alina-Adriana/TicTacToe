package AIGame;

import utils.AIGameUtils;

//public class Player implements Runnable {
//    private int index;
//    private boolean running;
//
//    public Player() {
//        this.running = true;
//    }
//
//    public Player(int index) {
//        this.index = index;
//        this.running = true;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public boolean isRunning() {
//        return running;
//    }
//
//    public void setRunning(boolean running) {
//        this.running = running;
//    }
//
//    private synchronized void makeAMove() {
//        System.out.println("index " + index);
//        while (AIGameUtils.turn % 2 != this.index && AIGameUtils.getNumberOfFreePositions() != 0) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        if (AIGameUtils.getNumberOfFreePositions() == 0) {
//            setRunning(false);
//            return;
//        }
////        if(index % 2 == 0){
////            System.out.println("i'm user");
////        }else{
////            System.out.println("i'm ai");
////        }
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        do {
//            System.out.println("Still running " + index);
//            makeAMove();
//        } while (isRunning());
//        System.out.println("Exited: " + index);
//    }
//
//    @Override
//    public String toString() {
//        return "Player{" +
//                "index=" + index +
//                ", running=" + running +
//                '}';
//    }
//
//}

public class Player {
    private int index;

    public Player() {
    }

    public Player(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Player{" +
                "index=" + index +
                '}';
    }
}
