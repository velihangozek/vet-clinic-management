# 🐾 Vet Clinic Management System

A Java Spring Boot project for managing veterinary clinic operations including animals, doctors, appointments, vaccines, and their relationships. Built with layered architecture, DTOs, ModelMapper, and best practices for scalability and maintainability.

---

## 📁 Project Structure

```
vet-clinic-management/
│
├── api/                     # REST controllers
├── business/                # Service layer
│   ├── abstracts/           # Service interfaces
│   └── concretes/           # Service implementations
├── core/                    # Utilities, configurations, exceptions, results
├── dto/                     # Data Transfer Objects (Request & Response)
│   ├── request/
│   └── response/
├── entities/                # JPA entity classes
├── repository/              # Spring Data JPA repositories
└── VetClinicManagementApp.java  # Main class
```

---

## 🔧 Technologies

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **ModelMapper**
- **PostgreSQL**
- **Lombok**
- **Jakarta Validation**
- **RESTful APIs**

---

## 📦 Features

- Manage **Animals**, **Doctors**, **Customers**, **Appointments**, **Vaccines**
- Many-to-Many and One-to-Many **JPA Relationships**
- Bidirectional mapping and lifecycle operations via **cascade/orphanRemoval**
- DTO structure with **Request/Response separation**
- **ModelMapper** for entity-DTO conversion
- Custom **exception handling** and meaningful API responses
- Validations using **Jakarta Annotations**
- Pagination with `cursor` endpoints
- Date-based filtering for animal vaccine protection periods

---

## 📚 API Endpoints (Sample)

### ✅ Available Dates
- `POST /v1/available-dates`
- `GET /v1/available-dates/{id}`
- `GET /v1/available-dates?page=0&pageSize=5`
- `PUT /v1/available-dates`
- `DELETE /v1/available-dates/{id}`

### ✅ Assign Available Date to Doctor
- `POST /v1/doctors/assign-available-date`

### ✅ Appointments & Vaccinations
- `POST /v1/appointments`
- `GET /v1/vaccines/filter-by-protection?start=2025-06-01&end=2025-06-30`

---

## 🗃️ Entities Overview

- **Doctor**: ManyToMany with `AvailableDate`, OneToMany `Appointment`
- **Animal**: ManyToOne `Customer`, OneToMany `Appointment`, OneToMany `AnimalVaccine`
- **Vaccine**: OneToMany `AnimalVaccine`
- **AnimalVaccine**: Join table with additional protection date fields
- **Appointment**: Connects `Doctor` and `Animal` with timestamp

---

## 🧪 Sample Validation

```java
@NotBlank(message = "Animal name cannot be blank")
private String name;

@NotNull(message = "Date of birth must be provided")
@PastOrPresent(message = "Birthdate must be past or present")
private LocalDate dateOfBirth;
```

---

## 💡 Development Tips

- Use **Postman** to test endpoints.
- Apply **DTO validation** before mapping.
- Avoid **CascadeType.REMOVE** on many-to-many relationships.
- Use **FetchType.LAZY** for collections to prevent performance issues.
- Use bidirectional `@PrePersist` if appointments/vaccines must sync both sides.

---

## 📷 Screenshots

Place entity table screenshots under:
```
src/main/resources/screenshots/
```
Reference them in the README as needed like:
```markdown
![Doctors Table](screenshots/doctors_table.png)
```

---

## 🚀 Getting Started

1. Clone the repository
2. Configure PostgreSQL credentials in `application.properties`
3. Run the project with your IDE or using:
```bash
./mvnw spring-boot:run
```
4. Use Postman or Swagger UI to test the endpoints

---

## 📜 License

MIT - © Velihan Gözek, 2025