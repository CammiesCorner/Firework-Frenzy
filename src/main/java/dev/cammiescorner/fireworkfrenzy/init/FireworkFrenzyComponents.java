package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.component.BlastJumper;
import dev.cammiescorner.fireworkfrenzy.component.PlayerBlastJumper;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.world.entity.player.Player;

public class FireworkFrenzyComponents implements EntityComponentInitializer {

	public static final ComponentKey<BlastJumper> BLAST_JUMPER = ComponentRegistry.getOrCreate(FireworkFrenzy.id("blast_jumper"), BlastJumper.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.beginRegistration(Player.class, BLAST_JUMPER)
			.impl(PlayerBlastJumper.class)
			.respawnStrategy(RespawnCopyStrategy.NEVER_COPY)
			.end(PlayerBlastJumper::new);
	}
}
