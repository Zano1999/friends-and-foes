package com.faboslav.friendsandfoes.mixin;

import com.faboslav.friendsandfoes.entity.passive.ai.brain.task.BeekeeperWorkTask;
import com.faboslav.friendsandfoes.init.ModVillagerProfessions;
import net.minecraft.entity.ai.brain.task.VillagerTaskListProvider;
import net.minecraft.entity.ai.brain.task.VillagerWorkTask;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(VillagerTaskListProvider.class)
public class VillagerTaskListProviderMixin
{
	@ModifyVariable(
		method = "createWorkTasks(Lnet/minecraft/village/VillagerProfession;F)Lcom/google/common/collect/ImmutableList;",
		at = @At("STORE"),
		ordinal = 0
	)
	private static VillagerWorkTask setSecondVillagerWorkTask(
		VillagerWorkTask originalTask,
		VillagerProfession profession,
		float f
	) {
		if (
			profession == ModVillagerProfessions.BEEKEEPER.get()
		) {
			return new BeekeeperWorkTask();
		}

		return originalTask;
	}
}
