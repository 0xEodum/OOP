## ER-диаграмма

```mermaid
erDiagram
    PATIENT ||--o{ VISIT : "обращается"
    DOCTOR ||--o{ VISIT : "принимает"

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
        string specialty
    }

    VISIT {
        int visit_id PK
        int patient_id FK
        int doctor_id FK
        date visit_date
        string diagnosis
        decimal treatment_cost
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
   - specialty: специальность врача

3. **VISIT (Посещение)**:
   - visit_id: уникальный идентификатор посещения (первичный ключ)
   - patient_id: внешний ключ, связывающий с таблицей PATIENT
   - doctor_id: внешний ключ, связывающий с таблицей DOCTOR
   - visit_date: дата посещения
   - diagnosis: установленный диагноз
   - treatment_cost: стоимость лечения

## Выбранная таблица для дальнейшей работы:
**Таблица DOCTOR - Врач**

## Диаграмма классов
```mermaid
classDiagram
    class IDoctor {
        <<interface>>
        +getDoctorId() int
        +getLastName() String
        +getFirstName() String
        +getInitials() String
    }

    class BriefDoctor {
        #int doctorId
        #String lastName
        #String firstName
        +BriefDoctor()
        +BriefDoctor(int, String, String)
        +setDoctorId(int)
        +setLastName(String)
        +setFirstName(String)
        +getInitials() String
        +toString() String
    }

    class Doctor {
        -String middleName
        -int qualification
        -String specialty
        -Doctor(Builder)
        +getMiddleName() String
        +getQualification() int
        +getSpecialty() String
        +getInitials() String
        +static createNewDoctor(String, String, String, int, String) Doctor
        +static updateExistingDoctor(int, String, String, String, int, String) Doctor
        +static createFromString(String) Doctor
        +static createFromJson(String) Doctor
        +toJson() String
        +toString() String
        +equals(Object) boolean
        +hashCode() int
        +isSameBriefDoctor(BriefDoctor) boolean
    }

    class DoctorValidator {
        +static validateDoctorId(int)
        +static validateName(String, String)
        +static validateQualification(int)
        +static validateSpecialty(String)
        +static validateDoctor(Doctor)
    }

    IDoctor <|.. BriefDoctor : implements
    BriefDoctor <|-- Doctor : extends
    Doctor ..> DoctorValidator : uses
    BriefDoctor ..> DoctorValidator : uses
```

## Диаграмма классов парсинга
```mermaid
classDiagram
    class IDoctorRepository {
        <<interface>>
        +getById(id: int): Doctor
        +get_k_n_short_list(k: int, n: int): List<BriefDoctor>
        +sortByField(fieldName: String)
        +addDoctor(doctor: Doctor)
        +replaceDoctor(id: int, newDoctor: Doctor)
        +deleteDoctor(id: int)
        +get_count(): int
    }

    class AbstractDoctorRepository {
        <<abstract>>
        #doctors: List<Doctor>
        #filename: String
        #serializationStrategy: SerializationStrategy
        +AbstractDoctorRepository(filename: String, strategy: SerializationStrategy)
        +readFromFile()
        +writeToFile()
        +getById(id: int): Doctor
        +get_k_n_short_list(k: int, n: int): List<BriefDoctor>
        +sortByField(fieldName: String)
        +addDoctor(doctor: Doctor)
        +replaceDoctor(id: int, newDoctor: Doctor)
        +deleteDoctor(id: int)
        +get_count(): int
    }

    class DoctorRepository {
        +DoctorRepository(filename: String, strategy: SerializationStrategy)
    }

    class SerializationStrategy {
        <<interface>>
        +readFromFile(filename: String): List<Doctor>
        +writeToFile(filename: String, doctors: List<Doctor>)
    }

    class AbstractSerializationStrategy {
        <<abstract>>
        #objectMapper: ObjectMapper
        +readFromFile(filename: String): List<Doctor>
        +writeToFile(filename: String, doctors: List<Doctor>)
        #createObjectMapper()*: ObjectMapper
    }

    class JsonSerializationStrategy {
        #createObjectMapper(): ObjectMapper
    }

    class YamlSerializationStrategy {
        #createObjectMapper(): ObjectMapper
    }

    class MongoDBConnection {
        -static instance: MongoDBConnection
        -mongoClient: MongoClient
        -database: MongoDatabase
        -MongoDBConnection()
        +static getInstance(): MongoDBConnection
        +getDatabase(): MongoDatabase
        +close()
    }

    class Doctor_rep_DB {
        -collection: MongoCollection<Document>
        +Doctor_rep_DB()
        +getById(id: int): Doctor
        +get_k_n_short_list(k: int, n: int): List<BriefDoctor>
        +addDoctor(doctor: Doctor)
        +replaceDoctor(id: int, newDoctor: Doctor)
        +deleteDoctor(id: int)
        +get_count(): long
    }

    class DoctorRepositoryDBAdapter {
        -dbRepository: Doctor_rep_DB
        +DoctorRepositoryDBAdapter()
        +getById(id: int): Doctor
        +get_k_n_short_list(k: int, n: int): List<BriefDoctor>
        +sortByField(fieldName: String)
        +addDoctor(doctor: Doctor)
        +replaceDoctor(id: int, newDoctor: Doctor)
        +deleteDoctor(id: int)
        +get_count(): int
    }

    IDoctorRepository <|.. AbstractDoctorRepository : implements
    AbstractDoctorRepository <|-- DoctorRepository : extends
    AbstractDoctorRepository --> SerializationStrategy : uses
    SerializationStrategy <|.. AbstractSerializationStrategy : implements
    AbstractSerializationStrategy <|-- JsonSerializationStrategy : extends
    AbstractSerializationStrategy <|-- YamlSerializationStrategy : extends
    IDoctorRepository <|.. DoctorRepositoryDBAdapter : implements
    DoctorRepositoryDBAdapter --> Doctor_rep_DB : uses
    Doctor_rep_DB --> MongoDBConnection : uses
```
