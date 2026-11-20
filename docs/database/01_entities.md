# Section 1 — Entities at a Glance

| Entity (table name) | What it stores | Related to |
|---|---|---|
| User | The app account: name, email, language (Firebase UID) | Warranty, OwnershipTransfer |
| Store | A store where a product was bought | Warranty |
| Warranty | One product the user bought and its warranty dates | User, Store, Receipt, ServiceVisit, MaintenanceTask, Reminder, OwnershipTransfer |
| Receipt | The purchase receipt photo and its OCR text | Warranty |
| ServiceVisit | One repair or service visit, and whether the warranty covered it | Warranty |
| MaintenanceTask | A repeating preventive-care task for a product | Warranty, Reminder |
| Reminder | A notification for a warranty expiry or a maintenance due date | Warranty or MaintenanceTask |
| OwnershipTransfer | A QR transfer of a warranty from one user to another | Warranty, User |
