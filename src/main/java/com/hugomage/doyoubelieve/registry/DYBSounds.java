package com.hugomage.doyoubelieve.registry;

import com.hugomage.doyoubelieve.DoYouBelieve;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DYBSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DoYouBelieve.MOD_ID);

    public static final RegistryObject<SoundEvent> BIGFOOT_AMBIENT = create("entity.bigfoot.ambient");
    public static final RegistryObject<SoundEvent> BIGFOOT_HURT = create("entity.bigfoot.hurt");
    public static final RegistryObject<SoundEvent> BIGFOOT_DEATH = create("entity.bigfoot.death");
    public static final RegistryObject<SoundEvent> BIGFOOT_ATTACK = create("entity.bigfoot.attack");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(DoYouBelieve.MOD_ID, name)));
    }
}
