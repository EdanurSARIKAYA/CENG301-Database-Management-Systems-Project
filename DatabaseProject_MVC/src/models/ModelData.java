package models;

import java.sql.ResultSet;

public class ModelData {

    public final String modelName;
    public final ResultSet resultSet;
    public final int recordCount;

    public ModelData() {
        this.modelName = null;
        this.resultSet = null;
        this.recordCount = 0;
    }

    public ModelData(String modelName, ResultSet resultSet) {
        this.modelName = modelName;
        this.resultSet = resultSet;
        this.recordCount = 0;
    }

    public ModelData(String modelName, int recordCount) {
        this.modelName = modelName;
        this.resultSet = null;
        this.recordCount = recordCount;
    }

    public ModelData(String modelName, ResultSet resultSet, int recordCount) {
        this.modelName = modelName;
        this.resultSet = resultSet;
        this.recordCount = recordCount;
    }

    @Override
    public String toString() {
        return "Model Data (" + modelName + ")";
    }

}
