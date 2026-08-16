package dev.cammiescorner.fireworkfrenzy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends ProjectileWeaponItem {
	@Shadow
	private static float getPowerForTime(int useTime, ItemStack crossbowStack) {
		throw new UnsupportedOperationException();
	}

	@Shadow
	public abstract int getUseDuration(ItemStack stack);

	private CrossbowItemMixin(Properties properties) {
		super(properties);
		throw new UnsupportedOperationException();
	}

	@Inject(method = "onUseTick", at = @At("HEAD"))
	private void setBlastJumperComponent(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration, CallbackInfo ci) {
		FireworkFrenzy.BLAST_JUMPER_COMPONENT.set(FireworkFrenzyComponents.BLAST_JUMPER.getNullable(livingEntity));
	}

	@SuppressWarnings("ConstantValue")
	@Inject(method = "onUseTick", at = @At("RETURN"))
	private void fireworkfrenzy$stopUsingItem(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration, CallbackInfo ci) {
		var component = FireworkFrenzy.BLAST_JUMPER_COMPONENT.get();
		if (component != null && component.isBlastJumping() && EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.AIR_STRIKE.get(), stack) > 0 && getPowerForTime(getUseDuration(stack) - remainingUseDuration, stack) >= 1.0F) {
			livingEntity.releaseUsingItem();
		}

		FireworkFrenzy.BLAST_JUMPER_COMPONENT.remove();
	}

	@ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
	private static int airstrikeChargeDuration(int original, ItemStack stack) {
		if(EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.AIR_STRIKE.get(), stack) > 0) {
			var jumper = FireworkFrenzy.BLAST_JUMPER_COMPONENT.get();

			if(jumper == null) {
				new Throwable().printStackTrace();
			}

			return jumper != null && jumper.isBlastJumping() ? FireworkFrenzyConfig.airStrikeJumpingChargeTime : FireworkFrenzyConfig.airStrikeGroundedChargeTime;
		}
		return original;
	}

	@ModifyConstant(method = "getChargeDuration", constant = @Constant(intValue = 25))
	private static int configurableChargeTime(int original) {
		return FireworkFrenzyConfig.crossbowChargeTime;
	}

	@ModifyConstant(method = "getChargeDuration", constant = @Constant(intValue = 5))
	private static int fireworkfrenzy$configurableQuickChargeModifier(int original) {
		return FireworkFrenzyConfig.quickChargeModifier;
	}

	@ModifyReturnValue(method = "getChargeDuration", at = @At("RETURN"))
	private static int fireworkfrenzy$changeDefaultPullTimes(int original) {
		return Math.max(1, original);
	}

	@ModifyReturnValue(method = "getAllSupportedProjectiles", at = @At("RETURN"))
	public Predicate<ItemStack> fireworkfrenzy$getProjectiles(Predicate<ItemStack> original) {
		if (FireworkFrenzyConfig.crossbowUseRocketsFromInventory) {
			return ARROW_OR_FIREWORK;
		}

		return original;
	}

	@ModifyVariable(method = "loadProjectile", at = @At(value = "STORE", ordinal = 0), ordinal = 2)
	private static boolean fireworkfrenzy$loadProjectile(boolean bl, LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) {
		return (FireworkFrenzyConfig.crossbowAllowInfinityEnchantment && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, crossbow) > 0) && (ARROW_ONLY.test(projectile) || (FireworkFrenzyConfig.infinityEnchantmentAffectsRockets && ARROW_OR_FIREWORK.test(projectile)));
	}
}
