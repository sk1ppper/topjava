package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer,Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        //MealsUtil.MEALS.forEach(this::save);
        MealsUtil.MEALS.forEach(meal ->save(0,meal));
        MealsUtil.MEALS.forEach(meal ->save(1,meal));
        save(1,new Meal(LocalDateTime.of(2015, Month.MAY, 10, 20, 0), "Ужин", 510));
        save(1,new Meal(LocalDateTime.of(2015, Month.MAY, 10, 14, 0), "Obed", 1510));
        save(1,new Meal(LocalDateTime.of(2015, Month.MAY, 10, 6, 0), "Zavtrak", 2510));
    }

    @Override
    public Meal save(Integer userId,Meal meal) {
        if (meal.isNew()){
            meal.setId(counter.incrementAndGet());
        }
        Map<Integer,Meal> map;
        if (repository.containsKey(userId)){
            map = repository.get(userId);
            map.put(meal.getId(),meal);
            repository.put(userId,map);
        } else {
            map = new ConcurrentHashMap<>();
            map.put(meal.getId(),meal);
            repository.put(userId,map);
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
    public void delete(Integer userId,int id) {
        repository.get(userId).remove(id);
    }

    @Override
    public Meal get(Integer userId,int id) {
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return repository.get(userId).values();
    }
}

