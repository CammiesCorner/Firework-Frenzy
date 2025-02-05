package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.advancement.criterion.DoBlastJumpTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class FireworkFrenzyCriteriaTriggers {

	public static final DoBlastJumpTrigger DO_BLAST_JUMP = new DoBlastJumpTrigger();

	public static void register() {
		CriteriaTriggers.register(DO_BLAST_JUMP);
	}
}
