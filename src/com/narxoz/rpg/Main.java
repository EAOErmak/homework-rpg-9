package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

/**
 * Entry point for Homework 9 - Chronomancer's Vault: Visitor + Memento.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        List<Hero> party = List.of(
                createAriadne(),
                createBorin());

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(party);

        System.out.println();
        System.out.println("Final result: " + result);
    }

    private static Hero createAriadne() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Weapon("Sunfire Blade", 120, 6, 14));
        inventory.addArtifact(new Potion("Bloodmoon Tonic", 45, 1, 35));
        inventory.addArtifact(new Scroll("Scroll of Frozen Hours", 80, 1, "Temporal Lock"));
        inventory.addArtifact(new Ring("Ring of Echoing Stars", 95, 1, 12));
        inventory.addArtifact(new Armor("Gravewarden Cuirass", 140, 10, 16));

        return new Hero("Ariadne", 120, 70, 18, 10, 95, inventory);
    }

    private static Hero createBorin() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Weapon("Storm Pike", 110, 7, 12));
        inventory.addArtifact(new Potion("Elixir of Dawn", 40, 1, 28));
        inventory.addArtifact(new Scroll("Scroll of Ember Path", 70, 1, "Flame Step"));
        inventory.addArtifact(new Ring("Ring of the North Star", 90, 1, 10));
        inventory.addArtifact(new Armor("Mirrorplate Aegis", 150, 12, 18));

        return new Hero("Borin", 150, 25, 22, 14, 55, inventory);
    }
}
