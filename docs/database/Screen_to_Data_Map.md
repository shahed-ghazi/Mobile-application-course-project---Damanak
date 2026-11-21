# 5. Screen-to-Data Map

| Screen | Reads (tables / fields) | Writes (insert / update / delete) |
| :--- | :--- | :--- |
| **Sign in** | User (userId, email) | User (update lastSyncAt on login) |
| **Sign up** | User (email — check uniqueness) | User (insert new user profile: userId, fullName, email, language, lastSyncAt) |
| **Home** | User (fullName, lastSyncAt), Warranty (warrantyId, productName, purchaseDate, expiryDate, isSynced), Store (name) | - |
| **Add / Edit warranty (Add mode)** | Store (storeId, name — autocomplete/lookup) | Store (insert if new store), Warranty (insert new warranty), Receipt (insert imageUri, ocrText, totalAmount), Reminder (insert EXPIRY reminder if reminderEnabled is true) |
| **Add / Edit warranty (Edit mode)** | Warranty (all fields), Store (storeId, name), Receipt (receiptId, imageUri), Reminder (reminderId, triggerDate) | Store (insert/update), Warranty (update fields, set isSynced = false), Receipt (insert/update/delete), Reminder (insert/update/delete based on reminderEnabled and expiryDate) |
| **Warranty detail** | Warranty (all fields), Store (name, phone, city), Receipt (imageUri, remoteUrl, ocrText, totalAmount), ServiceVisit (visitId, visitDate, description, isCovered, cost), MaintenanceTask (taskId, title, intervalDays, nextDueDate), OwnershipTransfer (status, transferCode) | ServiceVisit (insert new visit), MaintenanceTask (insert/update nextDueDate), OwnershipTransfer (insert new transfer request) |
| **Warranty detail – Delete confirm** | Warranty (warrantyId, productName) | Warranty (delete record — cascades to Receipt, ServiceVisit, MaintenanceTask, OwnershipTransfer, and Reminder) |
| **Notifications** | Reminder (reminderId, type, triggerDate, isRead), Warranty (productName, expiryDate), MaintenanceTask (title, nextDueDate) | Reminder (update isRead = true when viewed/tapped) |
| **Search & filter** | Warranty (warrantyId, productName, category, serialNumber, purchaseDate, expiryDate), Store (name, city) | - |
| **Profile** | User (userId, fullName, language, lastSyncAt), Warranty (warrantyId, expiryDate — summary counts) | User (update fullName, language, lastSyncAt / clear local session on logout) |
