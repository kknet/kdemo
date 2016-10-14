package com.doodle.kdemo.skillsystem.base;

import java.util.List;

public abstract class Buff implements EffectContainer {

	protected BattleAgent target;

	@Override
	public void cast(BattleAgent target) {
		// TODO Auto-generated method stub
		this.target = target;
	}

	@Override
	public void reverse(BattleAgent target) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Effect> getEffects() {
		// TODO Auto-generated method stub
		return null;
	}

}
