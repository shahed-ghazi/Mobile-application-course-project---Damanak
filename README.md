# Mobile-application-course-project---Damanak
# Damanak (ضمانك) — Receipt & Warranty Keeper

An offline Android app that keeps household receipts and warns you before a warranty expires.

**CSC 402 — Mobile Application Programming · Term Project · Group [XX]**
Imam Abdulrahman Bin Faisal University · College of Computer Science and Information Technology

---

## The problem

Warranties are proven by paper receipts. Thermal receipts fade within months, receipts get scattered, and nothing warns the owner that coverage is about to end. A repair that should have been free becomes a paid one.

Existing warranty apps are English-only, require an account, and upload receipt images — which contain purchase and payment details — to third-party servers.

## The solution

Damanak stores receipts and purchase data entirely on the device. It calculates each warranty's expiry date, shows every product's status at a glance, and sends notifications before coverage ends. No account, no server, no internet required.

---

## Features

| Feature | Priority |
| --- | --- |
| Add a product and capture its receipt with the camera | Must |
| Automatic warranty status (active / expiring soon / expired) | Must |
| Tiered notifications at 30, 7, and 1 day before expiry | Must |
| Home screen ordered by nearest expiry, with status indicators | Must |
| Product details with a full-screen zoomable receipt viewer | Must |
| Service history log per product | Must |
| Search, filter, and categories | Should |
| Backup and restore | Should |
| Receipt text extraction (OCR) | Could |

---

## Tech stack

- **Language:** Kotlin
- **UI:** Jetpack Compose · Material 3
- **Architecture:** MVVM with unidirectional data flow (UI → Domain → Data)
- **Persistence:** Room
- **Camera:** CameraX
- **Background work:** WorkManager
- **State:** ViewModel + StateFlow
- **Navigation:** Navigation Compose

**Minimum SDK:** 24 · **Target SDK:** 34

---

## Project structure

```
app/src/main/java/.../damanak/
├── data/          # Room entities, DAOs, repositories
├── domain/        # models and warranty logic
├── ui/
│   ├── home/      # product list
│   ├── detail/    # product details and service history
│   ├── addedit/   # add and edit forms
│   ├── search/    # search and filtering
│   └── theme/     # shared design system
├── work/          # WorkManager checks and notifications
└── util/
```

---

## Getting started

```bash
git clone https://github.com/[org]/[repo].git
```

1. Open the project in Android Studio (Ladybug or newer).
2. Let Gradle sync finish.
3. Run on an emulator or device with API 24+.

Grant the camera and notification permissions when prompted — receipt capture and expiry reminders depend on them.

---

## Team

| # | Name | Role |
| --- | --- | --- |
| 1 | | Team Lead & Data — repository, project board, Room and DAOs |
| 2 | | Media — CameraX capture, image storage, receipt viewer |
| 3 | | Warranty Logic — expiry calculation, WorkManager, notifications |
| 4 | | UI — home screen, product list, design system |
| 5 | | Forms & Search — add/edit, validation, categories, filtering |
| 6 | | QA & Documentation — details, service history, tests, docs |

Individual contribution is tracked through commit history, pull requests, and assigned issues.

---

## Documentation

| Milestone | File |
| --- | --- |
| M2 | [Architecture](docs/architecture.md) · [Architecture diagram](docs/architecture.png) · [Navigation map](docs/navigation-map.png) · [Style guide](docs/style-guide.md) · [Wireframes](docs/wireframes/) · [Figma prototype](docs/figma.md) |
| M3-A | [Database design](docs/database/) |
| All | [AI Use Log](AI_USE_LOG.md) |

---

## Milestones

| Milestone | Focus | Target |
| --- | --- | --- |
| M1 | Team formation & proposal | Week 3 |
| M2 | UI/UX design & architecture | Week 6 |
| M3 | Core development sprint | Week 9 |
| M4 | Feature completion — beta build | Week 12 |
| M5 | Testing, polish & documentation | Week 14 |
| Final | Submission & presentation | Weeks 15–16 |

---

## Contributing

- One branch per feature: `feature/<short-name>`
- Every task starts as a GitHub issue assigned to a named member
- Pull requests need one review before merging into `main`
- Commit messages follow `type: short description` (`feat:`, `fix:`, `docs:`, `test:`)

---

## License

Coursework submission for CSC 402. Not licensed for reuse.
