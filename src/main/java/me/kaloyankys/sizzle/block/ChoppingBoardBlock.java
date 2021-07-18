package me.kaloyankys.sizzle.block;

import me.kaloyankys.sizzle.init.SItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ChoppingBoardBlock extends Block {

    public static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 17.0D, 2.0D, 17.0D);

    public ChoppingBoardBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        int itemCount = player.getStackInHand(hand).getCount();
        if (!world.isClient()) {
            if (itemStack.getItem() == SItems.TOMATO) {
                player.swingHand(hand);
                itemStack.decrement(itemCount);
                ItemScatterer.spawn(world, pos.getX(), pos.getY() + 0.5, pos.getZ(), new ItemStack(SItems.SLICED_TOMATO, itemCount));
            } else if (itemStack.getItem() == SItems.CUCUMBER) {
                player.swingHand(hand);
                itemStack.decrement(itemCount);
                ItemScatterer.spawn(world, pos.getX(), pos.getY() + 0.5, pos.getZ(), new ItemStack(SItems.SLICED_CUCUMBER, itemCount));
            }
        }
        super.onUse(state, world, pos, player, hand, hit);
        return ActionResult.CONSUME;
    }
}
