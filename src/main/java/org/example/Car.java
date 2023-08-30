package org.example;

public record Car(String make, String model, Integer year, String color, String vin) {
    @Override
    public String toString() {
        return """
                 Make: '%s',
                 Model: '%s',
                 Year: %d,
                 Color: '%s'
                """.formatted(make, model, year, color);
    }
}
