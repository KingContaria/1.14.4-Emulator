package me.contaria.emulator114.mixin._testing;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.tag.BlockTags;
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
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow public abstract Arm getMainArm();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void onDismounted(Entity vehicle) {
        if (this.world.getBlockState(new BlockPos(vehicle)).getBlock().matches(BlockTags.PORTALS)) {
            this.updatePosition(vehicle.getX(), vehicle.getBodyY(1.0) + 0.001, vehicle.getZ());
            return;
        }
        if (vehicle instanceof BoatEntity || vehicle instanceof HorseBaseEntity) {
            float f;
            int i;
            double e;
            double d = (double)(this.getWidth() / 2.0f + vehicle.getWidth() / 2.0f) + 0.4;
            Box box = vehicle.getBoundingBox();
            if (vehicle instanceof BoatEntity) {
                e = box.y2;
                i = 2;
                f = 0.0f;
            } else {
                e = box.y1;
                i = 3;
                f = 1.5707964f * (float)(this.getMainArm() == Arm.RIGHT ? -1 : 1);
            }
            float g = -this.yaw * ((float)Math.PI / 180) - (float)Math.PI + f;
            float h = -MathHelper.sin(g);
            float j = -MathHelper.cos(g);
            double k = Math.abs(h) > Math.abs(j) ? d / (double)Math.abs(h) : d / (double)Math.abs(j);
            Box box2 = this.getBoundingBox().offset(-this.getX(), -this.getY(), -this.getZ());
            ImmutableSet<Entity> immutableSet = ImmutableSet.of(this, vehicle);
            double l = this.getX() + (double)h * k;
            double m = this.getZ() + (double)j * k;
            double n = 0.001;
            for (int o = 0; o < i; ++o) {
                double p = e + n;
                if (this.world.doesNotCollide(this, box2.offset(l, p, m), immutableSet)) {
                    System.out.println(l + ", " + p + ", " + m);
                    this.updatePosition(l, p, m);
                    return;
                }
                n += 1.0;
            }
            System.out.println("eee:  " + vehicle.getX() + ", " + vehicle.getBodyY(1.0) + 0.001 + ", " + vehicle.getZ());
            this.updatePosition(vehicle.getX(), vehicle.getBodyY(1.0) + 0.001, vehicle.getZ());
            return;
        }
        double q = vehicle.getX();
        double r = vehicle.getBodyY(1.0);
        double s = vehicle.getZ();
        Direction direction = vehicle.getMovementDirection();
        if (direction != null && direction.getAxis() != Direction.Axis.Y) {
            Direction direction2 = direction.rotateYClockwise();
            int[][] is = new int[][]{{0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}};
            double k = Math.floor(this.getX()) + 0.5;
            double t = Math.floor(this.getZ()) + 0.5;
            double l = this.getBoundingBox().x2 - this.getBoundingBox().x1;
            double m = this.getBoundingBox().z2 - this.getBoundingBox().z1;
            Box box3 = new Box(k - l / 2.0, vehicle.getBoundingBox().y1, t - m / 2.0, k + l / 2.0, Math.floor(vehicle.getBoundingBox().y1) + (double)this.getHeight(), t + m / 2.0);
            for (int[] js : is) {
                BlockPos blockPos;
                double u = direction.getOffsetX() * js[0] + direction2.getOffsetX() * js[1];
                double v = direction.getOffsetZ() * js[0] + direction2.getOffsetZ() * js[1];
                double w = k + u;
                double x = t + v;
                Box box4 = box3.offset(u, 0.0, v);
                if (this.world.doesNotCollide(this, box4)) {
                    blockPos = new BlockPos(w, this.getY(), x);
                    if (this.world.getBlockState(blockPos).hasSolidTopSurface(this.world, blockPos, this)) {
                        this.requestTeleport(w, this.getY() + 1.0, x);
                        return;
                    }
                    BlockPos blockPos2 = new BlockPos(w, this.getY() - 1.0, x);
                    if (!this.world.getBlockState(blockPos2).hasSolidTopSurface(this.world, blockPos2, this) && !this.world.getFluidState(blockPos2).matches(FluidTags.WATER)) continue;
                    q = w;
                    r = this.getY() + 1.0;
                    s = x;
                    continue;
                }
                blockPos = new BlockPos(w, this.getY() + 1.0, x);
                if (!this.world.doesNotCollide(this, box4.offset(0.0, 1.0, 0.0)) || !this.world.getBlockState(blockPos).hasSolidTopSurface(this.world, blockPos, this)) continue;
                q = w;
                r = this.getY() + 2.0;
                s = x;
            }
        }
        this.requestTeleport(q, r, s);
    }

    /**
     * @author
     * @reason
     */
    //@Overwrite
    private void onDismounted2(Entity entity) {
        if (entity instanceof BoatEntity || entity instanceof HorseBaseEntity) {
            double d = (double)(this.getWidth() / 2.0f + entity.getWidth() / 2.0f) + 0.4;
            float f = entity instanceof BoatEntity ? 0.0f : 1.5707964f * (float)(this.getMainArm() == Arm.RIGHT ? -1 : 1);
            float g = -MathHelper.sin(-this.yaw * ((float)Math.PI / 180) - (float)Math.PI + f);
            float h = -MathHelper.cos(-this.yaw * ((float)Math.PI / 180) - (float)Math.PI + f);
            double e = Math.abs(g) > Math.abs(h) ? d / (double)Math.abs(g) : d / (double)Math.abs(h);
            double i = this.getX() + (double)g * e;
            double j = this.getZ() + (double)h * e;
            this.updatePosition(i, entity.getY() + (double)entity.getHeight() + 0.001, j);
            if (this.world.doesNotCollide(this, this.getBoundingBox().union(entity.getBoundingBox()))) {
                return;
            }
            this.updatePosition(i, entity.getY() + (double)entity.getHeight() + 1.001, j);
            if (this.world.doesNotCollide(this, this.getBoundingBox().union(entity.getBoundingBox()))) {
                return;
            }
            this.updatePosition(entity.getX(), entity.getY() + (double)this.getHeight() + 0.001, entity.getZ());
            return;
        }
        double k = entity.getX();
        double l = entity.getBoundingBox().y1 + (double)entity.getHeight();
        double e = entity.getZ();
        Direction direction = entity.getMovementDirection();
        if (direction != null) {
            Direction direction2 = direction.rotateYClockwise();
            int[][] is = new int[][]{{0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}};
            double m = Math.floor(this.getX()) + 0.5;
            double n = Math.floor(this.getZ()) + 0.5;
            double o = this.getBoundingBox().x2 - this.getBoundingBox().x1;
            double p = this.getBoundingBox().z2 - this.getBoundingBox().z1;
            Box box = new Box(m - o / 2.0, entity.getBoundingBox().y1, n - p / 2.0, m + o / 2.0, Math.floor(entity.getBoundingBox().y1) + (double)this.getHeight(), n + p / 2.0);
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

    //@Redirect(method = "onDismounted", at = @At(value = "FIELD", target = "Lnet/minecraft/util/math/Box;y2:D"))
    private double test(Box box) {
        return box.y1;
    }
}
