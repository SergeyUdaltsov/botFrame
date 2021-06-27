package com.teleBot;

import com.teleBot.utils.DateUtils;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Serhii_Udaltsov on 4/7/2021
 */
public class TestClass {

    public static void main(String[] args) {
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDay = firstDay.plusMonths(1);
        long from = firstDay.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        long to = firstDay.plusMonths(1).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        System.out.println(firstDay);
        System.out.println(lastDay);
    }


}
