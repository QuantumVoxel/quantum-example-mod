package com.example.mixin.server;

import com.example.ExampleMod;
import dev.ultreon.quantum.server.QuantumVoxelServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(QuantumVoxelServer.class)
public class ExampleMixin {
	@Inject(at = @At("RETURN"), method = "<init>")
	private void init(CallbackInfo info) {
		// This code is injected into the start of Dimension.<init>()V
		ExampleMod.LOGGER.info("Hello World from Quantum Voxel (server)!");
	}
}