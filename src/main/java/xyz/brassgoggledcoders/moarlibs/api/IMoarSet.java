package xyz.brassgoggledcoders.moarlibs.api;

import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

public interface IMoarSet
{
	IBoilerplateMod getMod();

	Class<? extends IHolderEntity> getHolderEntity();

	IHolderItem getHolderItem();
}
