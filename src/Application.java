import models.Car;
import models.Journal;
import models.Park;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    public static void runApplication() {
        Journal journal = new Journal();
        Park park = new Park(20);
        List<Car> cars = createCars(10);

        simulateDays(30, journal, park, cars);
        initMenu(journal, park, cars);


    }

    private static void simulateDays(int days, Journal journal, Park park, List<Car> cars) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ldtEnd = now.plusDays(days);
        for (LocalDateTime i = now; i.isBefore(ldtEnd); i = i.plusMinutes(5)) {
            LocalDateTime current = i;
            cars.forEach(obj -> obj.update(park, journal, current));
        }
    }

    private static List<Car> createCars(int size) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cars.add(new Car());
        }
        return cars;
    }

    public static boolean switchMenu(String choice, Journal journal, Park park, List<Car> cars) {
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case "1" -> {
                System.out.println("Введите день: от 0 до 30 (0 - это сегодня)");
                String answer = sc.nextLine().trim();
                LocalDate chooseDate = LocalDate.now().plusDays(Long.parseLong(answer));
                Long total = journal.getTotalByDay(chooseDate);
                if (total == null) {
                    System.out.printf("За %s нет заработка%n", chooseDate);
                } else {
                    System.out.printf("Общ. сумма заработка за %s день: %s%n", chooseDate, total);
                }
            }
            case "2" -> drawCanvasForMoney(journal);
            case "3" -> journal.printTopTenCars();
            case "4" -> drawCanvas(journal.printLessStayed(30), "Машины припаркованыне менее 30 минут в день");
            case "5" -> drawCanvas(journal.printCountRatio(park), "Среднее кол-во занятых мест каждый день");
            case "6" -> {
                System.out.println("Введите номер дня: от 1 до 30");
                String day = sc.nextLine().trim();
                LocalDate chooseNewDate = LocalDate.now().plusDays(Long.parseLong(day));
                journal.printCarsByDate(chooseNewDate);
                System.out.println("Введите номер часа: от 0 до 24");
                String hour = sc.nextLine().trim();
                LocalDateTime start = LocalDateTime.now().withHour(Integer.parseInt(hour)).withMinute(0).withSecond(0);
                System.out.println("Введите продолжительность: ");
                String duration = sc.nextLine().trim();
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
                journal.printLog();
            }
            case "9" -> {
                System.out.println("Выход из программы.");
                return false;
            }
            default -> {
                System.out.println("Неверный выбор, пожалуйста, выберите из предложенного списка.");
                break;
            }
        }
        return true;
    }

    private static void showMenu() {
        System.out.println("====================================");
        System.out.println("Меню:");
        System.out.println("1) Общая сумма заработка за один рабочий день");
        System.out.println("2) Минимальная, средняя и максимальная сумма заработка за период симуляции (с гистограммой)");
        System.out.println("3) Топ 10 машин, стоявших на парковке дольше всего");
        System.out.println("4) Сколько машин стояло на парковке меньше 30 минут (с гистограммой)");
        System.out.println("5) Средний процент загруженности парковки в день (с гистограммой)");
        System.out.println("6) Список побывавших машин на парковке за определенный час/день");
        System.out.println("7) По номеру машины вывести все дни, в которые она была на парковке");
        System.out.println("8) Информация по всему журналу");
        System.out.println("9) Выход");
        System.out.print("Выберите опцию: ");
    }

    public static void drawCanvas(List<Integer> numbers, String name) {
        System.out.println(numbers);
        Canvas canvas = new Canvas();

        canvas.drawBorder("#");

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.get(i); j++) {
                canvas.setPixel((i + 1) * 2, canvas.getHeight() - j - 2, "*");
            }
        }

        canvas.printTextLine(5, 1, name);
        System.out.println(canvas);
    }

    public static void drawCanvasForMoney(Journal journal) {
        List<Long> numbers = journal.getTotalByDay();
        List<Long> copy = new ArrayList<>();
        copy.addAll(numbers);
        numbers.replaceAll(aLong -> aLong / 1000);

        System.out.println(copy);
        Canvas canvas = new Canvas();

        canvas.drawBorder("#");

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.get(i); j++) {
                canvas.setPixel((i + 1) * 2, canvas.getHeight() - j - 2, "*");
            }
        }

        canvas.printTextLine(5, 1, " Ежедневный заработок за каждый день ");
        System.out.println(canvas);
    }

    public static void initMenu(Journal journal, Park park, List<Car> cars) {
        Scanner sc = new Scanner(System.in);
        boolean cycle = true;
        while (cycle) {
            showMenu();
            String choice = sc.nextLine().trim();
            cycle = switchMenu(choice, journal, park, cars);
        }
        sc.close();
    }
}
