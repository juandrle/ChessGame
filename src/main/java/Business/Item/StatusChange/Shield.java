package Business.Item.StatusChange;

import Business.Gamepiece.Gamepiece;

public class Shield extends StatusChangeImpl {

    public Shield(String description) {
        super(description);
    }

    @Override
    public void applyStatusChange(Gamepiece gamepiece) {
        Shield shield = new Shield("Das Schild schütz Sie bei einer Niederlage in einem Minispiel und gibt ihnen die Chance auf ein Rematch.");
        gamepiece.setInventory(shield);
    }

}
