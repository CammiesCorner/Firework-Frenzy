package dev.cammiescorner.fireworkfrenzy.compat;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.superkat.explosiveenhancement.api.ExplosiveApi;


public class ExplosiveEnhancementCompat {
	public static final Ingredient FIREBALL_INGREDIENT = Ingredient.of(Items.FIRE_CHARGE);

	public static void spawnEnhancedBooms(Level level, double x, double y, double z, float power) {
		var type = ExplosiveApi.determineParticleType(level, new Vec3(x, y, z), ParticleTypes.EXPLOSION);
		ExplosiveApi.spawnParticles(level, x, y, z, power, type, false, true);

		level.playLocalSound(x, y, z, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL, 4.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F, false);
	}
}
