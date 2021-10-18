package com.jasonjat.testingmod.items;

import com.jasonjat.testingmod.screen.Gui;
import com.jasonjat.testingmod.screen.PreScreen;
import com.jasonjat.testingmod.screen.TestScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Wand extends Item {

    public Wand() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand==Hand.MAIN_HAND && world.isClient) {
            MinecraftClient.getInstance().setScreen(new TestScreen());
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        if (!world.isClient) {
            BlockPos blockpos = context.getBlockPos();


        }
        return super.useOnBlock(context);
    }

}
