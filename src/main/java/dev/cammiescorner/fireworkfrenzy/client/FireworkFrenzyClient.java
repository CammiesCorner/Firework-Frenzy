package dev.cammiescorner.fireworkfrenzy.client;

import com.google.auto.service.AutoService;
import dev.cammiescorner.fireworkfrenzy.client.sound.BlastJumpingSoundInstance;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import dev.upcraft.sparkweave.api.client.event.RegisterEntityRenderersEvent;
import dev.upcraft.sparkweave.api.entrypoint.ClientEntryPoint;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.entity.player.Player;

@AutoService(ClientEntryPoint.class)
public class FireworkFrenzyClient implements ClientEntryPoint {
	public static void playBlastSound(Player player) {
		var soundInstance = new BlastJumpingSoundInstance(player);
		Minecraft.getInstance().getSoundManager().queueTickingSound(soundInstance);
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		RegisterEntityRenderersEvent.EVENT.register(event -> event.registerRenderer(FireworkFrenzyEntityTypes.DAMAGE_CLOUD, NoopRenderer::new));
	}
}
