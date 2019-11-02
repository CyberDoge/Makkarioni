package com.example.Makkaroni.models;

public enum Grade {
    Admin(1, "Текущий Пастриарх"),
    OldButGold(2, "Бывший Пастриарх"),
    Theorist(3, "Теоретик"),
    FunnyDude(4, "Магистр Ордена Абсурда при РПЦ"),
    PressSecretary(5, "Пресс-секретарь"),
    Lead(6, "Глава Епархии РПЦ"),
    Dude(7, "Прихожанин"),
    NotDude(8, "Атеист/агностик"),
    Normi(9, "Представитель другой конфесcии");
    public final int id;
    public final String label;

    Grade(int id, String label) {
        this.id = id;
        this.label = label;
    }
}
