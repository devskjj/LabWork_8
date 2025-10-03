package models;

public class Park {
    private int count;
    private int initialCount;

    public Park(int count) {
        this.count = count;
        this.initialCount = count;
    }

    public int getCount() {
        return count;
    }

    public int getInitialCount() {
        return initialCount;
    }

    public void decreaseCount() {
        if (count > 0) {
            count--;
        }
    }

    public void increaseCount() {
        count++;
    }
}
