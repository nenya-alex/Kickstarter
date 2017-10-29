package ua.nenya.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PaymentDTO implements Serializable{

    private Long id;

    private Long projectId;

    @Range(min = 1, max = Integer.MAX_VALUE)
    private int amount;

    @NotNull
    @Length(min=2, max=20,
            message="Cardholder name must be between 2 and 20 characters long.")
    private String cardholderName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentDTO that = (PaymentDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", cardholderName='" + cardholderName + '\'' +
                '}';
    }
}
