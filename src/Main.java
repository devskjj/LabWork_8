import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = new Random()
                .ints(30, 1, 18)
                .boxed()
                .toList();

        System.out.println(numbers);

        Canvas canvas = new Canvas();

        canvas.drawBorder("#");
//        canvas.setPixel(3, 5, "@");

        for(int i = 0; i < numbers.size(); i++) {
            for(int j = 0; j < numbers.get(i); j++) {
                canvas.setPixel((i + 1) * 2, canvas.getHeight() - j - 2, "*");
            }

        }

        canvas.printTextLine(5, 5, " My canvas ");
        System.out.println(canvas);


        LocalDateTime now = LocalDateTime.now();

        LocalDateTime ldtEnd = now.plusDays(30);

        System.out.println(now);
        System.out.println(ldtEnd);

        int counter = 0;
        for (LocalDateTime i = now; i.isBefore(ldtEnd); i = i.plusMinutes(5)) {
            System.out.println(i);
            counter++;
        }

        System.out.println(counter);

    }
}