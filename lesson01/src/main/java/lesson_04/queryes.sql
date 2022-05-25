# Сделать запросы, считающие и выводящие в понятном виде:
# ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо колонки
# «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

select tb1.name     Film1,
       tb1.datetime DateStart1,
       tb1.duration Duration1,
       tb2.name     Film2,
       tb2.datetime DateStart2,
       tb2.duration Duration2
From (select p.datetime, f.name, f.duration, adddate(p.datetime, INTERVAL (f.duration + 30) MINUTE) date_end
      from poster p
               inner JOIN film f
                          on p.film_id = f.id) as tb1
         inner JOIN (select p.datetime, f.name, f.duration
                     from poster p
                              inner JOIN film f
                                         on p.film_id = f.id) as tb2
                    on tb1.date_end > tb2.datetime
                        and tb1.datetime < tb2.datetime
ORDER BY tb1.datetime;



# перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала»,
# «длительность», «время начала второго фильма», «длительность перерыва»;

Select tb1.name                                                                                 Film1,
       tb1.datetime                                                                             DateStartFilm1,
       tb1.duration                                                                             DurationFilm1,
       min(tb2.datetime)                                                                        DateStartFilm2,
       TIMESTAMPDIFF(MINUTE, adddate(tb1.datetime, INTERVAL tb1.duration MINUTE), tb2.datetime) BreakDuration
From (Select p.id, p.datetime, f.name, f.duration, adddate(p.datetime, INTERVAL f.duration MINUTE) Film_end
      from poster p
               inner JOIN film f
                          on p.film_id = f.id) as tb1
         inner JOIN (Select p.id, p.datetime, f.name, f.duration
                     from poster p
                              inner JOIN film f
                                         on p.film_id = f.id) as tb2
                    on tb1.datetime < tb2.datetime
group by tb1.id
HAVING BreakDuration >= 30
ORDER BY tb1.datetime, tb2.datetime;

# список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей
# суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

Select tb1.Film,
       sum(tb1.Visitors) All_Visitors,
       avg(tb1.Visitors) AVG_Visitors,
       sum(tb1.Summ)     Summ
From (SELECT f.name                  Film,
             sum(s.amount)           Visitors,
             sum(p.price * s.amount) Summ
      From film f
               inner join poster p
               inner join session s
                          on p.id = s.poster_id on f.id = p.film_id
      group by f.id, p.id) as tb1
group by tb1.Film
UNION
SELECT "Итого",
       sum(s.amount),
       null,
       sum(p.price * s.amount)
From film f
         inner join poster p
         inner join session s
                    on p.id = s.poster_id on f.id = p.film_id
order by Summ
Desc;

# число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21,
# с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

SELECT
    Case
        when TIME(p.datetime) >= '09:00:00' and TIME(p.datetime) < '15:00:00'
            then "С 9 до 15"
        when TIME(p.datetime) >= '15:00:00' and TIME(p.datetime) < '18:00:00'
            then "С 15 до 18"
        when TIME(p.datetime) >= '18:00:00' and TIME(p.datetime) < '21:00:00'
            then "С 18 до 21"
        when TIME(p.datetime) >= '21:00:00' and TIME(p.datetime) <= '23:59:59'
            then "С 21 до 00:00"
        else "VIP"
        end Grouptime,
    sum(s.amount) Visitors,
    sum(p.price * s.amount) Summ
From film f
         inner join poster p
         inner join session s
                    on p.id = s.poster_id
    on f.id = p.film_id
group by Grouptime
order by p.datetime;
