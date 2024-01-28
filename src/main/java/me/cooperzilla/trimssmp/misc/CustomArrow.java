package me.cooperzilla.trimssmp.misc;

import org.bukkit.entity.Arrow;

abstract public class CustomArrow implements Arrow {
    @Override
    public double getDamage() {
        return 8;
    }
}
