import models.Car;
import models.Journal;
import models.Park;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void runApplication() {
        Journal journal = new Journal();
        Park park = new Park(20);
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cars.add(new Car());
        }

//        List<Integer> numbers = new Random()
//                .ints(30, 1, 18)
//                .boxed()
//                .collect(Collectors.toList());
//
//        System.out.println(numbers);
//
//        Canvas canvas = new Canvas();
//
//        canvas.drawBorder("#");
////        canvas.setPixel(3, 5, "@");
//
//        for(int i = 0; i < numbers.size(); i++) {
//            for(int j = 0; j < numbers.get(i); j++) {
//                canvas.setPixel((i + 1) * 2, canvas.getHeight() - j - 2, "*");
//            }
//
//        }
//
//        canvas.printTextLine(5, 5, " My canvas ");
//        System.out.println(canvas);
//
//
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime ldtEnd = now.plusDays(1);

//        System.out.println(now);
//        System.out.println(ldtEnd);
//
//        int counter = 0;
        for (LocalDateTime i = now; i.isBefore(ldtEnd); i = i.plusMinutes(5)) {
            System.out.println(i);
            LocalDateTime current = i;
            cars.forEach(obj -> obj.update(park, journal, current));
            cars.forEach(obj -> obj.showCar());
        }

        journal.printLog();
//
//        System.out.println(counter);

    }
}
