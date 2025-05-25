package dev.cammiescorner.fireworkfrenzy.component;

import net.minecraft.world.entity.Entity;
import org.ladysnake.cca.api.v3.component.Component;

public interface BlastJumper extends Component {
	Entity getEntity();

	boolean isBlastJumping();

	void setBlastJumping(boolean blastJumping);

	int getTimeOnGround();

	void setTimeOnGround(int timeOnGround);

	void sync();
}
