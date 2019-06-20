package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealService {

       Meal get(int id, int userId) throws NotFoundException;

       void delete(int id, int userId) throws NotFoundException;
        List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

        List<Meal> getAll(int userId);

        Meal update(Meal meal, int userId) throws NotFoundException;

        Meal create(Meal meal, int userId);

        default List<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
              return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
           }


}