package dev.cammiescorner.fireworkfrenzy.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyCriteriaTriggers;
import dev.cammiescorner.fireworkfrenzy.util.FFCodecs;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class DoBlastJumpTrigger extends SimpleCriterionTrigger<DoBlastJumpTrigger.TriggerInstance> {

	public void trigger(ServerPlayer player, double speed) {
		this.trigger(player, triggerInstance -> speed >= triggerInstance.minSpeed);
	}

	@Override
	public Codec<TriggerInstance> codec() {
		return TriggerInstance.CODEC;
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, double minSpeed) implements SimpleInstance {

		public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
			FFCodecs.NON_NEGATIVE_DOUBLE.fieldOf("speed").forGetter(TriggerInstance::minSpeed)
		).apply(instance, TriggerInstance::new));

		public static Criterion<TriggerInstance> hasBlastJumpSpeed(double minSpeed) {
			return FireworkFrenzyCriteriaTriggers.BLAST_JUMP.get().createCriterion(new TriggerInstance(Optional.empty(), minSpeed));
		}
	}
}
