package com.github.coda1552.client.renderer;

import com.github.coda1552.common.entities.RayGunLaserEntity;
import com.github.coda1552.client.model.RayGunLaserModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class RayGunLaserRenderer extends GeoProjectilesRenderer<RayGunLaserEntity> {

    public RayGunLaserRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RayGunLaserModel());
    }

}

