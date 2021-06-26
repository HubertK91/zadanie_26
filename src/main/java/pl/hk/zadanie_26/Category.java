package pl.hk.zadanie_26;

public enum Category {
    FILTERS("filtry"), AIR_CONDITIONING("klimatyzacja"), OILS("oleje"), TIRES("opony");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
