package net.thechrisvazquez.tutorialmod.item.custom;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.List;

public class ClockItem extends Item {
    public ClockItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (user instanceof ServerPlayerEntity serverPlayer) {
                BlockPos spawnpos = serverPlayer.getSpawnPointPosition();
                user.teleport(spawnpos.getX(), spawnpos.getY(), spawnpos.getZ(), true);
                Text fancyMessage = Text.literal("¡Has usado el teleport! ").formatted(Formatting.BOLD, Formatting.RED);
                user.sendMessage(fancyMessage, false);
            }
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

}
