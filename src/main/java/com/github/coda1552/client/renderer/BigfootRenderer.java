package com.github.coda1552.client.renderer;

import com.github.coda1552.common.entities.BigfootEntity;
import com.github.coda1552.client.model.BigfootModel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BigfootRenderer extends GeoEntityRenderer<BigfootEntity> {
    protected BigfootEntity entity;

    public BigfootRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BigfootModel());
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("Item")) {
            stack.pushPose();
            stack.translate(0.1F, 0.7F, -0.2F);
            stack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            Minecraft.getInstance().getItemRenderer().renderStatic(entity.getItemBySlot(EquipmentSlot.MAINHAND), ItemTransforms.TransformType.GROUND, packedLightIn, packedOverlayIn, stack, this.rtb, 1);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}

