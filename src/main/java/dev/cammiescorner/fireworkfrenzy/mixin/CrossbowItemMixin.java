package dev.cammiescorner.fireworkfrenzy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.component.BlastJumper;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends ProjectileWeaponItem {
	@Unique private static final ThreadLocal<BlastJumper> BLAST_JUMPER_COMPONENT = new ThreadLocal<>();

	@Shadow private static float getPowerForTime(int timeLeft, ItemStack stack, LivingEntity shooter) { throw new UnsupportedOperationException(); }
	@Shadow public abstract int getUseDuration(ItemStack stack, LivingEntity entity);

	private CrossbowItemMixin(Properties properties) {
		super(properties);
		throw new UnsupportedOperationException();
	}

	@Inject(method = "onUseTick", at = @At("HEAD"))
	private void setBlastJumperComponent(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration, CallbackInfo ci) {
		BLAST_JUMPER_COMPONENT.set(FireworkFrenzyComponents.BLAST_JUMPER.getNullable(livingEntity));
	}

	@Inject(method = "onUseTick", at = @At("RETURN"))
	private void stopUsingItem(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration, CallbackInfo ci) {
		var component = BLAST_JUMPER_COMPONENT.get();

		// TODO enchantment shenanigans
//		if(component != null && component.isBlastJumping() && EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.AIR_STRIKE.holder(), stack) > 0 && getPowerForTime(getUseDuration(stack, livingEntity) - remainingUseDuration, stack, livingEntity) >= 1f)
//			livingEntity.releaseUsingItem();

		BLAST_JUMPER_COMPONENT.remove();
	}

	@ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
	private static int airstrikeChargeDuration(int original, ItemStack stack) {
		// TODO enchantment shenanigans
//		if(EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.AIR_STRIKE.holder(), stack) > 0) {
//			var jumper = BLAST_JUMPER_COMPONENT.get();
//
//			return jumper != null && jumper.isBlastJumping() ? FireworkFrenzyConfig.airStrikeJumpingChargeTime : FireworkFrenzyConfig.airStrikeGroundedChargeTime;
//		}

		return original;
	}

	@ModifyConstant(method = "getChargeDuration", constant = @Constant(floatValue = 20))
	private static float configurableChargeTime(float constant) {
		return FireworkFrenzyConfig.crossbowChargeTime;
	}

	@ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
	private static int changeDefaultPullTimes(int original) {
		return Math.max(1, original);
	}

	@ModifyReturnValue(method = "getAllSupportedProjectiles", at = @At("RETURN"))
	public Predicate<ItemStack> getProjectiles(Predicate<ItemStack> original) {
		if (FireworkFrenzyConfig.crossbowUseRocketsFromInventory) {
			return ARROW_OR_FIREWORK;
		}

		return original;
	}
}
