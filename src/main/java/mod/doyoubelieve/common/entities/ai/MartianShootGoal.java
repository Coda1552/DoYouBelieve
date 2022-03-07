package mod.doyoubelieve.common.entities.ai;

import mod.doyoubelieve.common.entities.MartianEntity;
import mod.doyoubelieve.common.entities.RayGunLaserEntity;
import mod.doyoubelieve.registry.DYBEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class MartianShootGoal extends Goal {
    protected MartianEntity entity;
    private int timer;
    private final int COOLDOWN = 40;
    private int cooldownTimer;

    public MartianShootGoal(MartianEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return this.entity.getTarget() != null && this.entity.distanceToSqr(this.entity.getTarget()) < 10.0f;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.entity.getAttackState() == 0 || this.entity.getAttackState() == 1) {
            if (this.cooldownTimer < COOLDOWN) {
                this.cooldownTimer++;
            } else {
                if (this.timer < 20) {
                    this.timer++;
                    this.entity.getNavigation().stop();
                    this.entity.setAttackState(1);
                    if (this.timer == 13) {
                        this.tryHurtTarget(this.entity, this.entity.distanceTo(this.entity.getTarget()));
                    }
                } else {
                    this.timer = 0;
                    this.cooldownTimer = 0;
                    this.entity.setAttackState(0);
                }
            }
        }
        else {
            this.stop();
        }
    }

    protected void tryHurtTarget(MartianEntity entity, double distanceTo){
        BlockPos blockpos = this.entity.blockPosition();
        RayGunLaserEntity laser = DYBEntities.RAYGUN_LASER.get().create(this.entity.level);
        laser.moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        Vec3 vec = this.entity.getViewVector(1.0f);
        this.entity.level.addFreshEntity(laser);
        laser.setDeltaMovement(vec.x * 10, vec.y * 10, vec.z * 10);
    }
}
