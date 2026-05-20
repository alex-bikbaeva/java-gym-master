package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        if (!timetable.containsKey(day)) {
            timetable.put(day, new TreeMap<>());
        }

        TreeMap<TimeOfDay, List<TrainingSession>> dayTimetable = timetable.get(day);

        if (!dayTimetable.containsKey(time)) {
            dayTimetable.put(time, new ArrayList<>());
        }

        List<TrainingSession> trainingSessionAtTime = dayTimetable.get(time);
        trainingSessionAtTime.add(trainingSession);

    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        if (timetable.containsKey(dayOfWeek)) {
            TreeMap<TimeOfDay, List<TrainingSession>> dayTimetable = timetable.get(dayOfWeek);

            List<TrainingSession> result = new ArrayList<>();
            for (TimeOfDay time : dayTimetable.navigableKeySet()) {
                result.addAll(dayTimetable.get(time));
            }

            return result;

        } else {
            return new ArrayList<>();
        }

    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        if (timetable.containsKey(dayOfWeek)) {
            TreeMap<TimeOfDay, List<TrainingSession>> dayTimetable = timetable.get(dayOfWeek);

            if (dayTimetable.containsKey(timeOfDay)) {
                List<TrainingSession> trainingSessions = dayTimetable.get(timeOfDay);
                return trainingSessions;
            } else {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        HashMap<Coach, Integer> counts = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> dayTimetable : timetable.values()) {
            for (List<TrainingSession> trainingSessions : dayTimetable.values()) {
                for (TrainingSession trainingSession : trainingSessions) {
                    Coach coach = trainingSession.getCoach();
                    counts.put(coach, counts.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Coach coach : counts.keySet()) {
            result.add(new CounterOfTrainings(counts.get(coach), coach));
        }

        result.sort((a, b) -> Integer.compare(b.getNumberOfSessions(), a.getNumberOfSessions()));
        return result;
    }
}
