/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxglgames;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.scene3d.Torus;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.animationBuilder;
import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxglgames.CelestialBody.SATURN;
import static com.almasb.fxglgames.CelestialBody.SUN;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class SolarSystemFactory implements EntityFactory {

    @Spawns("body")
    public Entity newBody(SpawnData data) {
        // from km to 3D units
        var sizeScale = 0.00005;
        var distanceScale = 0.00000016;

        CelestialBody bodyData = data.get("data");

        // distance between sun and this body, then offset by radius of each body
        var x = (bodyData.getDistanceFromSun() + SUN.getRadiusScaled(1.0) + bodyData.getRadiusScaled(1.0)) * distanceScale;

        var r = bodyData.getRadiusScaled(sizeScale);

        var mat = new PhongMaterial();
        mat.setDiffuseMap(bodyData.getImage());

        var view = new Sphere(r);
        view.setMaterial(mat);

        var e = entityBuilder(data)
                .at(x, 0, 0)
                .view(view)
                .with(new CelestialBodyComponent(bodyData))
                .build();

        if (bodyData == SATURN) {
            var torus = new Torus(r * 1.5, r * 0.3 / 3.0);

            e.getViewComponent().addChild(torus);

            animationBuilder()
                    .duration(Duration.seconds(0.1))
                    .rotate(torus)
                    .from(new Point3D(0, 0, 0))
                    .to(new Point3D(45, 0, 0))
                    .buildAndPlay();
        }

        if (bodyData == SUN) {
            e.getViewComponent().addChild(new AmbientLight(Color.rgb(233, 233, 233, 0.2)));
        }

        return e;
    }
}
