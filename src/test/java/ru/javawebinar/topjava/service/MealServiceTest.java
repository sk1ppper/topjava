package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }
    @Autowired
    MealService mealService;


    @Test
    public void get() {
        mealService.get(100002,100000);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        mealService.get(100002,100003);
    }

    @Test
    public void delete() {
        mealService.delete(100002,100000);
    }
    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        mealService.delete(100003,100003);
    }


    @Test
    public void getBetweenDateTimes() {
        mealService.getBetweenDateTimes(LocalDateTime.of(1990,10,2,8,0),LocalDateTime.of(2015,12,30,22,00),100000);
    }

    @Test
    public void getAll() {
        mealService.getAll(100000);
    }
    @Test
    public void getAllUserNotFound() {
        mealService.getAll(100000);
    }
    @Test
    public void update() {
        mealService.update(new Meal(LocalDateTime.of(2015, 10, 31, 20, 0), "Ужин9876543", 510),100000);
    }

    @Test
    public void create() {
        mealService.create(new Meal(LocalDateTime.of(2015, 10, 31, 20, 0), "хавчик", 510),100001);
    }
}