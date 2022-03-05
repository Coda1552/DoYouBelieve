package com.hugomage.doyoubelieve.registry;

import com.hugomage.doyoubelieve.DoYouBelieve;
import com.hugomage.doyoubelieve.common.entities.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DYBEntities {
    public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, DoYouBelieve.MOD_ID);

    public static final RegistryObject<EntityType<BigfootEntity>> BIGFOOT = create("bigfoot",
            EntityType.Builder.of(BigfootEntity::new, EntityClassification.CREATURE).sized(1.0f,3.3f));

    public static final RegistryObject<EntityType<FresnoEntity>> FRESNO = create("fresno_nightcrawler",
            EntityType.Builder.of(FresnoEntity::new, EntityClassification.MONSTER).sized(1.0f,1.5f));

    public static final RegistryObject<EntityType<MothmanEntity>> MOTHMAN = create("mothman",
            EntityType.Builder.of(MothmanEntity::new, EntityClassification.MONSTER).sized(1.2f,2.5f));

    public static final RegistryObject<EntityType<MartianEntity>> MARTIAN = create("martian",
            EntityType.Builder.of(MartianEntity::new, EntityClassification.CREATURE).sized(0.7f,1.7f));

    public static final RegistryObject<EntityType<RayGunLaserEntity>> RAYGUN_LASER = create("raygun_laser",
            EntityType.Builder.<RayGunLaserEntity>of(RayGunLaserEntity::new, EntityClassification.MISC).sized(0.25F,0.25F).clientTrackingRange(4).updateInterval(20).fireImmune());

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(DoYouBelieve.MOD_ID + "." + name));
    }
}
