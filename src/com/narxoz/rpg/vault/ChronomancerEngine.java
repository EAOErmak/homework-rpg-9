package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.Artifact;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.CurseDetector;
import com.narxoz.rpg.artifact.EnchantmentScanner;
import com.narxoz.rpg.artifact.GoldAppraiser;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.WeightLedger;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return a summary of the vault run
     */
    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            System.out.println("The Chronomancer's Vault stays sealed because no hero entered.");
            return new VaultRunResult(0, 0, 0);
        }

        System.out.println();
        System.out.println("=== Chronomancer's Vault Opens ===");
        printPartyState(party);

        int appraisalPasses = 0;
        int mementosCreated = 0;
        int restoredCount = 0;

        System.out.println();
        System.out.println("=== Appraisal Suite: Initial Three Visitors ===");

        GoldAppraiser goldAppraiser = new GoldAppraiser();
        appraisalPasses += applyVisitorToParty("Gold Appraiser", party, goldAppraiser);
        System.out.println("Gold Appraiser total estimate: " + goldAppraiser.getTotalEstimatedValue() + " gold.");

        EnchantmentScanner enchantmentScanner = new EnchantmentScanner();
        appraisalPasses += applyVisitorToParty("Enchantment Scanner", party, enchantmentScanner);
        System.out.println("Enchantment Scanner resonance score: " + enchantmentScanner.getResonanceScore() + ".");

        CurseDetector curseDetector = new CurseDetector();
        appraisalPasses += applyVisitorToParty("Curse Detector", party, curseDetector);
        System.out.println("Curse Detector flagged items: " + curseDetector.getCursedCount() + ".");

        System.out.println();
        System.out.println("=== Open/Closed Proof: Fourth Visitor ===");
        WeightLedger weightLedger = new WeightLedger();
        appraisalPasses += applyVisitorToParty("Weight Ledger", party, weightLedger);
        System.out.println("Weight Ledger total load: " + weightLedger.getTotalWeight() + " units.");

        Hero target = firstHero(party);
        if (target == null) {
            return new VaultRunResult(appraisalPasses, mementosCreated, restoredCount);
        }

        Caretaker caretaker = new Caretaker();

        System.out.println();
        System.out.println("=== Time Crystal Snapshot ===");
        System.out.println("Before snapshot: " + describeHero(target));
        caretaker.save(target.createMemento());
        mementosCreated++;
        System.out.println("Caretaker history size after save: " + caretaker.size() + ".");
        System.out.println("Caretaker peek confirms rewind point exists: " + (caretaker.peek() != null) + ".");

        System.out.println();
        System.out.println("=== Vault Trap Springs ===");
        springTemporalTrap(target);
        System.out.println("After trap: " + describeHero(target));

        System.out.println();
        System.out.println("=== Chronomancer Rewind ===");
        HeroMemento rewindPoint = caretaker.undo();
        if (rewindPoint != null) {
            target.restoreFromMemento(rewindPoint);
            restoredCount++;
            System.out.println("After rewind: " + describeHero(target));
        } else {
            System.out.println("No rewind point was available.");
        }

        System.out.println();
        System.out.println("Vault sequence complete.");
        return new VaultRunResult(appraisalPasses, mementosCreated, restoredCount);
    }

    private int applyVisitorToParty(String title, List<Hero> party, ArtifactVisitor visitor) {
        System.out.println("-- " + title + " --");

        int visitedArtifacts = 0;
        for (Hero hero : party) {
            if (hero == null) {
                continue;
            }

            System.out.println(hero.getName() + " presents " + hero.getInventory().size() + " artifacts.");
            hero.getInventory().accept(visitor);
            visitedArtifacts += hero.getInventory().size();
        }

        return visitedArtifacts;
    }

    private void printPartyState(List<Hero> party) {
        for (Hero hero : party) {
            if (hero != null) {
                System.out.println(describeHero(hero));
            }
        }
    }

    private Hero firstHero(List<Hero> party) {
        for (Hero hero : party) {
            if (hero != null) {
                return hero;
            }
        }
        return null;
    }

    private void springTemporalTrap(Hero hero) {
        hero.takeDamage(42);
        hero.spendMana(Math.min(18, hero.getMana()));
        hero.spendGold(Math.min(35, hero.getGold()));

        List<Artifact> remainingArtifacts = new ArrayList<>(hero.getInventory().getArtifacts());
        if (!remainingArtifacts.isEmpty()) {
            Artifact lostArtifact = remainingArtifacts.remove(remainingArtifacts.size() - 1);
            System.out.println("The hourglass trap disintegrates " + lostArtifact.getName() + ".");
        }

        hero.setInventory(new Inventory(remainingArtifacts));
    }

    private String describeHero(Hero hero) {
        return hero.getName()
                + " {hp=" + hero.getHp() + "/" + hero.getMaxHp()
                + ", mana=" + hero.getMana()
                + ", gold=" + hero.getGold()
                + ", inventory=" + inventoryNames(hero.getInventory())
                + "}";
    }

    private String inventoryNames(Inventory inventory) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (Artifact artifact : inventory.getArtifacts()) {
            joiner.add(artifact.getName());
        }
        return joiner.toString();
    }
}
