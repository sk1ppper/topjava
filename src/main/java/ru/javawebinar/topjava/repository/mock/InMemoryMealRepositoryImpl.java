package ru.javawebinar.topjava.repository.mock;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    //
    {
        //MealsUtil.MEALS.forEach(this::save);
        MealsUtil.MEALS.forEach(meal -> save(meal, 2));
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 10, 20, 0), "Ужин", 510), 1);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 10, 14, 0), "Obed", 1510), 1);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 10, 6, 0), "Zavtrak", 2510), 1);
    }


    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        Map<Integer, Meal> map;
        if (repository.containsKey(userId)) {
            map = repository.get(userId);
            map.put(meal.getId(), meal);
            repository.put(userId, map);
        } else {
            map = new ConcurrentHashMap<>();
            map.put(meal.getId(), meal);
            repository.put(userId, map);
        }
        return meal;

//        if (meal.isNew()) {
//            meal.setId(counter.incrementAndGet());
//            repository.put(meal.getId(), meal);
//            return meal;
//        }
//        // treat case: update, but absent in storage
//        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllFiltered(userId, meal -> true);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAllFiltered(userId, meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime));
    }

    private List<Meal> getAllFiltered(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = repository.get(userId);
        return CollectionUtils.isEmpty(meals) ? Collections.emptyList() :
                meals.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}