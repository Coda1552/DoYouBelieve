package mod.doyoubelieve.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import mod.doyoubelieve.client.model.MartianModel;
import mod.doyoubelieve.common.entities.MartianEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MartianRenderer extends GeoEntityRenderer<MartianEntity> {
    protected MartianEntity entity;

    public MartianRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MartianModel());
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("item")) {
            stack.pushPose();
            stack.translate(0.26D, 0.45D, 0.1D);
            stack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            stack.scale(0.7f, 0.7f, 0.7f);
            Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItemBySlot(EquipmentSlot.MAINHAND), ItemTransforms.TransformType.GROUND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}

