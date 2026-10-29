package com.main.first_project_g151.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fighter {

    private Integer id;
    private String fullName;
    private int age;
    private String style;
    private int countFights;
    private Boolean isActive;

    private static Integer idToBase = 5;

    @Getter
    private static List<Fighter> fighters = new ArrayList<>();

    static {
        fighters.add(new Fighter(1, "Serik Serikov", 20, "box", 20, true));
        fighters.add(new Fighter(2, "Berik Berikov", 23, "mma", 5, true));
        fighters.add(new Fighter(3, "Merik Merikov", 29, "mma", 17, true));
        fighters.add(new Fighter(4, "Jerik Jerikov", 32, "judo", 30, false));
    }


    public static Fighter getFighterById(int id) {

        return fighters.stream().filter(f -> f.id == id).findFirst().get();
    }

    public static void updateFighter(Fighter fighter) {

        for (Fighter f : fighters) {
            if (f.id == fighter.id) {
                f.setAge(fighter.age);
                f.setIsActive(fighter.isActive);
                f.setStyle(fighter.style);
                f.setCountFights(fighter.countFights);
                f.setFullName(fighter.fullName);
            }
        }

    }

    public static void deleteFighter(int id) {

        fighters.removeIf(f -> f.id == id);

    }

    public static void add(Fighter fighter) {

        fighter.setId(idToBase++);

        fighters.add(fighter);
    }
}
