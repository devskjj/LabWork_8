import models.Car;
import models.Journal;
import models.Park;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void runApplication() {
        Journal journal = new Journal();
        Park park = new Park(20);
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
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

        LocalDateTime ldtEnd = now.plusDays(5);

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

        LocalDate chooseDate = LocalDate.now().plusDays(3); // со скана запрашивать число
        Long total = journal.getTotalByDay(chooseDate);
        if (total == null) {
            System.out.printf("За %s нет заработка%n", chooseDate);
        } else {
            System.out.printf("Общ. сумма заработка за %s день: %s%n", chooseDate, total);
        }

        journal.printStatistic(); //статистика

        journal.printTopTenCars(); // топ 10

        journal.printLessStayed(30);

        journal.printCountRatio(park); // среднйи процент

        journal.printCarsByDate(chooseDate);

        LocalDateTime startHour = LocalDateTime.now().withHour(14).withMinute(0).withSecond(0); // c
        LocalDateTime endHour = startHour.plusHours(1); // по
        journal.printCarsByHour(startHour, endHour);

        journal.printDaysByCarId(cars.get(0).getCarId());
//
//        System.out.println(counter);

    }

    public static void switchMenu(String choice, Journal journal, Park park, List<Car> cars) {
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case "1" -> {
                System.out.println("Введите день: от 1 до 30");
                String answer = sc.nextLine();
                LocalDate chooseDate = LocalDate.now().plusDays(Long.parseLong(answer));
                Long total = journal.getTotalByDay(chooseDate);
                if (total == null) {
                    System.out.printf("За %s нет заработка%n", chooseDate);
                } else {
                    System.out.printf("Общ. сумма заработка за %s день: %s%n", chooseDate, total);
                }
            }
            case "2" -> journal.printStatistic();
            case "3" -> journal.printTopTenCars();
            case "4" -> journal.printLessStayed(30);
            case "5" -> journal.printCountRatio(park);
            case "6" -> {
                System.out.println("Введите номер дня: от 1 до 30");
                String day = sc.nextLine();
                LocalDate chooseNewDate = LocalDate.now().plusDays(Long.parseLong(day));
                journal.printCarsByDate(chooseNewDate);
                System.out.println("Введите номер часа: от 0 до 24");
                String hour = sc.nextLine();
                LocalDateTime start = LocalDateTime.now().withHour(Integer.parseInt(hour)).withMinute(0).withSecond(0);
                System.out.println("Введите продолжительность: ");
                String duration = sc.nextLine();
                LocalDateTime end = start.plusHours(Long.parseLong(duration));
                journal.printCarsByHour(start, end);
            }
            case "7" -> {
                cars.forEach(car -> System.out.println(car.getCarId()));
                System.out.print("Введите номер машины: ");
                String carId = sc.nextLine();
                journal.printDaysByCarId(carId);
            }
            case "8" -> {
                System.out.println("Выход из программы.");
                sc.close();
            }
            default -> System.out.println("Неверный выбор, пожалуйста, выберите из предложенного списка.");
        }
    }


}
