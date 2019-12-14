package contract;

public class ResponsePrisoners {

    private int yourPrisoners;
    private int enemyPrisoners;

    public ResponsePrisoners(int yourPrisoners, int enemyPrisoners) {
        this.yourPrisoners = yourPrisoners;
        this.enemyPrisoners = enemyPrisoners;
    }

    public void swapPrisoners() {
        int temp = yourPrisoners;
        yourPrisoners = enemyPrisoners;
        enemyPrisoners = temp;
    }

    public int getEnemyPrisoners() {
        return enemyPrisoners;
    }

    public int getYourPrisoners() {
        return yourPrisoners;
    }
}
