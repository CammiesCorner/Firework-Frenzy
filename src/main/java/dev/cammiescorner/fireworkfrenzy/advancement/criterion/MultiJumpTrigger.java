package dev.cammiescorner.fireworkfrenzy.advancement.criterion;

import com.google.gson.JsonObject;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;

public class MultiJumpTrigger extends SimpleCriterionTrigger<MultiJumpTrigger.TriggerInstance> {

	public static final ResourceLocation ID = FireworkFrenzy.id("consecutive_blast_jumps");

	@Override
	protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext deserializationContext) {
		int consecutiveJumps = GsonHelper.getAsInt(json, "jumps");
		return new TriggerInstance(predicate, consecutiveJumps);
	}

	public void trigger(ServerPlayer player, int totalJumps) {
		this.trigger(player, triggerInstance -> totalJumps >= triggerInstance.consecutiveJumps);
	}

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {

		private final int consecutiveJumps;

		public TriggerInstance(ContextAwarePredicate player, int consecutiveJumps) {
			super(ID, player);
			this.consecutiveJumps = consecutiveJumps;
		}

		public static TriggerInstance hasConsecutiveJumps(int consecutiveJumps) {
			return new TriggerInstance(ContextAwarePredicate.ANY, consecutiveJumps);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext context) {
			var json = super.serializeToJson(context);
			json.addProperty("jumps", this.consecutiveJumps);
			return json;
		}
	}
}
