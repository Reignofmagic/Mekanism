package mekanism.common.block;

import java.util.Random;

import mekanism.common.Mekanism;
import mekanism.common.MekanismItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockSalt extends Block
{
    public BlockSalt()
    {
        super(Material.sand);
        setCreativeTab(Mekanism.tabMekanism);
        setHardness(0.5F);
        setStepSound(soundTypeSand);
    }
    
/*
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(TextureAtlasSpriteRegister register)
	{
		blockIcon = register.registerIcon("mekanism:SaltBlock");
	}
*/

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune)
    {
        return MekanismItems.Salt;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }
}
