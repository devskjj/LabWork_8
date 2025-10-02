package util;

import models.Receipt;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
}
