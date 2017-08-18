package hello.dtos;

/**
 * Created by Matthias on 17/08/2017.
 */
public class AddIngredientDto {

    private String name;
    private int amount;
    private String metric;

    public AddIngredientDto() {
    }

    public AddIngredientDto(String name, int amount, String metric) {
        this.name = name;
        this.amount = amount;
        this.metric = metric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
