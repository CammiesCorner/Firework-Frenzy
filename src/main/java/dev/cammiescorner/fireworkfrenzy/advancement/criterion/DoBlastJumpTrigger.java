package dev.cammiescorner.fireworkfrenzy.advancement.criterion;

import com.google.gson.JsonObject;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;

public class DoBlastJumpTrigger extends SimpleCriterionTrigger<DoBlastJumpTrigger.TriggerInstance> {

	public static final ResourceLocation ID = FireworkFrenzy.id("blast_jump");

	@Override
	protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext deserializationContext) {
		double minSpeed = GsonHelper.getAsDouble(json, "speed");
		return new TriggerInstance(predicate, minSpeed);
	}

	public void trigger(ServerPlayer player, double speed) {
		this.trigger(player, triggerInstance -> speed >= triggerInstance.minSpeed);
	}

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {

		private final double minSpeed;

		public TriggerInstance(ContextAwarePredicate player, double minSpeed) {
			super(ID, player);
			this.minSpeed = minSpeed;
		}

		public static TriggerInstance hasBlastJumpSpeed(double minSpeed) {
			return new TriggerInstance(ContextAwarePredicate.ANY, minSpeed);
		}

		@Override
		public JsonObject serializeToJson(SerializationContext context) {
			var json = super.serializeToJson(context);
			json.addProperty("speed", this.minSpeed);
			return json;
		}
	}
}
