package model.row;

import model.TableObject;

public class TableObjectRow extends BaseRow<TableObject> {

    @ModelInfo(name = "Lorem")
    private String lorem;
    @ModelInfo(name = "Ipsum")
    private String ipsum;
    @ModelInfo(name = "Solor")
    private String solor;
    @ModelInfo(name = "Amet")
    private String amet;
    @ModelInfo(name = "Diceret")
    private String diceret;

    @Override
    public TableObject asModel() {
        return new TableObject(lorem, ipsum, solor, amet, diceret);
    }
}
