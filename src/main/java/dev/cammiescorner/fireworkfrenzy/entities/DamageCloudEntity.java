package dev.cammiescorner.fireworkfrenzy.entities;

import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyDamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

public class DamageCloudEntity extends AreaEffectCloud {
	public DamageCloudEntity(EntityType<? extends AreaEffectCloud> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void tick() {
		super.tick();

		if(!level().isClientSide()) {
			for(LivingEntity target : level().getEntitiesOfClass(LivingEntity.class, getBoundingBox())) {
				if(target.tickCount % 10 == 0)
					target.hurt(FireworkFrenzyDamageTypes.getDamageCloudDamage(this, getOwner()), 4f);
			}
		}
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return EntityDimensions.scalable(getRadius() * 2, getRadius() * 2);
	}
}
