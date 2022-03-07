package mod.doyoubelieve.common.entities;

import mod.doyoubelieve.common.entities.ai.MartianShootGoal;
import mod.doyoubelieve.registry.DYBItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Hand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MartianEntity extends Monster implements IAnimatable {
    private static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(MartianEntity.class, EntityDataSerializers.INT);
    private final AnimationFactory factory = new AnimationFactory(this);

    public MartianEntity(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MartianShootGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, 0);
    }

    public void setAttackState(int state){
        this.entityData.set(ATTACK_STATE, state);
    }

    public int getAttackState(){
        return this.entityData.get(ATTACK_STATE);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getAttackState() == 1){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.Martian.shoot", false));
            return PlayState.CONTINUE;
        }
        if(event.isMoving()){
            if(this.getItemInHand(InteractionHand.MAIN_HAND) != null){
                event.getController().setAnimation(new AnimationBuilder()
                        .addAnimation("animation.Martian.walk_item", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.Martian.walk", true));
            return PlayState.CONTINUE;
        }
        else {
            if(this.getItemInHand(InteractionHand.MAIN_HAND) != null){
                event.getController().setAnimation(new AnimationBuilder()
                        .addAnimation("animation.Martian.idle_item", true));
                return PlayState.CONTINUE;
            }
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.Martian.idle", true));
            return PlayState.CONTINUE;
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.ATTACK_DAMAGE, 7D)
                .add(Attributes.ATTACK_SPEED, 6D);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 8, this::predicate));
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(DYBItems.RAYGUN.get(), 1));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
