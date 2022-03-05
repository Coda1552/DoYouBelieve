package com.github.coda1552.common.entities;

import com.github.coda1552.registry.DYBSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class BigfootEntity extends Animal implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> EAT_COUNTER = SynchedEntityData.defineId(BigfootEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ROARING = SynchedEntityData.defineId(BigfootEntity.class, EntityDataSerializers.BOOLEAN);
    private static final Predicate<ItemEntity> TEMPTATION_ITEMS = (p_213575_0_) -> {
        Item item = p_213575_0_.getItem().getItem();
        return (item == Blocks.SWEET_BERRY_BUSH.asItem()) && p_213575_0_.isAlive() && !p_213575_0_.hasPickUpDelay();
    };
    private int attackAnimationRemainingTicks;

    public BigfootEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
        this.setCanPickUpLoot(true);
        this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, 0.0F);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getRoaring()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.BigFoot.roar", false));
            return PlayState.CONTINUE;
        }
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.BigFoot.walk", true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.BigFoot.idle", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new BigfootEntity.FindItemsGoal());
        this.goalSelector.addGoal(6, new BigfootEntity.EatBerriesGoal(1.2F, 12, 2));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.ATTACK_DAMAGE, 10D)
                .add(Attributes.ATTACK_SPEED, 6D);
    }

    public boolean doHurtTarget(Entity target) {
        if (!(target instanceof LivingEntity)) {
            return false;
        } else {
            this.attackAnimationRemainingTicks = 10;
            this.level.broadcastEntityEvent(this, (byte)4);
            this.playSound(DYBSounds.BIGFOOT_ATTACK.get(), 1.0F, this.getVoicePitch());
            float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
            boolean flag = target.hurt(DamageSource.mobAttack(this), f1);
            if (flag) {
                target.setDeltaMovement(target.getDeltaMovement().add(0.0D, (double)0.4F, 0.0D));
                this.doEnchantDamageEffects(this, target);
            }
            return  flag;
        }
    }

    public void aiStep() {
        if (this.attackAnimationRemainingTicks > 0) {
            --this.attackAnimationRemainingTicks;
        }

        super.aiStep();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EAT_COUNTER, 0);
        this.entityData.define(ROARING, false);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 4) {
            this.attackAnimationRemainingTicks = 10;
            this.playSound(DYBSounds.BIGFOOT_ATTACK.get(), 0.5F, this.getVoicePitch());
        } else {
            super.handleEntityEvent(p_70103_1_);
        }

    }

    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 6.5F);
        }

        return super.getExperienceReward(p_70693_1_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound () {
        return DYBSounds.BIGFOOT_AMBIENT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return DYBSounds.BIGFOOT_DEATH.get();
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return DYBSounds.BIGFOOT_HURT.get();
    }

    public boolean getRoaring(){
        return this.entityData.get(ROARING);
    }

    public void setRoaring(boolean roaring){
        this.entityData.set(ROARING, true);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn ) {
        if (!blockIn.getMaterial().isLiquid()) {
            this.playSound(SoundEvents.IRON_GOLEM_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume());
        }
    }

    private boolean canMove() {
        return !this.isSleeping();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BigfootEntity>(this, "controller", 8, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    class FindItemsGoal extends Goal {
        public FindItemsGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (!BigfootEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                return false;
            } else if (BigfootEntity.this.getTarget() == null && BigfootEntity.this.getLastHurtByMob() == null) {
                if (!BigfootEntity.this.canMove()) {
                    return false;
                } else if (BigfootEntity.this.getRandom().nextInt(10) != 0) {
                    return false;
                } else {
                    List<ItemEntity> list = BigfootEntity.this.level.getEntitiesOfClass(ItemEntity.class, BigfootEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), BigfootEntity.TEMPTATION_ITEMS);
                    return !list.isEmpty() && BigfootEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
                }
            } else {
                return false;
            }
        }

        public void tick() {
            List<ItemEntity> list = BigfootEntity.this.level.getEntitiesOfClass(ItemEntity.class, BigfootEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), BigfootEntity.TEMPTATION_ITEMS);
            ItemStack itemstack = BigfootEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                BigfootEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }

        public void start() {
            List<ItemEntity> list = BigfootEntity.this.level.getEntitiesOfClass(ItemEntity.class, BigfootEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), BigfootEntity.TEMPTATION_ITEMS);
            if (!list.isEmpty()) {
                BigfootEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }
    }

    public class EatBerriesGoal extends MoveToBlockGoal {
        protected int ticksWaited;

        public EatBerriesGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
            super(BigfootEntity.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
        }

        public double acceptedDistance() {
            return 2.0D;
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 100 == 0;
        }

        protected boolean isValidTarget(LevelReader p_179488_1_, BlockPos p_179488_2_) {
            BlockState blockstate = p_179488_1_.getBlockState(p_179488_2_);
            return blockstate.is(Blocks.SWEET_BERRY_BUSH) && blockstate.getValue(SweetBerryBushBlock.AGE) >= 2;
        }

        public void tick() {
            if (this.isReachedTarget()) {
                if (this.ticksWaited >= 40) {
                    this.onReachedTarget();
                } else {
                    ++this.ticksWaited;
                }
            } else if (!this.isReachedTarget() && BigfootEntity.this.random.nextFloat() < 0.05F) {
                BigfootEntity.this.playSound(SoundEvents.FOX_SNIFF, 1.0F, 1.0F);
            }

            super.tick();
        }

        protected void onReachedTarget() {
            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(BigfootEntity.this.level, BigfootEntity.this)) {
                BlockState blockstate = BigfootEntity.this.level.getBlockState(this.blockPos);
                if (blockstate.is(Blocks.SWEET_BERRY_BUSH)) {
                    int i = blockstate.getValue(SweetBerryBushBlock.AGE);
                    blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1));
                    int j = 1 + BigfootEntity.this.level.random.nextInt(2) + (i == 3 ? 1 : 0);
                    ItemStack itemstack = BigfootEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
                    if (itemstack.isEmpty()) {
                        BigfootEntity.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
                        --j;
                    }

                    if (j > 0) {
                        Block.popResource(BigfootEntity.this.level, this.blockPos, new ItemStack(Items.SWEET_BERRIES, j));
                    }

                    BigfootEntity.this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, 1.0F);
                    BigfootEntity.this.level.setBlock(this.blockPos, blockstate.setValue(SweetBerryBushBlock.AGE, 1), 2);
                }
            }
        }

        public boolean canUse() {
            return !BigfootEntity.this.isSleeping() && super.canUse();
        }

        public void start() {
            this.ticksWaited = 0;
            super.start();
        }
    }
}
