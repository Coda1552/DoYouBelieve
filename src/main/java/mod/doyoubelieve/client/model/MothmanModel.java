package mod.doyoubelieve.client.model;

import mod.doyoubelieve.DoYouBelieve;
import mod.doyoubelieve.common.entities.MothmanEntity;
import mod.doyoubelieve.common.entities.RayGunLaserEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MothmanModel extends AnimatedGeoModel<MothmanEntity> {

    @Override
    public ResourceLocation getModelLocation(MothmanEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "geo/mothman.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(MothmanEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "textures/entity/mothman.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(MothmanEntity animatable) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "animations/mothman.animations.json");
    }
}