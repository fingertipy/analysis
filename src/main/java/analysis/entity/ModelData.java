package analysis.entity;

import lombok.Data;

import java.util.List;

@Data
public class ModelData {
    private Integer type;
    private Long account;
    private String signatureImage;
    private List<Coordinate> coordinates;

    public ModelData(Integer type, Long account, List<Coordinate> coordinates) {
        this.type = type;
        this.account = account;
        this.coordinates = coordinates;
    }
    public ModelData(Integer type, Long account, String signatureImage) {
        this.type = type;
        this.account = account;
        this.signatureImage = signatureImage;
    }

    public static ModelData buidle(Integer type, Long account, String signatureImage) {
        return new ModelData(type, account, signatureImage);
    }

    public static ModelData buidle(Integer type, Long account, List<Coordinate> coordinates) {
        return new ModelData(type, account, coordinates);
    }
}
