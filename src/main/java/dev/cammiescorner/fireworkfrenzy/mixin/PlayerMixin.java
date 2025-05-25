package dev.cammiescorner.fireworkfrenzy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
	private PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
		super(entityType, level);
		throw new UnsupportedOperationException();
	}

	@ModifyReturnValue(method = "getFlyingSpeed", at = @At("RETURN"))
	public float fireworkfrenzy$airSpeed(float original) {
		if(this.getComponent(FireworkFrenzyComponents.BLAST_JUMPER).isBlastJumping())
			return original * FireworkFrenzyConfig.airStrafeSpeedMultiplier;

		return original;
	}
}
