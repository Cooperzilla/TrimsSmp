package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public class Dune extends ItemClass {

    public Dune() {
        super(NumUtils.getNum(10), NumUtils.seconds(20), "dune_cooldown");
    }

    @Override
    protected void run(Player player, ItemStack item) {

        AttributeModifier modifier = new AttributeModifier(
                UUID.randomUUID(),
                Attribute.GENERIC_ATTACK_SPEED.toString(),
                0.3,
                AttributeModifier.Operation.ADD_NUMBER
        );

        Objects.requireNonNull(item.getItemMeta()).addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);

        AttributeModifier negative_modifier = new AttributeModifier(
                UUID.randomUUID(),
                Attribute.GENERIC_ATTACK_SPEED.toString(),
                -0.3,
                AttributeModifier.Operation.ADD_NUMBER
        );

        item.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, negative_modifier);
    }

}
