package petterim1.arrowtrails;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.level.ParticleEffect;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ATentity extends EntityArrow {

    public String trail;

    public ATentity(FullChunk chunk, CompoundTag nbt) {
        this(chunk, nbt, null);
    }

    public ATentity(FullChunk chunk, CompoundTag nbt, Entity shootingEntity) {
        this(chunk, nbt, shootingEntity, false);
    }

    public ATentity(FullChunk chunk, CompoundTag nbt, Entity shootingEntity, boolean critical) {
        this(chunk, nbt, shootingEntity, critical, false);
    }

    public ATentity(FullChunk chunk, CompoundTag nbt, Entity shootingEntity, boolean critical, boolean isFromCrossbow) {
        this(chunk, nbt, shootingEntity, critical, isFromCrossbow, null);
    }

    public ATentity(FullChunk chunk, CompoundTag nbt, Entity shootingEntity, boolean critical, boolean isFromCrossbow, String trail) {
        super(chunk, nbt, shootingEntity, critical);
        this.trail = trail;
    }

    @Override
    public boolean onUpdate(int currentTick) {
        boolean onUpdate = super.onUpdate(currentTick);

        if (!closed) {
            if (null != trail) {
                if ((!onGround && !hadCollision) || 0 == currentTick % 30) {
                    level.addParticleEffect(this, ParticleEffect.valueOf(trail));
                }
            }
        }

        return onUpdate;
    }
}
