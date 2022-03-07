package mod.doyoubelieve.client.model;

import mod.doyoubelieve.common.entities.BigfootEntity;
import mod.doyoubelieve.DoYouBelieve;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BigfootModel extends AnimatedGeoModel<BigfootEntity> {

    @Override
    public ResourceLocation getModelLocation(BigfootEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "geo/bigfoot.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BigfootEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "textures/entity/bigfoot.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BigfootEntity animatable) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "animations/bigfoot.animations.json");
    }
}