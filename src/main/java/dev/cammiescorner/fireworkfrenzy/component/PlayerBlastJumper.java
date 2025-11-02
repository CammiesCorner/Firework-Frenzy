package dev.cammiescorner.fireworkfrenzy.component;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.client.FireworkFrenzyClient;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyCriteriaTriggers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class PlayerBlastJumper implements BlastJumper, AutoSyncedComponent, ServerTickingComponent, CommonTickingComponent {
	private static final int MAX_TIME_ON_GROUND = 100;
	private final Player player;
	private boolean blastJumping = false;
	private int timeOnGround = 0;
	private int consecutiveJumps = 0;

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

		if(blastJumping) {
			consecutiveJumps++;

			if(player instanceof ServerPlayer serverPlayer)
				FireworkFrenzyCriteriaTriggers.CONSECUTIVE_BLAST_JUMPS.get().trigger(serverPlayer, consecutiveJumps);
		}
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
	public void applySyncPacket(RegistryFriendlyByteBuf buf) {
		var previous = isBlastJumping();
		AutoSyncedComponent.super.applySyncPacket(buf);

		if(!previous && isBlastJumping() && player.level().isClientSide())
			FireworkFrenzyClient.playBlastSound(player);
	}

	@Override
	public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		blastJumping = tag.getBoolean("blastJumping");
		timeOnGround = tag.getInt("timeOnGround");
	}

	@Override
	public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
		tag.putBoolean("blastJumping", blastJumping);
		tag.putInt("timeOnGround", timeOnGround);
	}

	@Override
	public void tick() {
		if(isBlastJumping()) {
			if(player.onGround() || player.isUnderWater()) {
				setTimeOnGround(timeOnGround + 1);

				if(this.consecutiveJumps > 0) {
					this.consecutiveJumps = 0;
					sync();
				}
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
