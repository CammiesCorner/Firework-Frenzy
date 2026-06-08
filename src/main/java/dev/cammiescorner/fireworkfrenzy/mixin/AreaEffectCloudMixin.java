package dev.cammiescorner.fireworkfrenzy.mixin;

import dev.cammiescorner.fireworkfrenzy.entities.DamageCloudEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudMixin extends Entity {
	@Shadow public abstract EntityDimensions getDimensions(Pose pose);
	@Shadow public abstract float getRadius();

	private AreaEffectCloudMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
		throw new UnsupportedOperationException();
	}

	// TODO figure out which one we want to inject at i guess? maybe all idk?
	@Inject(method = "tick", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/level/Level;addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
	), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void spawnParticles(CallbackInfo ci, boolean bl, float f, ParticleOptions particleOptions, int i, float g, int j, float h, float k, double d, double e, double l) {
		if((Object) this instanceof DamageCloudEntity) {
			float total = getDimensions(getPose()).height() - 0.5f;

			for(float offset = 0.5f; offset < total; offset += 0.5f) {
				var v = new Vec3(d, e + offset, l);

				if(v.distanceTo(position().add(0, getRadius(), 0)) <= getRadius())
					level().addAlwaysVisibleParticle(particleOptions, d, e + offset, l, 0, 0, 0);
			}
		}
	}
}
