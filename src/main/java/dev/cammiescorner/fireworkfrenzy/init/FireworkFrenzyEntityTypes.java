package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.entities.DamageCloudEntity;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;

public class FireworkFrenzyEntityTypes {

	public static final RegistryHandler<EntityType<?>> ENTITY_TYPES = RegistryHandler.create(Registries.ENTITY_TYPE, FireworkFrenzy.MOD_ID);

	public static final RegistrySupplier<EntityType<DamageCloudEntity>> DAMAGE_CLOUD = ENTITY_TYPES.register("damage_cloud", () -> FabricEntityTypeBuilder.create().entityFactory(DamageCloudEntity::new).fireImmune().dimensions(EntityDimensions.scalable(6F, 6F)).build());
}
