package mod.doyoubelieve.client.renderer;

import mod.doyoubelieve.common.entities.RayGunLaserEntity;
import mod.doyoubelieve.client.model.RayGunLaserModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class RayGunLaserRenderer extends GeoProjectilesRenderer<RayGunLaserEntity> {

    public RayGunLaserRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RayGunLaserModel());
    }

}

