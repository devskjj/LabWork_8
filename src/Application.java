import models.Car;
import models.Journal;
import models.Park;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Application {
    public static void runApplication() {
        Journal journal = new Journal();
        Park park = new Park(20);
        List<Car> cars = new ArrayList<>();


        for (int i = 0; i < 200; i++) {
            cars.add(new Car());
        }


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ldtEnd = now.plusDays(30);




        while (now.isBefore(ldtEnd)) {

            now = now.plusMinutes(5);


            for (Car car : cars) {
                car.update(park, journal, now);
            }


            // if (now.getMinute() == 0 && now.getHour() == 12) {
            //     System.out.println("Парковка на " + now.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ". Свободно мест: " + park.getCount());
            // }
        }





        System.out.println("Обработка машин, оставшихся на парковке...");
        for (Car car : cars) {
            if (car.getState().getValue().equals("НА ПАРКОВКЕ")) {
                journal.saveDeparture(car.getCarId(), ldtEnd);
            }
        }


        System.out.println("\n--- ЖУРНАЛ ПАРКОВКИ ---\n");
        journal.printLog();

        // Здесь должен быть код для реализации пользовательского меню,
        // а также вычисления статистики и отрисовки гистограмм (Canvas).

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
//
//        // ... логика рисования гистограммы ...
//
//        canvas.printTextLine(5, 5, " My canvas ");
//        System.out.println(canvas);
    }
}