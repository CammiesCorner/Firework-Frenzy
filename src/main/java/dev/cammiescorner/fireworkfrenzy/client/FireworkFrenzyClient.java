package dev.cammiescorner.fireworkfrenzy.client;

import dev.cammiescorner.fireworkfrenzy.client.sound.BlastJumpingSoundInstance;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.entity.player.Player;

public class FireworkFrenzyClient implements ClientModInitializer {
	public static void playBlastSound(Player player) {
		var soundInstance = new BlastJumpingSoundInstance(player);
		Minecraft.getInstance().getSoundManager().queueTickingSound(soundInstance);
	}

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(FireworkFrenzyEntityTypes.DAMAGE_CLOUD.get(), NoopRenderer::new);
	}
}
