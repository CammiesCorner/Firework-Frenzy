package dev.cammiescorner.fireworkfrenzy.mixin;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
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

	@Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/Fireworks;addToTooltip(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void addTooltip(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag, CallbackInfo ci, Fireworks fireworks) {
		if(FireworkFrenzyConfig.showFireworkDamageTooltip) {
			var hasFireball = stack.getOrDefault(FireworkFrenzyDataComponents.FIREBALL.get(), false);
			float mobDamage = FireworkFrenzyConfig.mobDamage * fireworks.explosions().size();
			float playerDamage = FireworkFrenzyConfig.playerDamage * fireworks.explosions().size();

			if(hasFireball) {
				mobDamage += FireworkFrenzyConfig.fireballDamageBonus;
				playerDamage += FireworkFrenzyConfig.fireballDamageBonus;
			}

			tooltipComponents.add(Component.translatable("tooltip.fireworkfrenzy.rocket_damage_base", mobDamage).withStyle(ChatFormatting.GRAY));

			if(FireworkFrenzyConfig.mobDamage != FireworkFrenzyConfig.playerDamage)
				tooltipComponents.add(Component.translatable("tooltip.fireworkfrenzy.rocket_damage_players", playerDamage).withStyle(ChatFormatting.GRAY));

			if(hasFireball)
				tooltipComponents.add(Component.translatable("tooltip.fireworkfrenzy.rocket_has_fireball").withStyle(ChatFormatting.GOLD, ChatFormatting.ITALIC));
		}
	}
}
