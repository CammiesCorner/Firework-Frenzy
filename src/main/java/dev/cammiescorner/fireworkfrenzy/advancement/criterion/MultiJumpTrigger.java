package dev.cammiescorner.fireworkfrenzy.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyCriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;

import java.util.Optional;

public class MultiJumpTrigger extends SimpleCriterionTrigger<MultiJumpTrigger.TriggerInstance> {

	public void trigger(ServerPlayer player, int totalJumps) {
		this.trigger(player, triggerInstance -> totalJumps >= triggerInstance.consecutiveJumps);
	}

	@Override
	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}


	public record TriggerInstance(Optional<ContextAwarePredicate> player, int consecutiveJumps) implements SimpleInstance {

		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
			ExtraCodecs.NON_NEGATIVE_INT.fieldOf("jumps").forGetter(TriggerInstance::consecutiveJumps)
		).apply(instance, TriggerInstance::new));

		public static Criterion<TriggerInstance> hasConsecutiveJumps(int consecutiveJumps) {
			return FireworkFrenzyCriteriaTriggers.CONSECUTIVE_BLAST_JUMPS.get().createCriterion(new TriggerInstance(Optional.empty(), consecutiveJumps));
		}
	}
}
