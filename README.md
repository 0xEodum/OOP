

```mermaid
erDiagram
    PATIENT ||--o{ VISIT : "обращается"
    DOCTOR ||--o{ VISIT : "принимает"
    SPECIALTY ||--o{ DOCTOR : "имеет"

    PATIENT {
        int patient_id PK
        string last_name
        string first_name
        string middle_name
        date birth_date
    }

    DOCTOR {
        int doctor_id PK
        string last_name
        string first_name
        string middle_name
        int qualification
        int specialty_id FK
    }

    VISIT {
        int visit_id PK
        int patient_id FK
        int doctor_id FK
        date visit_date
        string diagnosis
        decimal treatment_cost
    }

    SPECIALTY {
        int specialty_id PK
        string specialty_name
    }
```

## Описание таблиц

1. **PATIENT (Пациент)**:
   - patient_id: уникальный идентификатор пациента (первичный ключ)
   - last_name, first_name, middle_name: ФИО пациента
   - birth_date: дата рождения

2. **DOCTOR (Врач)**:
   - doctor_id: уникальный идентификатор врача (первичный ключ)
   - last_name, first_name, middle_name: ФИО врача
   - qualification: квалификация врача
   - specialty_id: внешний ключ, связывающий с таблицей SPECIALTY

3. **VISIT (Посещение)**:
   - visit_id: уникальный идентификатор посещения (первичный ключ)
   - patient_id: внешний ключ, связывающий с таблицей PATIENT
   - doctor_id: внешний ключ, связывающий с таблицей DOCTOR
   - visit_date: дата посещения
   - diagnosis: установленный диагноз
   - treatment_cost: стоимость лечения

4. **SPECIALTY (Специальность)**:
   - specialty_id: уникальный идентификатор специальности (первичный ключ)
   - specialty_name: название специальности

## Выбранная таблица для дальнейшей работы:
**Таблица DOCTOR(Врач)**