
public class Main {
    public static void main(String[] args) {
        try {
            Application.runApplication();
        } catch (NullPointerException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }
    }
}