package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.entities.DamageCloudEntity;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class FireworkFrenzyEntityTypes {
	public static final RegistryHandler<EntityType<?>> ENTITY_TYPES = RegistryHandler.create(Registries.ENTITY_TYPE, FireworkFrenzy.MOD_ID);

	public static final RegistrySupplier<EntityType<DamageCloudEntity>> DAMAGE_CLOUD = ENTITY_TYPES.register("damage_cloud", () -> EntityType.Builder.of(DamageCloudEntity::new, MobCategory.MISC).fireImmune().sized(6f, 6f).build());
}
