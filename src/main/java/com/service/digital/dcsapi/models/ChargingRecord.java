package com.service.digital.dcsapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "cdr")
public class ChargingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Invalid Session: Session cannot be blank")
    private String session;

    @NotBlank(message = "Invalid Vehicle: Vehicle cannot be blank")
    private String vehicle;

    @NotNull(message = "Invalid Start Date: Start Date cannot be null")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull(message = "Invalid End Date: End Date cannot be null")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @NotNull(message = "Invalid Total Cost: Total Cost cannot be null")
    @Column(name = "total_cost")
    private double totalCost;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private ChargingPoint chargingPoint;

    @NotNull(message = "Invalid Company ID: Company ID cannot be null")
    @Column(name = "company_id")
    private Long companyId;
}
