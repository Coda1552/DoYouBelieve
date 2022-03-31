package mod.doyoubelieve.client.model;

import mod.doyoubelieve.common.entities.RayGunLaserEntity;
import mod.doyoubelieve.DoYouBelieve;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RayGunLaserModel extends AnimatedGeoModel<RayGunLaserEntity> {

    @Override
    public ResourceLocation getModelLocation(RayGunLaserEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "geo/raygun_laser.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(RayGunLaserEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "textures/entity/raygun_laser.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(RayGunLaserEntity animatable) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "animations/raygun_laser.animations.json");
    }
}