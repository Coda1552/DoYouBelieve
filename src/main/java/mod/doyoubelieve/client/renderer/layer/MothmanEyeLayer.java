package mod.doyoubelieve.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.doyoubelieve.DoYouBelieve;
import mod.doyoubelieve.common.entities.MothmanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@OnlyIn(Dist.CLIENT)
public class MothmanEyeLayer<T extends MothmanEntity> extends GeoLayerRenderer<T> {
    private static final RenderType EYES = RenderType.eyes(new ResourceLocation(DoYouBelieve.MOD_ID, "textures/entity/mothman_eyes.png"));
    private static final ResourceLocation MODEL = new ResourceLocation(DoYouBelieve.MOD_ID, "geo/mothman.geo.json");

    public MothmanEyeLayer(IGeoRenderer<T> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType cameo = EYES;
        matrixStackIn.pushPose();
        //Move or scale the model as you see fit
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        matrixStackIn.popPose();

    }
}
