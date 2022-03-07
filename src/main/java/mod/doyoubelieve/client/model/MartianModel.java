package mod.doyoubelieve.client.model;

import mod.doyoubelieve.common.entities.MartianEntity;
import mod.doyoubelieve.DoYouBelieve;
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