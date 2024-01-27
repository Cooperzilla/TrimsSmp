package me.cooperzilla.trimssmp.misc;

import org.bukkit.entity.Arrow;

abstract public class customArrow implements Arrow {
    @Override
    public double getDamage() {
        return 8;
    }
}
