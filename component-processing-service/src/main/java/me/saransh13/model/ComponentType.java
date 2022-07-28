package me.saransh13.model;

public enum ComponentType {
    INTEGRAL_ITEM("integral-item"),
    ACCESSORY("accessory");
    private final String name;

    ComponentType(String componentType) {
        this.name = componentType;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
