package ru.yandex.practicum.gym;

import java.util.Objects;

public class CounterOfTrainings {

    private Coach coach;
    private int numberOfSessions;

    public CounterOfTrainings(int numberOfSessions, Coach coach) {
        this.numberOfSessions = numberOfSessions;
        this.coach = coach;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CounterOfTrainings that = (CounterOfTrainings) object;
        return numberOfSessions == that.numberOfSessions && Objects.equals(coach, that.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach, numberOfSessions);
    }

    public Coach getCoach() {
        return coach;
    }

    public int getNumberOfSessions() {
        return numberOfSessions;
    }
}
