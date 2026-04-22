package com.mahghuuuls.examplemod.proxy;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		// Register items, blocks, tile entities, network handlers
	}

	@Override
	public void init() {
		// Register crafting recipes, event handlers
	}

	@Override
	public void postInit() {
		// Inter-mod communication, config finalization
	}
}
