package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,11,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,11,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        for(UserMealWithExceed pair:list) System.out.println(pair);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> list = new ArrayList<>();
        Map<LocalDate,Integer> map = new HashMap<>();
        for(UserMeal pair:mealList){
            if (!(map.containsKey(pair.getDateTime().toLocalDate()))){
                map.put(pair.getDateTime().toLocalDate(),pair.getCalories());
            } else {
                map.put(pair.getDateTime().toLocalDate(),map.get(pair.getDateTime().toLocalDate())+pair.getCalories());
            }
        }
        for(Map.Entry<LocalDate,Integer> pair:map.entrySet()){
            System.out.println(pair.getKey()+"--"+pair.getValue());
        }
        for(UserMeal pair:mealList){
            if((pair.getDateTime().toLocalTime().isAfter(startTime))&&(pair.getDateTime().toLocalTime().isBefore(endTime))) {
                int calories = map.get(pair.getDateTime().toLocalDate());
                list.add(new UserMealWithExceed(pair.getDateTime(),pair.getDescription(),pair.getCalories(), calories < 2005));
            }
        }
        return list;
    }
}
