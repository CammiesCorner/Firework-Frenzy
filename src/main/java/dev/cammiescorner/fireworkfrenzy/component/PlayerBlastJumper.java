package dev.cammiescorner.fireworkfrenzy.component;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.client.FireworkFrenzyClient;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class PlayerBlastJumper implements BlastJumper, AutoSyncedComponent, ServerTickingComponent, CommonTickingComponent {

	private static final int MAX_TIME_ON_GROUND = 100;

	private final Player player;
	private boolean blastJumping = false;
	private int timeOnGround = 0;

	public PlayerBlastJumper(Player player) {
		this.player = player;
	}

	@Override
	public Entity getEntity() {
		return player;
	}

	@Override
	public boolean isBlastJumping() {
		return blastJumping;
	}

	@Override
	public void setBlastJumping(boolean blastJumping) {
		this.blastJumping = blastJumping;
	}

	@Override
	public int getTimeOnGround() {
		return timeOnGround;
	}

	@Override
	public void setTimeOnGround(int timeOnGround) {
		this.timeOnGround = Mth.clamp(timeOnGround, 0, MAX_TIME_ON_GROUND);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void applySyncPacket(FriendlyByteBuf buf) {
		var previous = isBlastJumping();
		AutoSyncedComponent.super.applySyncPacket(buf);
		if(!previous && isBlastJumping() && player.level().isClientSide()) {
			FireworkFrenzyClient.playBlastSound(player);
		}
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		blastJumping = tag.getBoolean("blastJumping");
		timeOnGround = tag.getInt("timeOnGround");
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putBoolean("blastJumping", blastJumping);
		tag.putInt("timeOnGround", timeOnGround);
	}

	@Override
	public void tick() {
		if(isBlastJumping()) {
			if(player.onGround() || player.isUnderWater()) {
				setTimeOnGround(timeOnGround + 1);
			}

			if(getTimeOnGround() > 2 || player.isPassenger() || (FireworkFrenzyConfig.elytraCancelsRocketJumping && player.isFallFlying()) || !player.isAlive() || player.isSpectator() || player.getAbilities().flying) {
				setBlastJumping(false);
				sync();
			}
		}
		else {
			timeOnGround = 0;
		}
	}

	@Override
	public void sync() {
		FireworkFrenzyComponents.BLAST_JUMPER.sync(player);
	}
}
