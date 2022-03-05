package com.github.coda1552.client.renderer.layer;

import com.github.coda1552.common.entities.MothmanEntity;
import com.github.coda1552.DoYouBelieve;
import com.hugomage.doyoubelieve.client.model.MothmanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class MothmanEyeLayer<T extends MothmanEntity, M extends MothmanModel<T>> extends AbstractEyesLayer<T, M>{
    protected static final RenderType MOTHMAN_EYES = RenderType.eyes(new ResourceLocation(DoYouBelieve.MOD_ID, "textures/entity/mothman_eyes.png"));

    public MothmanEyeLayer(IEntityRenderer<T, M> p_i50921_1_) {
        super(p_i50921_1_);
    }

    public RenderType renderType() {
        return MOTHMAN_EYES;
    }
}
