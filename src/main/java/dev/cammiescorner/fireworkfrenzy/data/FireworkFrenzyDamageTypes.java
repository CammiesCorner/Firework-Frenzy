package dev.cammiescorner.fireworkfrenzy.data;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public class FireworkFrenzyDamageTypes {
	public static final ResourceKey<DamageType> DAMAGE_CLOUD = ResourceKey.create(Registries.DAMAGE_TYPE, FireworkFrenzy.id("damage_cloud"));

	public static DamageSource getDamageCloudDamage(Entity direct, @Nullable Entity trueSource) {
		return direct.damageSources().source(DAMAGE_CLOUD, direct, trueSource);
	}
}
