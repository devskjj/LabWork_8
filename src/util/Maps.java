package util;

import models.Receipt;

import java.time.LocalDate;
import java.util.*;

public class Maps {
    private final Map<LocalDate, Long> moneyByDate;

    public Maps() {
        this.moneyByDate = new HashMap<>();
    }

    public void add(Receipt receipt) {
        LocalDate date = receipt.getReceiptDate();
        long cost = receipt.getTotalCost();
        moneyByDate.put(date, moneyByDate.getOrDefault(date, 0L) + cost);
    }

    public Long getTotalByDate(LocalDate date) {
        return moneyByDate.get(date);
    }

    public List<Long> printStatistic() {
        if (moneyByDate.isEmpty()) {
            System.out.println("Нет данных для статистики.");
            return null;
        }

        List<Map.Entry<LocalDate, Long>> naturalOrder = new ArrayList<>(moneyByDate.entrySet());
        naturalOrder.sort(Comparator.comparing(Map.Entry::getKey));

        List<Long> forCanvas = new ArrayList<>();
        for (Map.Entry<LocalDate, Long> entry : naturalOrder) {
            forCanvas.add(entry.getValue());
        }

        Collection<Long> data = moneyByDate.values();
        List<Long> sorted = new ArrayList<>(data);
        Collections.sort(sorted);

        long min = sorted.get(0);
        long max = sorted.get(sorted.size() - 1);

        long sum = 0;
        for (Long val : sorted) {
            sum += val;
        }

        double average = (double) sum / sorted.size();
        System.out.println("=========================================");
        System.out.println("Статистика заработка за период симуляции:");
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
        System.out.println("Среднее: " + average);
        System.out.println("=========================================");
        return forCanvas;
    }

    public void sortTopTenCars(Map<String, Long> map) {
        List<Map.Entry<String, Long>> maps = new ArrayList<>(map.entrySet());
        maps.sort((m1, m2) -> Long.compare(m2.getValue(), m1.getValue()));

        System.out.println("=========================================");
        System.out.println("Топ 10 машин по времени стоянки:");
        for (int i = 0; i < Math.min(10, maps.size()); i++) {
            Map.Entry<String, Long> row = maps.get(i);
            System.out.printf("%d. Машина %s — %d минут%n", i + 1, row.getKey(), row.getValue());
        }
        System.out.println("=========================================");
    }
}
