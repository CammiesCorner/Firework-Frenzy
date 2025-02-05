package dev.cammiescorner.fireworkfrenzy.mixin;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(FireworkRocketItem.class)
public abstract class FireworkRocketItemMixin extends Item {
	public FireworkRocketItemMixin(Properties properties) {
		super(properties);
		throw new UnsupportedOperationException();
	}

	@Inject(method = "use", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
	public void onUse(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
		if (FireworkFrenzyConfig.boostCancelsRocketJumping) {
			var component = player.getComponent(FireworkFrenzyComponents.BLAST_JUMPER);
			if (component.isBlastJumping()) {
				component.setBlastJumping(false);
				component.sync();
			}
		}
	}

	@Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/ListTag;isEmpty()Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void addTooltip(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced, CallbackInfo ci, CompoundTag compoundTag, ListTag listTag) {
		if (FireworkFrenzyConfig.showFireworkDamageTooltip) {
			var hasFireball = compoundTag.getBoolean("Fireball");
			float mobDamage = 0.0F;
			float playerDamage = 0.0F;
			if (!listTag.isEmpty()) {
				mobDamage += FireworkFrenzyConfig.mobDamage * listTag.size();
				playerDamage += FireworkFrenzyConfig.playerDamage * listTag.size();
			}

			if (hasFireball) {
				mobDamage += FireworkFrenzyConfig.fireballDamageBonus;
				playerDamage += FireworkFrenzyConfig.fireballDamageBonus;
			}

			tooltipComponents.add(Component.translatable("tooltip.fireworkfrenzy.rocket_damage_base", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(mobDamage)).withStyle(ChatFormatting.GRAY));
			if (FireworkFrenzyConfig.mobDamage != FireworkFrenzyConfig.playerDamage) {
				tooltipComponents.add(Component.translatable("tooltip.fireworkfrenzy.rocket_damage_players", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(playerDamage)).withStyle(ChatFormatting.GRAY));
			}

			if(hasFireball) {
				tooltipComponents.add(Component.translatable("tooltip.fireworkfrenzy.rocket_has_fireball").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
			}
		}
	}
}
