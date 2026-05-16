package com.tugas.deploy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "20230140047_ibnaty")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String Id;
    private String nama;
    private String nim;
    private String jenis_kelamin;
}