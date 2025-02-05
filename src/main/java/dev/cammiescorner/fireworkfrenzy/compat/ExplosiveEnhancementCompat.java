package dev.cammiescorner.fireworkfrenzy.compat;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.superkat.explosiveenhancement.ExplosiveEnhancementClient;
import net.superkat.explosiveenhancement.api.ExplosiveApi;


public class ExplosiveEnhancementCompat {
	public static final Ingredient FIREBALL_INGREDIENT = Ingredient.of(Items.FIRE_CHARGE);

	public static void spawnEnhancedBooms(Level level, double x, double y, double z, float power) {
		boolean isUnderWater = false;

		if(ExplosiveEnhancementClient.config.underwaterExplosions && level.getFluidState(BlockPos.containing(x, y, z)).is(FluidTags.WATER)) {
			isUnderWater = true;

			if(ExplosiveEnhancementClient.config.debugLogs) {
				FireworkFrenzy.LOGGER.info("Spawning underwater enhanced explosion particle!");
			}
		}

		ExplosiveApi.spawnParticles(level, x, y, z, power, isUnderWater, false, true);
		level.playLocalSound(x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL, 4.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F, false);
	}
}
