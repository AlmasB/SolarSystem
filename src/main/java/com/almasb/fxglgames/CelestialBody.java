/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxglgames;

import javafx.scene.image.Image;

import static com.almasb.fxgl.dsl.FXGL.image;

/**
 * Majority of data from https://nssdc.gsfc.nasa.gov/planetary/factsheet/
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum CelestialBody {

    // scale down...
    SUN(1392680 * 0.2, 1.997, 0, 0, image("2k_sun.jpg")),

    MERCURY(4879, 0.003026, 57.9, 88, image("2k_mercury.jpg")),

    VENUS(12104, 0.00181, 108.2, 224.7, image("2k_venus_surface.jpg")),

    EARTH(12756, 0.4651, 149.6, 365.2, image("2k_earth_daymap.jpg")),

    MARS(6792, 0.24117, 228.0, 687.0, image("2k_mars.jpg")),

    JUPITER(142984, 12.6, 778.5, 4331, image("2k_jupiter.jpg")),

    SATURN(120536, 9.87, 1432.0, 10747, image("2k_saturn.jpg")),

    URANUS(51118, 2.59, 2867.0, 30589, image("2k_uranus.jpg")),

    NEPTUNE(49528, 2.68, 4515.0, 59800, image("2k_neptune.jpg")),

    PLUTO(2376, 0.01311, 5906.4, 90560, image("2k_neptune.jpg"));

    // in km
    private double diameter;

    // in km/s
    private double rotationSpeed;

    // in 10^6 km
    private double distanceFromSun;

    // in days
    private double orbitalPeriod;

    private Image image;

    CelestialBody(double diameter, double rotationSpeed, double distanceFromSun, double orbitalPeriod, Image image) {
        this.diameter = diameter;
        this.rotationSpeed = rotationSpeed;
        this.distanceFromSun = distanceFromSun;
        this.orbitalPeriod = orbitalPeriod;
        this.image = image;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getRadiusScaled(double scale) {
        return diameter * scale / 2.0;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public double getDistanceFromSun() {
        return distanceFromSun * 1_000_000;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Image getImage() {
        return image;
    }
}
