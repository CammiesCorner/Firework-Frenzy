package dev.cammiescorner.fireworkfrenzy.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {
	@WrapOperation(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;getChargeDuration(Lnet/minecraft/world/item/ItemStack;)I"))
	private static int setBlastJumper(ItemStack crossbowStack, Operation<Integer> original, @Local(argsOnly = true) AbstractClientPlayer player) {
		FireworkFrenzy.BLAST_JUMPER_COMPONENT.set(player.getComponent(FireworkFrenzyComponents.BLAST_JUMPER));

		int orig = original.call(crossbowStack);

		FireworkFrenzy.BLAST_JUMPER_COMPONENT.remove();

		return orig;
	}
}
