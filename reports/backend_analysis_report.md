# Backend Codebase Analysis Report: Event Registration API

Based on a thorough review of the codebase located at `backend/event-registration-api`, several critical and moderate issues have been identified across Security, Business Logic, and API Design.

## 🚨 1. Security Issues

### 1.1 Hardcoded Cryptographic Keys & Secrets
- **Issue**: [JwtServiceImpl.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/JwtServiceImpl.java) contains a hardcoded JWT secret (`SECRET="9070...`) rather than loading it from a secure environment variable. It completely ignores the `spring.jwt.secret` configuration.
- **Risk**: Critical. Anyone with access to the source code can forge JWT tokens and impersonate any user, including admins.
- **Location**: [src/main/java/com/event_registration/lk/service/impl/JwtServiceImpl.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/JwtServiceImpl.java)
- **Fix**: Revert to using `@Value("${jwt.secret}")` and inject it from [application.yml](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/resources/application.yml) or environment variables.

### 1.2 Overly Permissive CORS Configuration
- **Issue**: In [SecurityConfig.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/config/SecurityConfig.java), CORS is configured to allow `*` (all origins) for all methods.
- **Risk**: High. This allows any website to make cross-origin requests to the API, potentially leading to Cross-Site Request Forgery (CSRF) or data exfiltration if combined with other vulnerabilities.
- **Location**: [src/main/java/com/event_registration/lk/config/SecurityConfig.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/config/SecurityConfig.java)
- **Fix**: Restrict `setAllowedOriginPatterns` to specific trusted frontend domains (e.g., `http://localhost:3000`).

### 1.3 Hardcoded Admin Credentials
- **Issue**: [AdminUserInitializer.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/config/AdminUserInitializer.java) inserts a default admin user with a hardcoded password (`admin123`).
- **Risk**: High. If deployed to production without changing the password, attackers can easily gain admin access.
- **Location**: [src/main/java/com/event_registration/lk/config/AdminUserInitializer.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/config/AdminUserInitializer.java)
- **Fix**: Use environment variables to pass the initial admin password dynamically during the first launch.

### 1.4 Unhandled JWT exceptions
- **Issue**: [JwtFilter.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/config/JwtFilter.java) does not catch or handle exceptions thrown by [JwtServiceImpl](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/JwtServiceImpl.java#21-114) when processing invalid or expired tokens (e.g., `ExpiredJwtException`, `MalformedJwtException`).
- **Risk**: Moderate. It may result in a `500 Internal Server Error` instead of a standardized `401 Unauthorized` response.
- **Location**: [src/main/java/com/event_registration/lk/config/JwtFilter.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/config/JwtFilter.java)

---

## ⚙️ 2. Business Logic & Stability Issues

### 2.1 Missing Transactional Annotations & Destructive Updates
- **Issue**: `EventServiceImpl.updateEvent()` implements an update by first deleting the existing entity (`eventRepository.delete(...)`) and then re-saving a new entity. Furthermore, this method is not annotated with `@Transactional`.
- **Risk**: Critical. If the `save()` operation fails after [delete()](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/controller/UserController.java#24-28) succeeds, the event is permanently lost. It also breaks any relational integrity (foreign keys) pointing to the old event.
- **Location**: [src/main/java/com/event_registration/lk/service/impl/EventServiceImpl.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/EventServiceImpl.java)
- **Fix**: Fetch the entity, update its fields via setters, and let Hibernate's `@Transactional` dirty-checking apply the update automatically.

### 2.2 NullPointerException (NPE) Risks in Authentication
- **Issue**: `UserServiceImpl.loginUser` fetches a user by email and blindly converts it without checking for null. If the user doesn't exist, `objectMapper.convertValue(entity, User.class)` will throw `IllegalArgumentException`, or `user.setPassword(null)` will throw an NPE. The same issue exists in [getUserByEmail(email)](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/UserServiceImpl.java#128-136).
- **Risk**: High. Login attempts with unregistered emails will crash the server thread instead of returning an error.
- **Location**: [src/main/java/com/event_registration/lk/service/impl/UserServiceImpl.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/UserServiceImpl.java)

### 2.3 Race Condition in Ticket Generation
- **Issue**: `BookingServiceImpl.generateTicketNumber()` counts the tickets for the current day to generate a sequence number. In a highly concurrent environment, two users booking simultaneously might get the exact same sequence number and ticket ID.
- **Risk**: Moderate. Potential uniqueness constraint violations on ticket numbers.
- **Location**: [src/main/java/com/event_registration/lk/service/impl/BookingServiceImpl.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/BookingServiceImpl.java)
- **Fix**: Rely on auto-incrementing database sequences, UUIDs, or Redis atomic counters for ticket generation.

### 2.4 Exception Swallowing & Poor Error Handling
- **Issue**: Across most services ([EventServiceImpl](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/EventServiceImpl.java#17-109), [BookingServiceImpl](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/BookingServiceImpl.java#23-147), [UserServiceImpl](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/UserServiceImpl.java#20-137)), exceptions are caught with a generic `catch (Exception e)` and swallowed, returning a generic DTO like `new UserResponse("signup", "unsuccess")`. The exceptions are rarely logged properly (except in BookingServiceImpl).
- **Risk**: High. Makes debugging very difficult in production, and hides systemic failures (like database connection issues) as simple validation errors.

### 2.5 Copy-Paste Errors
- **Issue**: The [getAllUsers()](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/controller/UserController.java#40-44) method in [UserServiceImpl](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/UserServiceImpl.java#20-137) mistakenly returns a `UserResponse` with the status `"event-list"`, which is clearly leftover from `EventServiceImpl.getAllEvents()`.
- **Location**: [src/main/java/com/event_registration/lk/service/impl/UserServiceImpl.java](file:///d:/becs/year_03/semester_05/becs_31213_enterprise_sw_design/backend/event-registration-api/src/main/java/com/event_registration/lk/service/impl/UserServiceImpl.java)

---

## 🏗️ 3. API Design & Best Practices

### 3.1 HTTP Status Codes Ignored
- **Issue**: Controllers return an `HTTP 200 OK` for almost **all** requests, regardless of whether the operation was a success, a bad request, or a server error. The actual success/fail status is wrapped inside custom Response DTOs.
- **Risk**: Moderate. Breaks REST API conventions. Frontend clients and load balancers cannot distinguish successful operations from failures without parsing the JSON body.
- **Fix**: Use Spring's `ResponseEntity<T>` to return proper HTTP codes (e.g., `201 Created`, `400 Bad Request`, `404 Not Found`).

### 3.2 Missing Input Validation
- **Issue**: Endpoints lack validation layers. DTOs are not annotated with `jakarta.validation.constraints` (like `@NotBlank`, `@Email`, etc.), and `@Valid` is missing on `@RequestBody` parameters in Controllers.
- **Risk**: Moderate. Users can send malformed or empty data, polluting the database.

### 3.3 Query Parameters vs. Path Variables
- **Issue**: Controllers use `@RequestParam` for identifying resources (e.g., `@DeleteMapping("/delete") public EventResponse deleteEvent(@RequestParam String eventId)`).
- **Best Practice**: REST API resource identifiers should generally use `@PathVariable` (e.g., `DELETE /event/{eventId}`) rather than query parameters.

### 3.4 Incomplete Booking Verifications
- **Issue**: `BookingServiceImpl.bookEvent` does not explicitly verify if the `eventId` or `userId` actually exists in the database before creating the booking. It blindly saves the foreign keys. If database foreign key constraints are missing, this leads to orphaned bookings.
