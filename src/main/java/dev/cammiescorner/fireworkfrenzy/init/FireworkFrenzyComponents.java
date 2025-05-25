package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.component.BlastJumper;
import dev.cammiescorner.fireworkfrenzy.component.PlayerBlastJumper;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

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
