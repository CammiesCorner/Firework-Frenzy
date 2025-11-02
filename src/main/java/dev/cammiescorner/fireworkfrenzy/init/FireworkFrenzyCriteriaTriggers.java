package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.advancement.criterion.DoBlastJumpTrigger;
import dev.cammiescorner.fireworkfrenzy.advancement.criterion.MultiJumpTrigger;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;

public class FireworkFrenzyCriteriaTriggers {

	public static final RegistryHandler<CriterionTrigger<?>> TRIGGERS = RegistryHandler.create(Registries.TRIGGER_TYPE, FireworkFrenzy.MOD_ID);

	public static final RegistrySupplier<DoBlastJumpTrigger> BLAST_JUMP = TRIGGERS.register("blast_jump", DoBlastJumpTrigger::new);
	public static final RegistrySupplier<MultiJumpTrigger> CONSECUTIVE_BLAST_JUMPS = TRIGGERS.register("consecutive_blast_jumps", MultiJumpTrigger::new);
}
