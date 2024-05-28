package FirstAssignment;
public class Item {
    private String itemName;
    private String itemDescription;
    private String itemUse;
    private int itemStat;

    public Item(String itemName, String itemDescription, String itemUse, int itemStat) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemUse = itemUse;
        this.itemStat = itemStat;
    }

    public String getItemName() {return itemName;}
    public String getItemDescription() {return itemDescription;}
    public String getItemUse() {return itemUse;}
    public int getItemStat() {return itemStat;}
}


