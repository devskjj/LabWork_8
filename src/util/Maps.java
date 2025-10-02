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

    public void printStatistic() {
        if (moneyByDate.isEmpty()) {
            System.out.println("Нет данных для статистики.");
            return;
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
    }
}
