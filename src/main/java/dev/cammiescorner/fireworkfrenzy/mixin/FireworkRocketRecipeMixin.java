package dev.cammiescorner.fireworkfrenzy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.cammiescorner.fireworkfrenzy.compat.FireworkFrenzyCompat;
import dev.cammiescorner.fireworkfrenzy.compat.ExplosiveEnhancementCompat;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FireworkRocketRecipe.class)
public abstract class FireworkRocketRecipeMixin extends CustomRecipe {

	private FireworkRocketRecipeMixin(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
		throw new UnsupportedOperationException();
	}

	@Inject(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void fireworkfrenzy$captureStack(CraftingContainer inv, Level level, CallbackInfoReturnable<Boolean> cir, boolean bl, int i, int j, ItemStack itemStack, @Share("itemStack") LocalRef<ItemStack> stackRef) {
		stackRef.set(itemStack);
	}

	@ModifyExpressionValue(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/world/item/crafting/FireworkRocketRecipe;STAR_INGREDIENT:Lnet/minecraft/world/item/crafting/Ingredient;")), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Ingredient;test(Lnet/minecraft/world/item/ItemStack;)Z"))
	public boolean fireworkfrenzy$allowFireball(boolean original, @Share("itemStack") LocalRef<ItemStack> stackRef) {
		return original || FireworkFrenzyCompat.EXPLOSIVE_ENHANCEMENT.orElse(() -> () -> ExplosiveEnhancementCompat.FIREBALL_INGREDIENT.test(stackRef.get()), false);
	}

	@Inject(method = "assemble(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;putByte(Ljava/lang/String;B)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void fireworkfrenzy$addFireballTag(CraftingContainer container, RegistryAccess registryAccess, CallbackInfoReturnable<ItemStack> cir, ItemStack itemStack, CompoundTag compoundTag, ListTag listTag, int i) {
		FireworkFrenzyCompat.EXPLOSIVE_ENHANCEMENT.ifEnabled(() -> () -> {
			for (int slot = 0; slot < container.getContainerSize(); slot++) {
				ItemStack itemStack2 = container.getItem(slot);

				if (!itemStack2.isEmpty() && ExplosiveEnhancementCompat.FIREBALL_INGREDIENT.test(itemStack2)) {
					compoundTag.putBoolean("Fireball", true);
					break;
				}
			}
		});
	}
}
