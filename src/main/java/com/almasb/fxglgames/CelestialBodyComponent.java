/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxglgames;

import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point3D;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.animationBuilder;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class CelestialBodyComponent extends Component {

    private CelestialBody data;
    private Sphere view;

    public CelestialBodyComponent(CelestialBody data) {
        this.data = data;
    }

    @Override
    public void onAdded() {
        view = (Sphere) entity.getViewComponent().getChildren().get(0);

        animationBuilder()
                .duration(Duration.seconds(data.getRotationSpeed() * 3))
                .repeatInfinitely()
                .rotate(view)
                .from(new Point3D(0, 0, 0))
                .to(new Point3D(0, 360, 0))
                .buildAndPlay();

        animationBuilder()
                .duration(Duration.seconds(data.getOrbitalPeriod() * 0.1))
                .repeatInfinitely()
                .rotate(entity)
                .origin(new Point3D(-entity.getX(), 0, 0))
                .from(new Point3D(0, 0, 0))
                .to(new Point3D(0, 360, 0))
                .buildAndPlay();
    }
}
