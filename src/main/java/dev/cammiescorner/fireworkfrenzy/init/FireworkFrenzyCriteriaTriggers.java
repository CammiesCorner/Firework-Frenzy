package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.advancement.criterion.DoBlastJumpTrigger;
import dev.cammiescorner.fireworkfrenzy.advancement.criterion.MultiJumpTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class FireworkFrenzyCriteriaTriggers {

	public static final DoBlastJumpTrigger BLAST_JUMP = new DoBlastJumpTrigger();
	public static final MultiJumpTrigger CONSECUTIVE_BLAST_JUMPS = new MultiJumpTrigger();

	public static void register() {
		CriteriaTriggers.register(BLAST_JUMP);
		CriteriaTriggers.register(CONSECUTIVE_BLAST_JUMPS);
	}
}
