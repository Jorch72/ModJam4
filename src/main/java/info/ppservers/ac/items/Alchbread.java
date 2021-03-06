package info.ppservers.ac.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import info.ppservers.ac.AlchemicalCombination;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Alchbread extends ItemFood {

    private Object healAmount;
    private Object isWolfsFavoriteMeat;
    private Object saturationModifier;

    public Alchbread(int healamnt_, float saturatamnt, boolean wolfmeat_) {
        super(healamnt_, saturatamnt, wolfmeat_);

        this.setCreativeTab(AlchemicalCombination.ACTab);
        this.setMaxStackSize(16);
        this.setAlwaysEdible();
        this.setMaxDamage(3);
        this.healAmount = healamnt_;
        this.isWolfsFavoriteMeat = wolfmeat_;
        this.saturationModifier = saturatamnt;
        this.setUnlocalizedName("alchBread");
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    private IIcon alchBread;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register){
        alchBread = register.registerIcon("alchcom:"+Info.ALCHBREAD_ICON);
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;

    }


    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return alchBread;
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }

    @Override


    public ItemStack onEaten(ItemStack stack, World world,
                             EntityPlayer player) {

        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(Potion.field_76405_D.id, 50, 1));

            onFoodEaten(stack, world, player);
        }
        return stack;
    }


    @Override
    protected void onFoodEaten(ItemStack stack, World world,
                               EntityPlayer player) {
        int D = stack.getItemDamage();

        if (D > 0) {
            stack.setItemDamage(D--);
        } else {
            stack.stackSize--;
        }
        super.onFoodEaten(stack, world, player);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack,
                               EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

        par3List.add("Eating this will ");
        par3List.add("make you feel");
        par3List.add("balanced");
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    }

}
