package com.narxoz.rpg.artifact;

/**
 * Flags suspicious or cursed artifacts in the vault.
 */
public class CurseDetector implements ArtifactVisitor {

    private int cursedCount;

    public int getCursedCount() {
        return cursedCount;
    }

    @Override
    public void visit(Weapon weapon) {
        boolean cursed = hasDarkName(weapon.getName()) || weapon.getAttackBonus() >= 15;
        report("Weapon", weapon.getName(), cursed, cursed
                ? "The blade thirsts for repeated combat."
                : "No hostile aura detected.");
    }

    @Override
    public void visit(Potion potion) {
        boolean cursed = hasDarkName(potion.getName());
        report("Potion", potion.getName(), cursed, cursed
                ? "The liquid carries lunar corruption."
                : "The brew is stable and safe.");
    }

    @Override
    public void visit(Scroll scroll) {
        String spell = scroll.getSpellName().toLowerCase();
        boolean cursed = spell.contains("lock") || spell.contains("hex");
        report("Scroll", scroll.getName(), cursed, cursed
                ? "The script can bind time against its reader."
                : "The script feels controlled.");
    }

    @Override
    public void visit(Ring ring) {
        boolean cursed = hasDarkName(ring.getName()) || ring.getMagicBonus() >= 12;
        report("Ring", ring.getName(), cursed, cursed
                ? "The loop reflects dangerous arcane feedback."
                : "The aura is balanced.");
    }

    @Override
    public void visit(Armor armor) {
        boolean cursed = hasDarkName(armor.getName()) || armor.getDefenseBonus() >= 18;
        report("Armor", armor.getName(), cursed, cursed
                ? "The plating is haunted by old sentries."
                : "The wards are defensive only.");
    }

    private void report(String type, String name, boolean cursed, String detail) {
        if (cursed) {
            cursedCount++;
            System.out.println("[Curse] " + type + " " + name + " is FLAGGED. " + detail);
            return;
        }

        System.out.println("[Curse] " + type + " " + name + " is clear. " + detail);
    }

    private boolean hasDarkName(String name) {
        String lowered = name.toLowerCase();
        return lowered.contains("blood")
                || lowered.contains("grave")
                || lowered.contains("void")
                || lowered.contains("cursed")
                || lowered.contains("echo");
    }
}
