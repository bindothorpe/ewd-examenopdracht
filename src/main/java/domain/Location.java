package domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"id", "book"})
@ToString(exclude = "id")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int locationCode1;
    private int locationCode2;
    private String locationName;

    @ManyToOne
    private Book book;

    public Location(int locationCode1, int locationCode2, String locationName) {
        setLocationCodes(locationCode1, locationCode2);
        setLocationName(locationName);
    }

    public Location(int locationCode1, int locationCode2, String locationName, Book book) {
        setLocationCodes(locationCode1, locationCode2);
        setLocationName(locationName);
        this.book = book;
    }

    private void setLocationName(String locationName) {
        if (!locationName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Location name can only contain letters");
        }
        this.locationName = locationName;
    }
    private void setLocationCodes(int locationCode1, int locationCode2) {
        if (locationCode1 < 50 || locationCode1 > 300 || locationCode2 < 50 || locationCode2 > 300) {
            throw new IllegalArgumentException("Location code has to be between 50 and 300");
        }
        if (Math.abs(locationCode1 - locationCode2) < 50) {
            throw new IllegalArgumentException("Location codes have to differ at least 50");
        }

        this.locationCode1 = locationCode1;
        this.locationCode2 = locationCode2;
    }
}
