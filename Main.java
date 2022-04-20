package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long numberOfMinors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("В Лондоне всего несовершеннолетних " + numberOfMinors + " человек");


        System.out.println("Список фамилий призывников (мужчин от 18 и до 27 лет): ");
        List<String> populationCensus = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(person -> person.getFamily().toString())
                .collect(Collectors.toList());
        System.out.println(populationCensus);

        System.out.println("Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием");
        System.out.println("(от 18 до 60 лет для женщин и до 65 лет для мужчин)");
        List<String> workable = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 65)
                .filter(person -> {
                    return person.getSex() == Sex.MAN ? true : person.getAge() <= 60;
                })
                .filter(person -> person.getEducation() == Education.HIGHER)
                .map(person -> person.getFamily().toString())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(workable);
    }
}
