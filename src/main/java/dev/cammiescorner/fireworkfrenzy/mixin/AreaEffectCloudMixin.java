package dev.cammiescorner.fireworkfrenzy.mixin;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudMixin extends Entity {
	@Shadow public abstract EntityDimensions getDimensions(Pose pose);

	private AreaEffectCloudMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
		throw new UnsupportedOperationException();
	}

	// TODO figure out which one we want to inject at i guess? maybe all idk?
	@Inject(method = "tick", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/level/Level;addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
	), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void spawnParticles(CallbackInfo ci, boolean bl, float f, ParticleOptions particleOptions, int i, float g, int j, float h, float k, double d, double e, double l) {
		float total = getDimensions(getPose()).height() - 0.5f;

		for(float offset = 0.5F; offset < total; offset += 0.5f)
			level().addAlwaysVisibleParticle(particleOptions, g, h + offset, k, d, e, l);
	}
}
