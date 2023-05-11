package me.contaria.emulator114.mixin.entity;

import me.contaria.emulator114.plugin.annotations.MCBug;
import me.contaria.emulator114.plugin.annotations.Ugly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Arm;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow protected abstract void dropXp();

    @Shadow public abstract Arm getMainArm();

    // Reverts: "Experience orbs now appear at the same spatial and temporal location as loot when an entity is killed."
    @Redirect(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;dropXp()V"))
    private void emulator114$noDroppingXPearly(LivingEntity entity) {
    }

    // see above
    @Inject(method = "updatePostDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;remove()V"))
    private void emulator114$dropXPLate(CallbackInfo ci) {
        this.dropXp();
    }

    // Bugreport: https://bugs.mojang.com/browse/MC-159574
    @MCBug("MC-159574")
    @Redirect(method = "consumeItem", at = @At(value = "INVOKE", target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z"))
    private boolean emulator114$consumeWrongItem(Object item1, Object item2) {
        return true;
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateTrackedPosition(DDD)V"))
    private void emulator114$noUpdatingTrackedPosition(LivingEntity entity, double x, double y, double z) {
    }

    /**
     * @author contaria
     * @reason Where the game puts you on dismount changed, but I haven't found a nice way of reverting it yet so for now we just overwrite it with the original 1.14 method.
     */
    @Ugly
    @Overwrite
    private void onDismounted(Entity vehicle) {
        if (vehicle instanceof BoatEntity || vehicle instanceof HorseBaseEntity) {
            double d = (this.getWidth() / 2.0f + vehicle.getWidth() / 2.0f) + 0.4;
            float f = vehicle instanceof BoatEntity ? 0.0f : 1.5707964f * (this.getMainArm() == Arm.RIGHT ? -1 : 1);
            float g = -MathHelper.sin(-this.yaw * ((float)Math.PI / 180) - (float)Math.PI + f);
            float h = -MathHelper.cos(-this.yaw * ((float)Math.PI / 180) - (float)Math.PI + f);
            double e = Math.abs(g) > Math.abs(h) ? d / Math.abs(g) : d / Math.abs(h);
            double i = this.getX() + g * e;
            double j = this.getZ() + h * e;
            this.updatePosition(i, vehicle.getY() + vehicle.getHeight() + 0.001, j);
            if (this.world.doesNotCollide(this, this.getBoundingBox().union(vehicle.getBoundingBox()))) {
                return;
            }
            this.updatePosition(i, vehicle.getY() + vehicle.getHeight() + 1.001, j);
            if (this.world.doesNotCollide(this, this.getBoundingBox().union(vehicle.getBoundingBox()))) {
                return;
            }
            this.updatePosition(vehicle.getX(), vehicle.getY() + this.getHeight() + 0.001, vehicle.getZ());
            return;
        }
        double k = vehicle.getX();
        double l = vehicle.getBoundingBox().y1 + vehicle.getHeight();
        double e = vehicle.getZ();
        Direction direction = vehicle.getMovementDirection();
        if (direction != null) {
            Direction direction2 = direction.rotateYClockwise();
            int[][] is = new int[][]{{0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}};
            double m = Math.floor(this.getX()) + 0.5;
            double n = Math.floor(this.getZ()) + 0.5;
            double o = this.getBoundingBox().x2 - this.getBoundingBox().x1;
            double p = this.getBoundingBox().z2 - this.getBoundingBox().z1;
            Box box = new Box(m - o / 2.0, vehicle.getBoundingBox().y1, n - p / 2.0, m + o / 2.0, Math.floor(vehicle.getBoundingBox().y1) + this.getHeight(), n + p / 2.0);
            for (int[] js : is) {
                BlockPos blockPos;
                double q = direction.getOffsetX() * js[0] + direction2.getOffsetX() * js[1];
                double r = direction.getOffsetZ() * js[0] + direction2.getOffsetZ() * js[1];
                double s = m + q;
                double t = n + r;
                Box box2 = box.offset(q, 0.0, r);
                if (this.world.doesNotCollide(this, box2)) {
                    blockPos = new BlockPos(s, this.getY(), t);
                    if (this.world.getBlockState(blockPos).hasSolidTopSurface(this.world, blockPos, this)) {
                        this.requestTeleport(s, this.getY() + 1.0, t);
                        return;
                    }
                    BlockPos blockPos2 = new BlockPos(s, this.getY() - 1.0, t);
                    if (!this.world.getBlockState(blockPos2).hasSolidTopSurface(this.world, blockPos2, this) && !this.world.getFluidState(blockPos2).matches(FluidTags.WATER)) continue;
                    k = s;
                    l = this.getY() + 1.0;
                    e = t;
                    continue;
                }
                blockPos = new BlockPos(s, this.getY() + 1.0, t);
                if (!this.world.doesNotCollide(this, box2.offset(0.0, 1.0, 0.0)) || !this.world.getBlockState(blockPos).hasSolidTopSurface(this.world, blockPos, this)) continue;
                k = s;
                l = this.getY() + 2.0;
                e = t;
            }
        }
        this.requestTeleport(k, l, e);
    }
}