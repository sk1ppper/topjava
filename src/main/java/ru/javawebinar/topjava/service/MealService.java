package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealService {
    Meal create(Meal user);

    void delete(int user, int id) throws NotFoundException;

    Meal get(int user,int id) throws NotFoundException;

    void update(Meal user);

    Collection<Meal> getAll(int userId);

    void save(int user, Meal meal);

    Collection<MealWithExceed> sortByDateTime(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate, int userId);
}