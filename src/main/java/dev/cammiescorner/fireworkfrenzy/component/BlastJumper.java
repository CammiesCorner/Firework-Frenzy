package dev.cammiescorner.fireworkfrenzy.component;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.world.entity.Entity;

public interface BlastJumper extends Component {

	Entity getEntity();

	boolean isBlastJumping();

	void setBlastJumping(boolean blastJumping);

	int getTimeOnGround();

	void setTimeOnGround(int timeOnGround);

	void sync();
}
