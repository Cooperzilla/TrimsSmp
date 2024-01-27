package me.cooperzilla.trimssmp.trims;

import com.google.common.collect.Multimap;
import me.cooperzilla.trimssmp.misc.customArrow;
import me.cooperzilla.trimssmp.utils.CheaksUtils;
import me.cooperzilla.trimssmp.utils.ColorUtils;
import me.cooperzilla.trimssmp.utils.CooldownUtils;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.Color;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Tbd implements Listener {

    private final int num = NumUtils.getNum(10);
    private final long COOLDOWN_DURATION = NumUtils.seconds(20);

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (CheaksUtils.hasCorrectIngredients(event, num)) {
            Color color = ColorUtils.getColorFromAdjacentOre(event);
            if (color != null) {
                ColorUtils.applyColor(result, color, num);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        if (CheaksUtils.hasTrim(item, num) && (player.isSwimming() || player.getWorld().hasStorm() || player.getWorld().isThundering())) {
            if (event.getAction().name().contains("RIGHT_CLICK")) {
                if (!(player.hasMetadata("sentry_cooldown"))) {

                    AttributeModifier modifier = new AttributeModifier(
                            UUID.randomUUID(),
                            Attribute.GENERIC_ATTACK_SPEED.toString(),
                            0.3,
                            AttributeModifier.Operation.ADD_NUMBER
                    );

                    item.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);

                    AttributeModifier negative_modifier = new AttributeModifier(
                            UUID.randomUUID(),
                            Attribute.GENERIC_ATTACK_SPEED.toString(),
                            -0.3,
                            AttributeModifier.Operation.ADD_NUMBER
                    );

                    item.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, negative_modifier);


                    CooldownUtils.setCooldown(player, "sentry_cooldown", COOLDOWN_DURATION);

                } else {
                    player.sendMessage("Ability on cooldown!");
                }
            }
        }
    }
}
