# Layer responsibilities

| Layer | Responsibility | Key classes | Rule |
|-------|----------------|-------------|------|
| **UI** | Displays the user interface and handles user interactions | Screens, ViewModels, AppNavHost | Must not contain data fetching or business logic |
| **Domain** | Contains core business logic and app use cases | Models, UseCases, Repository interfaces | Must not depend on UI or Data layer frameworks |
| **Data** | Manages data fetching, local storage, and cloud synchronization | Room, Firebase, Mappers, Repository impl | Must not contain any UI-related code |

---

# Unidirectional Data Flow

| | |
|---|---|
| **Example screen** | Sign in / Sign up |
| **UiState** | **AuthUiState:**<br>- **Loading:** `isLoading = true` (shows progress indicator)<br>- **Success:** `authSuccess = true` (navigates to HomeScreen)<br>- **Error:** `errorMessage != null` (shows error message)<br>- **Form Data:** `isSignUpTab`, `fullName`, `email`, `password` |
| **Events (UI → ViewModel)** | **AuthEvent received by AuthViewModel:**<br>- `OnTabSwitched (isSignUp)` / (switches between Sign In and Sign Up tabs)<br>- `OnFullNameChanged (name)` / (updates full name input)<br>- `OnEmailChanged (email)` / (updates email input)<br>- `OnPasswordChanged (password)` / (updates password input)<br>- `OnSubmitClicked` / (triggers login or sign-up authentication) |
| **Explanation** | When the user clicks submit on AuthScreen, an event is sent to AuthViewModel. The ViewModel sets `isLoading = true` in AuthUiState while checking credentials through SignInUseCase. Next, the Data layer verifies the user using Firebase Authentication. Once verified, AuthUiState updates to `authSuccess = true` and the app opens HomeScreen. |

---

# Walk-through of one feature

| Step | Layer | What happens (use real class and function names) |
|------|-------|--------------------------------------------------|
| 1 | UI | User enters credentials and taps (Sign In) on AuthScreen, calling `AuthViewModel.signIn()` |
| 2 | UI | AuthViewModel sets `isLoading = true` in AuthUiState to show a loading indicator |
| 3 | Domain | AuthViewModel calls `SignInUseCase.execute(email, password)` |
| 4 | Data | AuthRepositoryImpl sends credentials to Firebase Authentication for validation |
| 5 | Data | Firebase Authentication authenticates the user and returns the result to AuthRepositoryImpl |
| 6 | UI | AuthViewModel updates AuthUiState with `authSuccess = true`, and AuthScreen navigates to HomeScreen |
