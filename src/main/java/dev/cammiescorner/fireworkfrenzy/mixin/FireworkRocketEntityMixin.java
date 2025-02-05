package dev.cammiescorner.fireworkfrenzy.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import dev.cammiescorner.fireworkfrenzy.compat.ExplosiveEnhancementCompat;
import dev.cammiescorner.fireworkfrenzy.compat.FireworkFrenzyCompat;
import dev.cammiescorner.fireworkfrenzy.component.BlastJumper;
import dev.cammiescorner.fireworkfrenzy.entities.DamageCloudEntity;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyCriteriaTriggers;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEnchantments;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin extends Projectile implements ItemSupplier {
	@Shadow @Final private static EntityDataAccessor<ItemStack> DATA_ID_FIREWORKS_ITEM;

	private FireworkRocketEntityMixin(EntityType<? extends Projectile> entityType, Level level) {
		super(entityType, level);
		throw new UnsupportedOperationException();
	}

	@Shadow protected abstract boolean hasExplosion();

	@Shadow private @Nullable LivingEntity attachedToEntity;
	@Shadow private int lifetime;

	@Unique private LivingEntity directTarget;

	@Inject(method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
	void fireworkfrenzy$noRandomFuse(Level level, double x, double y, double z, ItemStack stack, CallbackInfo ci, int i) {
		setDeltaMovement(0.0D, 0.05D, 0.0D);

		if(EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.FIXED_FUSE.get(), entityData.get(DATA_ID_FIREWORKS_ITEM)) > 0) {
			lifetime = 10 * i + 6;
		}
	}

	@ModifyArg(method = "dealExplosionDamage", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/phys/AABB;inflate(D)Lnet/minecraft/world/phys/AABB;"
	))
	private double fireworkfrenzy$blastRadius(double original, @Share("blastSize") LocalFloatRef blastSize, @Share("knockbackAmount") LocalFloatRef knockbackAmountRef, @Share("glowingDuration") LocalIntRef glowingDurationRef) {
		var tag = entityData.get(DATA_ID_FIREWORKS_ITEM).getTagElement("Fireworks");
		Set<FireworkRocketItem.Shape> types = EnumSet.noneOf(FireworkRocketItem.Shape.class);

		int glowingDuration = 0;
		float knockbackAmount = 1.0F;
		if(tag != null) {
			var nbtList = tag.getList(FireworkRocketItem.TAG_EXPLOSIONS, Tag.TAG_COMPOUND);

			for(int i = 0; i < nbtList.size(); i++) {
				var nbt = nbtList.getCompound(i);

				if(nbt.contains(FireworkRocketItem.TAG_EXPLOSION_TYPE, Tag.TAG_BYTE)) {
					types.add(FireworkRocketItem.Shape.values()[nbt.getByte(FireworkRocketItem.TAG_EXPLOSION_TYPE)]);
				}

				if(nbt.getBoolean(FireworkRocketItem.TAG_EXPLOSION_TRAIL)) {
					knockbackAmount += 0.1F;
				}

				if(nbt.getBoolean(FireworkRocketItem.TAG_EXPLOSION_FLICKER)) {
					glowingDuration += 20;
				}
			}
		}
		knockbackAmountRef.set(knockbackAmount);
		glowingDurationRef.set(glowingDuration);

		if(types.contains(FireworkRocketItem.Shape.LARGE_BALL)) {
			blastSize.set(5.0F);
		}
		else if(types.contains(FireworkRocketItem.Shape.STAR)) {
			blastSize.set(5.0F);
		}
		else {
			blastSize.set(3.0F);
		}

		return blastSize.get();
	}

	@Inject(method = "dealExplosionDamage", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
			ordinal = 1
	), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void fireworkfrenzy$explodePostDamage(CallbackInfo info, float damage, ItemStack stack, CompoundTag tag, ListTag nbtList, double d, Vec3 vec, List<LivingEntity> list, Iterator<LivingEntity> iterator, LivingEntity target, @Share("target") LocalRef<LivingEntity> targetRef, @Share("blastSize") LocalFloatRef blastSize, @Share("knockbackAmount") LocalFloatRef knockbackAmount, @Share("glowingDuration") LocalIntRef glowingDuration) {
		targetRef.set(target);

		if(hasExplosion() && tag != null) {
			DamageSource source = damageSources().fireworks((FireworkRocketEntity) (Object) this, getOwner());

			if(!target.isDamageSourceBlocked(source)) {
				var adjustedPos = position().add(0.0D, getBbHeight() / 2.0D, 0.0D);
				var adjustedTargetPos = target.position().add(0.0D, target.getBbHeight() / 2.0D, 0.0D);
				HitResult hitResult = ProjectileUtil.getEntityHitResult(this, adjustedPos, adjustedTargetPos, getBoundingBox().inflate(blastSize.get()), entity -> entity == target, adjustedPos.distanceToSqr(adjustedTargetPos));

				if(hitResult != null && hitResult.getType() == HitResult.Type.ENTITY) {
					var owner = getOwner();
					double distance = Math.max(1, hitResult.getLocation().distanceTo(adjustedPos));
					float fireworkDamage = (target instanceof Player ? FireworkFrenzyConfig.playerDamage : FireworkFrenzyConfig.mobDamage) * nbtList.size() + (tag.getBoolean("Fireball") ? FireworkFrenzyConfig.fireballDamageBonus : 0);

					// calculate damage falloff
					if(FireworkFrenzyConfig.rocketsHaveDamageFalloff && owner != null) {
						fireworkDamage = Math.max(FireworkFrenzyConfig.minFalloffMultiplier * fireworkDamage, fireworkDamage - Math.max(0, this.distanceTo(owner) - FireworkFrenzyConfig.rocketDamageFalloffStartDistance) * FireworkFrenzyConfig.rocketDamageFalloffPerMeter);
					}

					// calculate air strike damage
					if(EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.AIR_STRIKE.get(), stack) > 0 && owner != null && FireworkFrenzyComponents.BLAST_JUMPER.maybeGet(owner).map(BlastJumper::isBlastJumping).orElse(false)) {
						fireworkDamage *= FireworkFrenzyConfig.airStrikeDamageMultiplier;
					}

					// remove damage from owner if wearing takeoff boots
					if(target == owner && EnchantmentHelper.getEnchantmentLevel(FireworkFrenzyEnchantments.TAKEOFF.get(), target) > 0) {
						fireworkDamage = 0;
					}

					if(glowingDuration.get() > 0) {
						target.addEffect(new MobEffectInstance(MobEffects.GLOWING, glowingDuration.get(), 0, false, false));
					}

					if(target == directTarget)
						target.hurt(source, fireworkDamage);
					else
						target.hurt(source, (float) Math.max(1, fireworkDamage / distance));

					if(FireworkFrenzyConfig.allowRocketJumping) {
						double multiplier = ((nbtList.size() + (tag.getBoolean("Fireball") ? 1 : 0)) * 0.3) * knockbackAmount.get() * (target == owner ? FireworkFrenzyConfig.rocketJumpKnockbackMultiplier : FireworkFrenzyConfig.defaultKnockbackMultiplier);
						var targetVelocity = target.getDeltaMovement();
						targetVelocity = new Vec3(targetVelocity.x(), Math.max(1, Math.abs(targetVelocity.y())), targetVelocity.z()).scale(multiplier / distance);
						target.setDeltaMovement(targetVelocity);
						target.hurtMarked = true;
						if(target instanceof ServerPlayer serverPlayer) {
							FireworkFrenzyCriteriaTriggers.BLAST_JUMP.trigger(serverPlayer, targetVelocity.length());
						}
					}
				}
			}
		}

		if(FireworkFrenzyConfig.allowRocketJumping) {
			FireworkFrenzyComponents.BLAST_JUMPER.maybeGet(target).ifPresent(component -> {
				component.setTimeOnGround(0);
				component.setBlastJumping(true);
				component.sync();
			});
		}
	}

	@Inject(method = "dealExplosionDamage", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void fireworkfrenzy$spawnPotionCloud(CallbackInfo info, @Share("target") LocalRef<LivingEntity> targetRef, @Share("blastSize")LocalFloatRef blastSize) {
		ItemStack stack = entityData.get(DATA_ID_FIREWORKS_ITEM);
		var tag = stack.isEmpty() ? null : stack.getOrCreateTagElement(FireworkRocketItem.TAG_FIREWORKS);
		Set<FireworkRocketItem.Shape> types = EnumSet.noneOf(FireworkRocketItem.Shape.class);

		if(tag != null) {
			var nbtList = tag.getList("Explosions", Tag.TAG_COMPOUND);

			for(int i = 0; i < nbtList.size(); i++) {
				var nbt = nbtList.getCompound(i);

				if(nbt.contains(FireworkRocketItem.TAG_EXPLOSION_TYPE, Tag.TAG_BYTE))
					types.add(FireworkRocketItem.Shape.byId(nbt.getByte(FireworkRocketItem.TAG_EXPLOSION_TYPE)));
			}

			if(types.contains(FireworkRocketItem.Shape.STAR)) {
				DamageCloudEntity cloud = FireworkFrenzyEntityTypes.DAMAGE_CLOUD.get().create(level());

				if(cloud != null) {
					cloud.setRadius(blastSize.get());
					cloud.setOwner(attachedToEntity);
					cloud.setDuration(200);
					cloud.setFixedColor(0xf8d26a);
					cloud.setPos(position().add(0, -cloud.getRadius(), 0));
					level().addFreshEntity(cloud);
				}
			}

			if(types.contains(FireworkRocketItem.Shape.BURST) && targetRef.get() instanceof Player player && player.isBlocking() && random.nextFloat() < FireworkFrenzyConfig.burstDisableShieldChance) {
				player.getCooldowns().addCooldown(Items.SHIELD, 50);
				player.stopUsingItem();
				level().broadcastEntityEvent(this, EntityEvent.SHIELD_DISABLED);
			}
		}
	}

	@Inject(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FireworkRocketEntity;explode()V"))
	public void onDirectHit(EntityHitResult entityHitResult, CallbackInfo info) {
		if(entityHitResult.getEntity() instanceof LivingEntity target) {
			directTarget = target;
		}
	}

	@WrapWithCondition(method = "handleEntityEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;createFireworks(DDDDDDLnet/minecraft/nbt/CompoundTag;)V"))
	public boolean inhibitFireworkParticles(Level instance, double x, double y, double z, double motionX, double motionY, double motionZ, CompoundTag compound) {
		return !FireworkFrenzyCompat.EXPLOSIVE_ENHANCEMENT.isEnabled();
	}

	@Inject(method = "handleEntityEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;createFireworks(DDDDDDLnet/minecraft/nbt/CompoundTag;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
	public void fireworkfrenzy$particleTime(byte id, CallbackInfo ci, ItemStack itemStack, CompoundTag compoundTag, Vec3 vec3) {
		if(FireworkFrenzyCompat.EXPLOSIVE_ENHANCEMENT.isEnabled()) {
			if(compoundTag.getBoolean("Fireball")) {
				ExplosiveEnhancementCompat.spawnEnhancedBooms(level(), getX(), getY(), getZ(), 1.25F);
			}
			else
				level().createFireworks(getX(), getY(), getZ(), vec3.x(), vec3.y(), vec3.z(), compoundTag);
		}
	}

	@ModifyArg(method = "dealExplosionDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
	public float noSelfDamage(DamageSource source, float amount) {
		return 0;
	}

	@ModifyArg(method = "dealExplosionDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 1))
	public float noCrossbowDamage(DamageSource source, float amount) {
		return 0;
	}
}
