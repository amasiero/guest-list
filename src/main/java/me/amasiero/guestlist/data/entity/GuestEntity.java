package me.amasiero.guestlist.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "guests")
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;
    @Column(name = "accompanying_guests")
    private Integer accompanyingGuests;
    @Column(name = "time_arrived")
    private String timeArrived;

    @Override
    public boolean equals(Object o) {
        return (o instanceof GuestEntity guest) &&
            guest.getName().equals(getName()) &&
            guest.getTable().equals(getTable()) &&
            guest.getAccompanyingGuests().equals(getAccompanyingGuests());
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (accompanyingGuests != null ? accompanyingGuests.hashCode() : 0);
        return result;
    }
}
