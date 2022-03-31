package mod.doyoubelieve.client.renderer;

import mod.doyoubelieve.client.model.MothmanModel;
import mod.doyoubelieve.client.renderer.layer.MothmanEyeLayer;
import mod.doyoubelieve.common.entities.MothmanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MothmanRenderer extends GeoEntityRenderer<MothmanEntity> {
    protected MothmanEntity entity;

    public MothmanRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MothmanModel());
        addLayer(new MothmanEyeLayer<>(this));
        shadowRadius = 0.8F;
    }
}

