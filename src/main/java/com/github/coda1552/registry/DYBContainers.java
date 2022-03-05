package com.github.coda1552.registry;

import com.github.coda1552.common.containers.BulletinBoardContainer;
import com.github.coda1552.DoYouBelieve;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DYBContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, DoYouBelieve.MOD_ID);

    public static final RegistryObject<ContainerType<BulletinBoardContainer>> BULLETIN_BOARD = CONTAINERS.register("blender", () -> IForgeContainerType.create(BulletinBoardContainer::new));
}
