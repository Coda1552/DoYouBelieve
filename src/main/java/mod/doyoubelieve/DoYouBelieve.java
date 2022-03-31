package mod.doyoubelieve;

import mod.doyoubelieve.common.entities.BigfootEntity;
import mod.doyoubelieve.common.entities.MartianEntity;
import mod.doyoubelieve.common.entities.MothmanEntity;
import mod.doyoubelieve.registry.DYBBlocks;
import mod.doyoubelieve.registry.DYBEntities;
import mod.doyoubelieve.registry.DYBItems;
import mod.doyoubelieve.registry.DYBSounds;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.VillageFeature;
import net.minecraft.world.level.levelgen.feature.WoodlandMansionFeature;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(DoYouBelieve.MOD_ID)
public class DoYouBelieve {
    public static final String MOD_ID = "doyoubelieve";
    private static final Logger LOGGER = LogManager.getLogger();

    public DoYouBelieve() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        DYBItems.ITEMS.register(bus);
        DYBBlocks.BLOCKS.register(bus);
        DYBEntities.ENTITIES.register(bus);
        DYBSounds.SOUNDS.register(bus);

        bus.addListener(this::registerCommon);
        bus.addListener(this::registerSpawns);
        bus.addListener(this::registerStructureSpawns);
        bus.addListener(this::createEntityAttributes);

        GeckoLib.initialize();
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        SpawnPlacements.register(DYBEntities.MOTHMAN.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, (entity, level, type, pos, rand) -> MothmanEntity.checkMothmanSpawnRules(level, pos));
    }

    private void registerSpawns(BiomeLoadingEvent event) {
        String name = event.getName().getPath();
        Biome.BiomeCategory category = event.getCategory();

        // Mothman spawns
        if (category.equals(Biome.BiomeCategory.TAIGA) || name.equals("dark_forest")) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(DYBEntities.MOTHMAN.get(), 3, 1, 1));
        }
    }

    private void registerStructureSpawns(StructureSpawnListGatherEvent event) {
        StructureFeature<?> feature = event.getStructure();

        if (feature instanceof VillageFeature || feature instanceof WoodlandMansionFeature) {
            event.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(DYBEntities.MOTHMAN.get(), 5, 1, 1));
        }
    }

    private void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(DYBEntities.BIGFOOT.get(), BigfootEntity.createAttributes().build());
        event.put(DYBEntities.MOTHMAN.get(), MothmanEntity.createAttributes().build());
        event.put(DYBEntities.MARTIAN.get(), MartianEntity.createAttributes().build());
    }

}
