## 1. Entities at a Glance

| Entity (table name) | What it stores | Related to |
| :--- | :--- | :--- |
| **User** | Profile, authentication, and sync preferences of an application user | Warranty, OwnershipTransfer |
| **Store** | Retail store details (name, phone, city) where a product was purchased | Warranty |
| **Warranty** | Core product warranty record including purchase/expiry dates, price, and serial number | User, Store, Receipt, ServiceVisit, MaintenanceTask, OwnershipTransfer, Reminder |
| **Receipt** | Digital receipt images, remote URLs, and extracted OCR text/amount for a warranty | Warranty |
| **ServiceVisit** | Repair or maintenance service logs, visit dates, costs, and coverage status | Warranty |
| **MaintenanceTask** | Recurring preventive maintenance schedules and intervals for a warranted product | Warranty, Reminder |
| **OwnershipTransfer** | Records and status of transferring a product warranty from one user to another | Warranty, User |
| **Reminder** | Scheduled notifications triggered for upcoming warranty expirations or maintenance tasks | Warranty, MaintenanceTask |
