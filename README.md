# Citizen Plans Reporting Application

This is a Spring MVC-based web application for searching and exporting **Citizen Plans** data.  
Users can search plans by filters and download the results as **PDF** or **Excel** reports.

---

## **Features**
- Search Citizen Plans by:
  - Plan Name
  - Plan Status
- View search results in a web UI
- Export results to:
  - **PDF** (`plans.pdf`)
  - **Excel** (`plans.xls`)
- Dynamic form filters loaded from the database
- MVC architecture with service and controller layers

---

## **Technologies Used**
- **Java 8+**
- **Spring Boot / Spring MVC**
- **Thymeleaf** for rendering views
- **Apache POI** for Excel export
- **iText / OpenPDF** for PDF export
- **Maven** for build management
- **MySQL / H2 Database** (depending on configuration)

---

## **Endpoints**

### **1. Load Search Page**

- Loads the search form with plan name and status dropdowns.
- Populates data from `ReportService`.

### **2. Search Plans**
- Accepts a `SearchRequest` object (plan name, plan status, etc.).
- Displays matching results in `index.html`.

### **3. Export PDF**
- Generates and downloads a **plans.pdf** file containing search results.

### **4. Export Excel**
- Generates and downloads a **plans.xls** file containing search results.

---

## **Project Structure**
src/main/java/com/gmk/
├── controller
│ └── ReportController.java
├── entities
│ └── CitizenPlans.java
├── request
│ └── SearchRequest.java
├── service
│ └── ReportService.java
│ └── ReportServiceImpl.java
---

## **How to Run**
1. **Clone the repository**
   ```bash
   git clone <repo_url>
   cd citizen-plans-report

mvn spring-boot:run

