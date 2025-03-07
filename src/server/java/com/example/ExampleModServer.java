package com.example;

import dev.ultreon.quantum.Logger;
import dev.ultreon.quantum.LoggerFactory;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

public class ExampleModServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ExampleMod.LOGGER.info("Hello Fabric world!");
	}
}