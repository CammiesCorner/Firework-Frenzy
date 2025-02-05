package dev.cammiescorner.fireworkfrenzy.client.sound;

import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class BlastJumpingSoundInstance extends AbstractTickableSoundInstance {
	private final Player player;
	private int tickCount;

	public BlastJumpingSoundInstance(Player player) {
		super(SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS, SoundInstance.createUnseededRandom());
		this.player = player;
		this.looping = true;
		this.delay = 0;
		this.volume = 0.1F;
	}

	public void tick() {
		this.tickCount++;

		var component = player.getComponent(FireworkFrenzyComponents.BLAST_JUMPER);
		if (!this.player.isRemoved() && !this.player.isFallFlying() && (this.tickCount <= 20 || component.isBlastJumping())) {
			this.x = this.player.getX();
			this.y = this.player.getY();
			this.z = this.player.getZ();
			float f = (float) this.player.getDeltaMovement().lengthSqr();
			this.volume = f / 4F;
			this.pitch = 1F;
		} else {
			this.stop();
		}
	}
}
