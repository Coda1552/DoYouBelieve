package com.github.coda1552.client.model;

import com.github.coda1552.common.entities.MartianEntity;
import com.github.coda1552.DoYouBelieve;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class MartianModel extends AnimatedGeoModel<MartianEntity> {

    @Override
    public ResourceLocation getModelLocation(MartianEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "geo/martian.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MartianEntity object) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "textures/entity/martian.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MartianEntity animatable) {
        return new ResourceLocation(DoYouBelieve.MOD_ID, "animations/martian.animations.json");
    }
}