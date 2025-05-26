package dev.cammiescorner.fireworkfrenzy.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
	private LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
		super(clientLevel, gameProfile);
		throw new UnsupportedOperationException();
	}

	@ModifyExpressionValue(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
	public boolean noSlowDownWhileRocketJumping(boolean original) {
		return original && !this.getComponent(FireworkFrenzyComponents.BLAST_JUMPER).isBlastJumping();
	}
}
