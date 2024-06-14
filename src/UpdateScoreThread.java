public class UpdateScoreThread extends Thread {

    public static boolean running = true;

    public void run() {
        while (running) {
            try {
                switch (Menu.difficulty.getText()) {
                    case "LEICHT":
                        Thread.sleep(1000);
                        break;
                    case "NORMAL":
                        Thread.sleep(750);  //waittime passend zur geschwindigkeit anpassen
                        break;
                    case "SCHWER":
                        Thread.sleep(500);
                        break;
                    case "EXTREM":
                        Thread.sleep(300);
                        break;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (running) GameFrame.updateScore(GameFrame.getScore() + 1);
        }
    }
}