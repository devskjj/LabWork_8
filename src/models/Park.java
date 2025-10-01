package models;

public class Park {
    private int count;

    public Park(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
