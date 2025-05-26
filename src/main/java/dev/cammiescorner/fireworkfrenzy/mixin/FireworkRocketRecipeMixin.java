package dev.cammiescorner.fireworkfrenzy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.cammiescorner.fireworkfrenzy.compat.ExplosiveEnhancementCompat;
import dev.cammiescorner.fireworkfrenzy.compat.FireworkFrenzyCompat;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(FireworkRocketRecipe.class)
public abstract class FireworkRocketRecipeMixin extends CustomRecipe {
	private FireworkRocketRecipeMixin(CraftingBookCategory category) {
		super(category);
		throw new UnsupportedOperationException();
	}

	@Inject(method = "matches(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/world/level/Level;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void captureStack(CraftingInput input, Level level, CallbackInfoReturnable<Boolean> cir, boolean bl, int i, int j, ItemStack itemStack, @Share("itemStack") LocalRef<ItemStack> stackRef) {
		stackRef.set(itemStack);
	}

	@ModifyExpressionValue(method = "matches(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/world/level/Level;)Z", slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/world/item/crafting/FireworkRocketRecipe;STAR_INGREDIENT:Lnet/minecraft/world/item/crafting/Ingredient;")), at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Ingredient;test(Lnet/minecraft/world/item/ItemStack;)Z"))
	public boolean allowFireball(boolean original, @Share("itemStack") LocalRef<ItemStack> stackRef) {
		return original || FireworkFrenzyCompat.EXPLOSIVE_ENHANCEMENT.orElse(() -> () -> ExplosiveEnhancementCompat.FIREBALL_INGREDIENT.test(stackRef.get()), false);
	}

	@Inject(method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;set(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;"), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void addFireballTag(CraftingInput input, HolderLookup.Provider registries, CallbackInfoReturnable<ItemStack> cir, List<FireworkExplosion> list, int i, ItemStack itemStack2) {
		FireworkFrenzyCompat.EXPLOSIVE_ENHANCEMENT.ifEnabled(() -> () -> {
			for(int slot = 0; slot < input.size(); slot++) {
				ItemStack itemStack = input.getItem(slot);

				if(ExplosiveEnhancementCompat.FIREBALL_INGREDIENT.test(itemStack)) {
					itemStack2.set(FireworkFrenzyDataComponents.FIREBALL.get(), true);
					break;
				}
			}
		});
	}
}
