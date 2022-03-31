package mod.doyoubelieve.client;

import mod.doyoubelieve.DoYouBelieve;
import mod.doyoubelieve.client.renderer.BigfootRenderer;
import mod.doyoubelieve.client.renderer.MartianRenderer;
import mod.doyoubelieve.client.renderer.MothmanRenderer;
import mod.doyoubelieve.client.renderer.RayGunLaserRenderer;
import mod.doyoubelieve.registry.DYBEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DoYouBelieve.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void init(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DYBEntities.MOTHMAN.get(), MothmanRenderer::new);
        event.registerEntityRenderer(DYBEntities.BIGFOOT.get(), BigfootRenderer::new);
        event.registerEntityRenderer(DYBEntities.MARTIAN.get(), MartianRenderer::new);
        event.registerEntityRenderer(DYBEntities.RAYGUN_LASER.get(), RayGunLaserRenderer::new);
    }

}
