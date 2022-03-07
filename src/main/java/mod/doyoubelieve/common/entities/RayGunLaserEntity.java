package mod.doyoubelieve.common.entities;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class RayGunLaserEntity extends AbstractArrow implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public LivingEntity shooter;
    public LivingEntity entity;

    public RayGunLaserEntity(EntityType<? extends RayGunLaserEntity> type, LivingEntity shooter, Level worldIn) {
        super(type, shooter, worldIn);
        this.shooter = shooter;
        this.setNoGravity(true);
        this.setNoPhysics(true);
        this.setBaseDamage(3.7D);
    }

    public RayGunLaserEntity(EntityType<RayGunLaserEntity> type, Level world) {
        super(type, world);
        this.setBaseDamage(3.7D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
            return PlayState.CONTINUE;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isInWater() || this.inGround) {
            this.discard();
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        int arrowTimer = 50;
        this.entity = entity;
        this.entity.hurt(DamageSource.arrow(this, this.shooter), this.entity.getArmorValue() /2.0f * 0.09f);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
