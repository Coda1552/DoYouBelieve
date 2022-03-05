package com.github.coda1552.client.model;

import com.github.coda1552.common.entities.BigfootEntity;
import com.github.coda1552.DoYouBelieve;
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