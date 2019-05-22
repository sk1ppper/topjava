package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
@Service
public class MealServiceImpl implements MealService {
    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(Meal user) {
        return null;
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        repository.delete(userId,id);
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        return repository.get(userId,id);
    }


    @Override
    public void update(Meal user) {

    }
    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void save(int userId, Meal meal) {
        repository.save(userId,meal);
    }

    @Override
    public Collection<MealWithExceed> sortByDateTime(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate, int userId) {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(userId),MealsUtil.DEFAULT_CALORIES_PER_DAY,startTime,endTime,startDate,endDate);
    }


}