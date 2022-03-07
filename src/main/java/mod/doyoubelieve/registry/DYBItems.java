package mod.doyoubelieve.registry;

import mod.doyoubelieve.DoYouBelieve;
import mod.doyoubelieve.client.renderer.RayGunRenderer;
import mod.doyoubelieve.common.item.RayGunItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DYBItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DoYouBelieve.MOD_ID);

    // Spawn Egg
    public static final RegistryObject<Item> BIGFOOT_SPAWN_EGG = ITEMS.register("bigfoot_spawn_egg",() -> new ForgeSpawnEggItem(DYBEntities.BIGFOOT, 0x702817, 0x5E4944 , new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> MOTHMAN_SPAWN_EGG = ITEMS.register("mothman_spawn_egg",() -> new ForgeSpawnEggItem(DYBEntities.MOTHMAN, 0x802817, 0x5E4944 , new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> MARTIAN_SPAWN_EGG = ITEMS.register("martian_spawn_egg",() -> new ForgeSpawnEggItem(DYBEntities.MARTIAN, 0x802817, 0x5E2944 , new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    // Items
    public static final RegistryObject<RayGunItem> RAYGUN = ITEMS.register("raygun", () -> new RayGunItem(new Item.Properties().setISTER(() -> RayGunRenderer::new).setNoRepair()));

}